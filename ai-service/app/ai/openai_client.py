from openai import AsyncOpenAI

from app.ai.prompt import load_resume_extraction_prompt
from app.core.config import get_settings
from app.schemas.candidate import ExtractedCandidateSchema


class OpenAIClient:

    def __init__(self):
        settings = get_settings()

        self.client = AsyncOpenAI(
            api_key=settings.openai_api_key
        )

        self.model = settings.openai_model

    async def extract_candidate(
        self,
        resume_text: str,
    ) -> ExtractedCandidateSchema:

        prompt = load_resume_extraction_prompt(
            resume_text
        )

        response = await self.client.responses.parse(
            model=self.model,
            input=prompt,
            text_format=ExtractedCandidateSchema,
        )

        for output in response.output:

            if output.type != "message":
                continue

            for content in output.content:

                if content.type != "output_text":
                    continue

                if content.parsed is None:
                    raise ValueError(
                        "OpenAI returned empty parsed output"
                    )

                return content.parsed

        raise ValueError(
            "Could not parse OpenAI response"
        )
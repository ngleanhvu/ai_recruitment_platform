from app.ai.openai_client import OpenAIClient
from app.document.document_service import DocumentService
from app.infrastructure.storage.minio_client import MinioStorage
from app.schemas.candidate import ExtractedCandidateSchema


class ExtractionService:

    def __init__(self):
        self.storage = MinioStorage()
        self.document_service = DocumentService()
        self.ai_client = OpenAIClient()

    async def extract_from_file(
        self,
        file_key: str,
    ) -> ExtractedCandidateSchema:

        file_bytes = self.storage.get_file(file_key)

        resume_text = self.document_service.extract_text(
            file_name=file_key,
            file_bytes=file_bytes,
        )

        if not resume_text.strip():
            raise ValueError(
                "Could not extract text from resume"
            )

        return await self.ai_client.extract_candidate(
            resume_text
        )
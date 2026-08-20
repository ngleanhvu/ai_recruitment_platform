from pathlib import Path


PROMPT_DIR = Path(__file__).resolve().parents[2] / "prompts"


def load_resume_extraction_prompt(
    resume_text: str,
) -> str:

    prompt_path = PROMPT_DIR / "resume_extraction.txt"

    template = prompt_path.read_text(
        encoding="utf-8"
    )

    return template.replace(
        "{{resume_text}}",
        resume_text,
    )
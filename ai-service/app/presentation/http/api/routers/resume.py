from fastapi import APIRouter, File, HTTPException, UploadFile

from app.application.resume.extraction_service import ExtractionService
from app.config import get_settings
from app.shared.utils.file_utils import get_file_name, is_supported_file, validate_file_size


router = APIRouter(
    prefix="/api/v1/ai/resumes",
    tags=["Resume"],
)

extraction_service = ExtractionService()


@router.post("/extract")
async def extract_resume(
    file: UploadFile = File(...),
):
    try:
        file_name = get_file_name(file.filename)
        if not is_supported_file(file_name):
            raise ValueError("Unsupported resume file type")

        max_file_size_mb = get_settings().max_file_size_mb
        file_bytes = await file.read(max_file_size_mb * 1024 * 1024 + 1)
        validate_file_size(
            len(file_bytes),
            max_file_size_mb,
        )

        result = await extraction_service.extract_from_file(
            file_name=file_name,
            file_bytes=file_bytes,
        )

        return {
            "file_name": file_name,
            "data": result,
        }

    except ValueError as exc:

        raise HTTPException(
            status_code=400,
            detail=str(exc),
        )

    except Exception as exc:

        raise HTTPException(
            status_code=500,
            detail="Failed to extract resume",
        ) from exc

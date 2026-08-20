from fastapi import APIRouter, HTTPException

from app.services.extraction_service import ExtractionService


router = APIRouter(
    prefix="/api/v1/resumes",
    tags=["Resume"],
)

extraction_service = ExtractionService()


@router.post("/extract")
async def extract_resume(
    file_key: str,
):
    try:

        result = await extraction_service.extract_from_file(
            file_key
        )

        return {
            "file_key": file_key,
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
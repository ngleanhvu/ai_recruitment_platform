from fastapi import FastAPI

from app.api.routers.resume import router as resume_router
from app.core.config import get_settings


settings = get_settings()

app = FastAPI(
    title=settings.app_name,
    version="1.0.0",
)

app.include_router(resume_router)


@app.get("/health")
async def health():
    return {
        "status": "UP",
        "service": settings.app_name,
    }
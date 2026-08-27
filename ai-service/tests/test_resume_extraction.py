import unittest
from unittest.mock import AsyncMock, Mock

from fastapi import UploadFile
from starlette.datastructures import Headers

from app.application.resume.extraction_service import ExtractionService
from app.presentation.http import resume


class ExtractionServiceTest(unittest.IsolatedAsyncioTestCase):
    async def test_extracts_text_from_uploaded_cv_without_storage_lookup(self):
        service = ExtractionService.__new__(ExtractionService)
        service.document_service = Mock()
        service.document_service.extract_text.return_value = "synthetic resume text"
        service.ai_client = Mock()
        service.ai_client.extract_candidate = AsyncMock(return_value={"profile": {}})

        result = await service.extract_from_file("resume.pdf", b"pdf bytes")

        service.document_service.extract_text.assert_called_once_with(
            file_name="resume.pdf",
            file_bytes=b"pdf bytes",
        )
        service.ai_client.extract_candidate.assert_awaited_once_with(
            "synthetic resume text"
        )
        self.assertEqual({"profile": {}}, result)


class ResumeEndpointTest(unittest.IsolatedAsyncioTestCase):
    async def test_passes_uploaded_cv_directly_to_extraction_service(self):
        service = Mock()
        service.extract_from_file = AsyncMock(return_value={"profile": {}})
        original_service = resume.extraction_service
        resume.extraction_service = service
        try:
            upload = UploadFile(
                filename="resume.pdf",
                file=Mock(),
                headers=Headers({"content-type": "application/pdf"}),
            )
            upload.file.read = Mock(return_value=b"pdf bytes")

            response = await resume.extract_resume(upload)

            service.extract_from_file.assert_awaited_once_with(
                file_name="resume.pdf",
                file_bytes=b"pdf bytes",
            )
            self.assertEqual("resume.pdf", response["file_name"])
        finally:
            resume.extraction_service = original_service


if __name__ == "__main__":
    unittest.main()

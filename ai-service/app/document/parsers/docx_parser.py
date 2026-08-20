import io
from docx import Document

from app.document.parsers.base import DocumentParser


class DocxParser(DocumentParser):

    def supports(self, extension: str) -> bool:
        return extension == ".docx"

    def extract_text(self, file_bytes: bytes) -> str:
        document = Document(io.BytesIO(file_bytes))

        return "\n".join(
            paragraph.text
            for paragraph in document.paragraphs
        )
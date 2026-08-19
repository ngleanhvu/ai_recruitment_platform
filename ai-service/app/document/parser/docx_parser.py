from docx import Document

from app.document.parsers.base import DocumentParser
from app.utils.text_utils import normalize_text


class DocxParser(DocumentParser):

    SUPPORTED_EXTENSION = ".docx"

    def supports(self, extension: str) -> bool:
        return extension.lower() == self.SUPPORTED_EXTENSION

    def extract_text(self, file_path: str) -> str:
        document = Document(file_path)

        paragraphs = []

        for paragraph in document.paragraphs:
            text = paragraph.text.strip()

            if text:
                paragraphs.append(text)

        return normalize_text("\n".join(paragraphs))
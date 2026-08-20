import io
from pypdf import PdfReader

from app.document.parsers.base import DocumentParser


class PdfParser(DocumentParser):

    def supports(self, extension: str) -> bool:
        return extension == ".pdf"

    def extract_text(self, file_bytes: bytes) -> str:
        reader = PdfReader(io.BytesIO(file_bytes))

        text = []

        for page in reader.pages:
            page_text = page.extract_text()

            if page_text:
                text.append(page_text)

        return "\n".join(text)
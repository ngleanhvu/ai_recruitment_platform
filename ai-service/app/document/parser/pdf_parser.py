import fitz

from app.document.parsers.base import DocumentParser
from app.utils.text_utils import normalize_text


class PdfParser(DocumentParser):

    SUPPORTED_EXTENSION = ".pdf"

    def supports(self, extension: str) -> bool:
        return extension.lower() == self.SUPPORTED_EXTENSION

    def extract_text(self, file_path: str) -> str:
        document = fitz.open(file_path)

        try:
            pages = []

            for page in document:
                text = page.get_text()

                if text:
                    pages.append(text)

            return normalize_text("\n".join(pages))

        finally:
            document.close()
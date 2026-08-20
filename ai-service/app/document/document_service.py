from pathlib import Path

from app.document.parsers.base import DocumentParser
from app.document.parsers.docx_parser import DocxParser
from app.document.parsers.pdf_parser import PdfParser


class DocumentService:

    def __init__(self):
        self.parsers: list[DocumentParser] = [
            PdfParser(),
            DocxParser(),
        ]

    def extract_text(
        self,
        file_name: str,
        file_bytes: bytes,
    ) -> str:

        extension = Path(file_name).suffix.lower()

        parser = self._find_parser(extension)

        return parser.extract_text(file_bytes)

    def _find_parser(
        self,
        extension: str,
    ) -> DocumentParser:

        for parser in self.parsers:
            if parser.supports(extension):
                return parser

        raise ValueError(
            f"Unsupported file extension: {extension}"
        )
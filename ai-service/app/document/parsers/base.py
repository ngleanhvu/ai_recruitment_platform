from abc import ABC, abstractmethod


class DocumentParser(ABC):

    @abstractmethod
    def supports(self, extension: str) -> bool:
        pass

    @abstractmethod
    def extract_text(self, file_bytes: bytes) -> str:
        pass
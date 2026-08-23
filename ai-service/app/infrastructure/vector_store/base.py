from abc import ABC, abstractmethod


class VectorStore(ABC):

    @abstractmethod
    async def upsert(
        self,
        collection: str,
        id: str,
        vector: list[float],
        payload: dict,
    ):
        pass

    @abstractmethod
    async def search(
        self,
        collection: str,
        vector: list[float],
        limit: int = 10,
        filters: dict | None = None,
    ):
        pass

    @abstractmethod
    async def delete(
        self,
        collection: str,
        id: str,
    ):
        pass
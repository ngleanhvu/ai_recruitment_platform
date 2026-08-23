class ResumeChunker:

    def chunk(self, candidate: dict):

        chunks = []

        if candidate.get("summary"):
            chunks.append(...)

        for skill in candidate.get("skills", []):
            chunks.append(...)

        for experience in candidate.get("experiences", []):
            chunks.append(...)

        for education in candidate.get("educations", []):
            chunks.append(...)

        return chunks
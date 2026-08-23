import re


def normalize_text(text: str) -> str:
    if not text:
        return ""

    text = text.replace("\r\n", "\n")
    text = text.replace("\r", "\n")

    # Remove trailing spaces
    lines = [
        line.strip()
        for line in text.split("\n")
    ]

    # Remove empty lines
    lines = [
        line
        for line in lines
        if line
    ]

    return "\n".join(lines)


def normalize_whitespace(text: str) -> str:
    if not text:
        return ""

    return re.sub(r"\s+", " ", text).strip()
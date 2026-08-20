from pathlib import Path


SUPPORTED_FILE_EXTENSIONS = {
    ".pdf",
    ".doc",
    ".docx",
}


def get_file_extension(filename: str) -> str:
    if not filename:
        raise ValueError("Filename is required")

    extension = Path(filename).suffix.lower()

    if not extension:
        raise ValueError("File extension is required")

    return extension


def is_supported_file(filename: str) -> bool:
    extension = get_file_extension(filename)

    return extension in SUPPORTED_FILE_EXTENSIONS


def get_file_name(filename: str) -> str:
    if not filename:
        raise ValueError("Filename is required")

    return Path(filename).name

def validate_file_size(
    file_size: int,
    max_size_mb: int,
) -> None:
    max_size_bytes = max_size_mb * 1024 * 1024

    if file_size > max_size_bytes:
        raise ValueError(
            f"File size exceeds maximum allowed size "
            f"of {max_size_mb} MB"
        )
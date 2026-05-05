# domain/models.py

from dataclasses import dataclass


@dataclass
class ReviewRequest:
    """
    Representa una petición de análisis de código.
    """
    diff: str
    project_type: str


@dataclass
class ReviewResult:
    """
    Resultado del análisis IA.
    """
    raw_text: str
    parsed_json: dict
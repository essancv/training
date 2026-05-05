# domain/interfaces.py

from abc import ABC, abstractmethod


class LLMClient(ABC):
    """
    Interfaz para cualquier proveedor de LLM.
    Permite intercambiar Gemini, OpenAI, etc.
    """

    @abstractmethod
    def generate(self, prompt: str) -> str:
        pass


class CodeProvider(ABC):
    """
    Proveedor de código (GitHub, GitLab, etc.)
    """

    @abstractmethod
    def get_pr_diff(self, repo: str, pr_number: int) -> str:
        pass


class PRPublisher(ABC):
    def publish_comment(self, repo: str, pr_number: int, comment: str):
        pass
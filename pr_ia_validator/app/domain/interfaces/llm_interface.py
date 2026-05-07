from abc import ABC, abstractmethod


class LLMInterface(ABC):
    """
    Abstraction for any LLM provider (Ollama, Gemini, OpenAI...).

    DIP principle:
    - Application layer depends on this interface, not on concrete LLMs.
    """

    @abstractmethod
    def generate(self, prompt: str) -> str:
        """
        Executes the prompt and returns raw response.
        """
        pass
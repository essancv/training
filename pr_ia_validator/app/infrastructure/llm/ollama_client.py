import requests
from domain.interfaces.llm_interface import LLMInterface


class OllamaClient(LLMInterface):
    """
    Concrete implementation for Ollama (local LLM).

    Can be replaced by:
    - OpenAI
    - Gemini
    - Azure
    without touching application layer (DIP)
    """

    def __init__(self, model: str = "llama3"):
        self.model = model
        self.url = "http://localhost:11434/api/generate"

    def generate(self, prompt: str) -> str:

        response = requests.post(
            self.url,
            json={
                "model": self.model,
                "prompt": prompt,
                "stream": False
            }
        )

        if response.status_code != 200:
            raise Exception(f"Ollama error: {response.text}")

        return response.json()["response"]
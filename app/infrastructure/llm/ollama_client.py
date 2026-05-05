# infrastructure/llm/ollama_client.py

"""
Ollama LLM Client

Implementación del LLMClient usando Ollama local.
Compatible con modelos como:
- llama3
- mistral
- codellama
"""

import requests
from domain.interfaces import LLMClient


class OllamaClient(LLMClient):
    """
    Cliente para modelos locales en Ollama.
    """

    def __init__(self, model: str = "llama3", host: str = "http://localhost:11434"):
        self.model = model
        self.host = host

    def generate(self, prompt: str) -> str:

        url = f"{self.host}/api/generate"

        payload = {
            "model": self.model,
            "prompt": prompt,
            "stream": False
        }

        response = requests.post(url, json=payload)

        if response.status_code != 200:
            raise Exception(f"Ollama error: {response.text}")

        data = response.json()

        return data.get("response", "")
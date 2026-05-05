# infrastructure/llm/gemini_client.py

import os
import requests
from domain.interfaces import LLMClient


class GeminiClient(LLMClient):
    """
    Implementación concreta para Gemini API.
    """

    def __init__(self):
        self.api_key = os.getenv("GEMINI_API_KEY")
        print (f'Gemeni api key {self.api_key}')
        # v1 can be unsupported for some models/methods; default to v1beta.
        api_version = os.getenv("GEMINI_API_VERSION", "v1").strip()
        model = os.getenv("GEMINI_MODEL", "gemini-2.0-flash").strip()
        self.url = f"https://generativelanguage.googleapis.com/{api_version}/models/{model}:generateContent"

    def generate(self, prompt: str) -> str:

        payload = {
            "contents": [
                {"parts": [{"text": prompt}]}
            ]
        }

        response = requests.post(
            f"{self.url}?key={self.api_key}",
            json=payload
        )

        if response.status_code != 200:
            raise Exception(f"Gemini error: {response.text}")

        data = response.json()

        return data["candidates"][0]["content"]["parts"][0]["text"]

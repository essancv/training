# infrastructure/llm/llm_factory.py

from infrastructure.llm.ollama_client import OllamaClient
from infrastructure.llm.mock_client import MockLLMClient


def create_llm_client(provider: str, config: dict):

    if provider == "ollama":
        return OllamaClient(
            model=config.get("model", "llama3"),
            host=config.get("host", "http://localhost:11434")
        )

    if provider == "mock":
        return MockLLMClient()

    raise Exception(f"Unknown LLM provider: {provider}")
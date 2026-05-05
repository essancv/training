# infrastructure/llm/mock_client.py

from domain.interfaces import LLMClient


class MockLLMClient(LLMClient):
    """
    Cliente mock para pruebas sin coste.
    """

    def generate(self, prompt: str) -> str:

        return """
{
  "status": "PASS",
  "summary": "No critical issues found in the provided diff",
  "issues": [
    {
      "type": "STYLE",
      "severity": "LOW",
      "message": "Consider using consistent naming conventions",
      "file": "unknown",
      "line": 0,
      "suggestion": "Align naming with project standards"
    }
  ]
}
"""
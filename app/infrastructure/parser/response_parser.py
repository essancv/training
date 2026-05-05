# infrastructure/parser/response_parser.py

import json
import re


class ResponseParser:
    """
    Convierte texto IA en JSON válido.
    """

    def parse(self, text: str) -> dict:

        match = re.search(r"\{.*\}", text, re.DOTALL)

        if not match:
            raise Exception("No JSON found in response")

        return json.loads(match.group(0))
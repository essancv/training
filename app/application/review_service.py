# application/review_service.py

from domain.models import ReviewRequest, ReviewResult
from domain.interfaces import LLMClient


class ReviewService:
    """
    Caso de uso principal.
    Orquesta todo el flujo de análisis.
    """

    def __init__(self, llm_client, parser, validator):
        self.llm_client = llm_client
        self.parser = parser
        self.validator = validator

    def execute(self, request: ReviewRequest) -> ReviewResult:

        # 1. Construir prompt
        prompt = self._build_prompt(request)

        # 2. Llamar LLM
        raw = self.llm_client.generate(prompt)

        # 3. Parsear respuesta
        parsed = self.parser.parse(raw)

        # 4. Validar
        self.validator.validate_ai_json(parsed)

        return ReviewResult(
            raw_text=raw,
            parsed_json=parsed
        )

    def _build_prompt(self, request: ReviewRequest) -> str:

        return f"""
You are an expert software reviewer.

You MUST analyze ONLY the provided code diff. Do NOT assume any missing context. Do NOT invent code or behavior outside the diff.

Return ONLY valid JSON. Do NOT include explanations outside JSON.

--------------------------------------------------
OUTPUT FORMAT (STRICT)
--------------------------------------------------

{{
  "status": "PASS | FAIL",
  "summary": "string",
  "issues": [
    {{
      "type": "STYLE | DOCUMENTATION | DESIGN | SECURITY | TESTING",
      "severity": "LOW | MEDIUM | HIGH",
      "message": "string",
      "file": "string",
      "line": number,
      "suggestion": "string"
    }}
  ]
}}

--------------------------------------------------
ANALYSIS SCOPE
--------------------------------------------------

Analyze ONLY the modified or added code in the diff.

Do NOT:
- assume full project context
- evaluate architecture outside visible code
- suggest large refactors unrelated to the diff

--------------------------------------------------
REVIEW CRITERIA
--------------------------------------------------

1. STYLE (PEP8 or equivalent)
- Detect clear violations: naming, formatting, imports, indentation
- Focus only on obvious issues in modified code

2. DOCUMENTATION
- Every new or modified function MUST have a docstring
- Docstring MUST be in English
- Must include:
  - description
  - arguments
  - return value

3. DESIGN (SOLID + DRY)
- Detect:
  - multiple responsibilities in a function (SRP violation)
  - duplicated logic (DRY violation)
  - rigid conditionals (potential OCP issues)
- Only report clear, local issues visible in the diff

4. SECURITY
- Detect:
  - SQL injection risks (string concatenation)
  - unsafe use of user input
  - use of eval/exec
  - insecure deserialization
  - exposure of sensitive data
- Mark these as HIGH severity when applicable

5. TESTING
- For each NEW or MODIFIED function:
  - Check if there is evidence of related unit tests in the diff
  - Look for test files or test functions

IMPORTANT:
- If NO evidence of tests is found in the diff, report a TESTING issue
- BUT do NOT assume tests do not exist outside the diff
- This should usually be MEDIUM severity

--------------------------------------------------
SEVERITY RULES
--------------------------------------------------

HIGH:
- Security vulnerabilities
- Critical logic errors

MEDIUM:
- Missing tests for modified/new logic
- Missing documentation
- Design issues affecting maintainability

LOW:
- Style issues
- Minor improvements

--------------------------------------------------
STATUS RULES
--------------------------------------------------

FAIL:
- At least one HIGH issue
- OR multiple MEDIUM issues

PASS:
- No HIGH issues
- Only LOW or minor MEDIUM issues

--------------------------------------------------
QUALITY RULES
--------------------------------------------------

Each issue MUST:
- Be specific and actionable
- Reference the affected file and line if possible
- Include a concrete suggestion

Avoid:
- Generic advice
- Repeating the same issue multiple times

--------------------------------------------------
INPUT DIFF
--------------------------------------------------
{request.diff}
"""
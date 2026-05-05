# infrastructure/validator/validator.py

"""
Validator v2 - LLM Code Review Output

Valida que la respuesta del LLM:
- Tiene formato JSON correcto
- Cumple el contrato esperado
- Tiene calidad mínima para ser usable en PRs

NO valida dominio (Jira, ADS, etc.)
"""


class ValidationError(Exception):
    pass


# ==========================================================
# CONSTANTES
# ==========================================================

VALID_STATUS = {"PASS", "FAIL"}

VALID_TYPES = {
    "SECURITY",
    "STYLE",
    "DOCUMENTATION",
    "ARCHITECTURE"
}

VALID_SEVERITY = {"LOW", "MEDIUM", "HIGH"}


# ==========================================================
# VALIDACIÓN PRINCIPAL
# ==========================================================

def validate_ai_json(data: dict) -> bool:
    """
    Valida la salida del LLM para code review.
    """

    if not isinstance(data, dict):
        raise ValidationError("La respuesta no es un JSON válido")

    # ------------------------------------------------------
    # status
    # ------------------------------------------------------
    status = data.get("status")
    if status not in VALID_STATUS:
        raise ValidationError(f"status inválido: {status}")

    # ------------------------------------------------------
    # summary
    # ------------------------------------------------------
    summary = data.get("summary")
    if not isinstance(summary, str) or not summary.strip():
        raise ValidationError("summary vacío o inválido")

    # ------------------------------------------------------
    # issues
    # ------------------------------------------------------
    issues = data.get("issues")

    if not isinstance(issues, list):
        raise ValidationError("issues debe ser una lista")

    for i, issue in enumerate(issues):

        if not isinstance(issue, dict):
            raise ValidationError(f"issue[{i}] no es un objeto")

        _validate_issue(issue, i)

    # ------------------------------------------------------
    # coherencia básica
    # ------------------------------------------------------
    if status == "PASS" and issues:
        # no debería haber issues graves si pasa
        high_issues = [
            i for i in issues if i.get("severity") == "HIGH"
        ]
        if high_issues:
            raise ValidationError(
                "status PASS pero hay issues HIGH"
            )

    print("✅ Validación LLM OK")

    return True


# ==========================================================
# VALIDACIÓN DE ISSUE
# ==========================================================

def _validate_issue(issue: dict, index: int):

    # type
    issue_type = issue.get("type")
    if issue_type not in VALID_TYPES:
        raise ValidationError(
            f"issue[{index}].type inválido: {issue_type}"
        )

    # severity
    severity = issue.get("severity")
    if severity not in VALID_SEVERITY:
        raise ValidationError(
            f"issue[{index}].severity inválido: {severity}"
        )

    # message
    message = issue.get("message")
    if not isinstance(message, str) or not message.strip():
        raise ValidationError(
            f"issue[{index}].message vacío"
        )

    # file (opcional pero recomendado)
    file = issue.get("file")
    if file is not None and not isinstance(file, str):
        raise ValidationError(
            f"issue[{index}].file inválido"
        )

    # line (opcional)
    line = issue.get("line")
    if line is not None and not isinstance(line, int):
        raise ValidationError(
            f"issue[{index}].line inválido"
        )

    # suggestion
    suggestion = issue.get("suggestion")
    if suggestion is not None and not isinstance(suggestion, str):
        raise ValidationError(
            f"issue[{index}].suggestion inválido"
        )
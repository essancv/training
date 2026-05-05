# infrastructure/code/pr_comment_formatter.py

"""
Convierte el resultado del análisis en comentario Markdown
"""

def format_comment(result: dict) -> str:

    status = result.get("status")
    summary = result.get("summary")
    issues = result.get("issues", [])

    emoji = "✅" if status == "PASS" else "❌"

    comment = f"# {emoji} AI Code Review Result\n\n"
    comment += f"**Status:** {status}\n\n"
    comment += f"**Summary:** {summary}\n\n"

    if not issues:
        comment += "🎉 No issues found\n"
        return comment

    comment += "## Issues\n"

    for issue in issues:
        comment += f"- **{issue.get('type')} [{issue.get('severity')}]**\n"
        comment += f"  - {issue.get('message')}\n"

        if issue.get("file"):
            comment += f"  - File: `{issue.get('file')}`\n"

        if issue.get("line"):
            comment += f"  - Line: {issue.get('line')}\n"

        if issue.get("suggestion"):
            comment += f"  - 💡 {issue.get('suggestion')}\n"

        comment += "\n"

    return comment
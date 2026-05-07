from domain.models.review_issue_models import ParsedReview


class ReviewFormatterService:
    """
    Builds GitHub markdown review comments.

    DRY:
    - Centralized markdown generation
    """

    def format(self, review: ParsedReview) -> str:

        if not review.issues:
            return "✅ No issues detected."

        lines = []

        lines.append("# 🤖 AI Code Review")
        lines.append("")

        for issue in review.issues:

            lines.append(f"## {issue.severity}")
            lines.append(f"**File:** `{issue.file}`")
            lines.append("")
            lines.append(f"### Explanation")
            lines.append(issue.explanation)
            lines.append("")
            lines.append(f"### Recommendation")
            lines.append(issue.recommendation)
            lines.append("")
            lines.append("---")
            lines.append("")

        return "\n".join(lines)
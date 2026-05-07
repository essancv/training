from fastapi import FastAPI

from domain.interfaces.web.github_webhook_controller import (
    GitHubWebhookController
)

from application.use_cases.trigger_pr_review import (
    TriggerPRReview
)


def create_app(trigger_use_case):
    """
    Application bootstrap.

    CLEAN:
    - no business logic
    - only wiring
    """

    app = FastAPI(
        title="PR AI Validator",
        version="1.0.0"
    )

    controller = GitHubWebhookController(
        trigger_use_case=trigger_use_case
    )

    app.include_router(controller.router)

    return app
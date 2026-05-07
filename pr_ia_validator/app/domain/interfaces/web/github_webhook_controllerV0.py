from fastapi import APIRouter, Request



class GitHubWebhookController:
    """
    HTTP layer adapter.

    RESPONSIBILITY:
    - Receive GitHub webhook
    - Extract PR info
    - Call application use case

    CLEAN:
    - NO business logic
    """
    router = APIRouter()

    def __init__(self, trigger_use_case):
        self.trigger_use_case = trigger_use_case

    @router.post("/webhook/github")
    async def handle_webhook(self, request: Request):
        """
        GitHub webhook entrypoint.
        """

        payload = await request.json()

        # ==========================================
        # EXTRACT EVENT TYPE
        # ==========================================

        event_type = request.headers.get("X-GitHub-Event")

        if event_type != "pull_request":
            return {"status": "ignored"}

        action = payload.get("action")

        # Only trigger on relevant events
        if action not in ["opened", "synchronize", "reopened"]:
            return {"status": "ignored"}

        # ==========================================
        # EXTRACT PR ID
        # ==========================================

        pr_id = payload["pull_request"]["number"]

        # ==========================================
        # TRIGGER USE CASE
        # ==========================================

        result = self.trigger_use_case.execute(pr_id)

        return {
            "status": "processed",
            "blocked": result.blocked
        }
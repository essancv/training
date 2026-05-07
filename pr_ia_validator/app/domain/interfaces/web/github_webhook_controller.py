from fastapi import APIRouter, Request

router = APIRouter()


def create_github_webhook_controller(trigger_use_case):

    @router.post("/webhook/github")
    async def handle_webhook(request: Request):

        payload = await request.json()

        event_type = request.headers.get("X-GitHub-Event")

        if event_type != "pull_request":
            return {"status": "ignored"}

        action = payload.get("action")

        if action not in ["opened", "synchronize", "reopened"]:
            return {"status": "ignored"}

        pr_id = payload["pull_request"]["number"]

        result = trigger_use_case.execute(pr_id)

        return {
            "status": "processed",
            "blocked": result.blocked
        }

    return router
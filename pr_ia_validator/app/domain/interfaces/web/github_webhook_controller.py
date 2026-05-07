from fastapi import APIRouter, Request

from domain.models.pullrequest_context import PullRequestContext

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

        print ("WEbhook payload ==============================")
        print (f"PR number : {pr_id}")
        print (f"Repo name : { payload["repository"]["full_name"]}")
        print ("==============================")
        pr_context = PullRequestContext (
            pr_id ,
            payload["repository"]["full_name"] ,
            payload["repository"]["owner"]["login"] ,
            payload["pull_request"]["user"]["login"] ,
            payload["pull_request"]["head"]["repo"]["full_name"]
        )
  
        result = trigger_use_case.execute(pr_id)

        return {
            "status": "processed",
            "blocked": result.blocked
        }

    return router
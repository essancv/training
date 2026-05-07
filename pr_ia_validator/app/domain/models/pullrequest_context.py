from dataclasses import dataclass

@dataclass
class PullRequestContext:
    pr_id: int
    repo_full_name: str
    repo_owner: str
    author: str
    branch: str
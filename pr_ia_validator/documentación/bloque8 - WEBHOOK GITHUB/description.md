app/
├── interfaces/
│   └── web/
│       └── github_webhook_controller.py   👈 ENTRYPOINT HTTP
│
├── application/
│   └── use_cases/
│       └── trigger_pr_review.py           👈 ORQUESTADOR
│
└── infrastructure/
    └── web/
        └── fastapi_app.py                 👈 SERVER

flowchart TD

A[GitHub PR Event]
--> B[Webhook Controller]

B --> C[TriggerPRReview UseCase]

C --> D[ReviewPullRequest]
D --> E[Policy Engine]
E --> F[LLM]

F --> G[PublishReviewResult]

G --> H[GitHub Comment]
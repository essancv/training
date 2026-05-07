app/
├── domain/
│   ├── interfaces/
│   │   └── llm_interface.py
│   └── models/
│       └── review_models.py
│
├── application/
│   └── use_cases/
│       └── review_pull_request.py
│
└── infrastructure/
    └── llm/
        └── ollama_client.py   (ya lo tienes, lo alineamos)


```mermaid

flowchart TD

A[PR ID] --> B[BuildDiffContext]

B --> C[DiffContext]

C --> D[PolicyEngine]

D -->|BLOCK| E[Stop]

D -->|OK| F[PromptBuilder]

F --> G{Mode}

G -->|Batch| H[Single LLM Call]
G -->|Per File| I[Multiple Calls]

H --> J[Result]
I --> J

```
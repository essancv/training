app/
├── domain/
│   ├── models/
│   │   └── policy_models.py
│   │
│   ├── services/
│   │   ├── policy_engine.py
│   │   └── rules/
│   │       ├── base_rule.py
│   │       ├── test_rule.py
│   │       └── naming_rule.py
│
├── application/
│   └── use_cases/
│       └── evaluate_policy.py
│
└── config/
    └── settings.py   (ya existente)

```mermaid

flowchart TD

A[SCM Files] --> B[DiffContext Builder]

B --> C[Map expected test]
B --> D[Search test in PR files]

D --> E[DiffGroup]

E --> F[Policy Engine]
E --> G[LLM Input Builder]

```
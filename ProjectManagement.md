# Fase inicial del proyecto

| Artefacto (acrónimo) | Descripción breve                              |   SMALL |  MEDIUM | COMPLEX | Destino principal |
| -------------------- | ---------------------------------------------- | ------: | ------: | ------: | ----------------- |
| **OVR**              | Overview funcional / alcance                   | **OBL** | **OBL** | **OBL** | GitHub            |
| **REQMAP**           | Mapa requisitos → artefactos / trazabilidad    |     OPT | **OBL** | **OBL** | GitHub            |
| **EPIC**             | Épicas Jira                                    | **OBL** | **OBL** | **OBL** | Jira              |
| **STORY**            | Historias de usuario                           | **OBL** | **OBL** | **OBL** | Jira              |
| **TASK**             | Tareas / subtareas                             |     OPT | **OBL** | **OBL** | Jira              |
| **SPIKE**            | Investigación / duda técnica                   |     OPT | **OBL** | **OBL** | Jira              |
| **RISK**             | Riesgos identificados                          |     OPT |     OPT | **OBL** | Jira / GitHub     |
| **FLOW**             | Flujos de negocio Mermaid                      | **OBL** | **OBL** | **OBL** | GitHub            |
| **ARCH-L1**          | Arquitectura nivel 1 (componentes principales) |     OPT | **OBL** | **OBL** | GitHub            |
| **ARCH-L2**          | Arquitectura detallada (integraciones/capas)   |       — |     OPT | **OBL** | GitHub            |
| **API**              | Diseño inicial APIs / contratos                |     OPT | **OBL** | **OBL** | GitHub            |
| **DATA**             | Modelo datos inicial                           |     OPT | **OBL** | **OBL** | GitHub            |
| **SEC**              | Seguridad / auth / permisos                    |       — |     OPT | **OBL** | GitHub            |
| **DEP**              | Dependencias externas/internas                 |       — |     OPT | **OBL** | Jira / GitHub     |
| **ADR**              | Architecture Decision Records                  |       — |     OPT | **OBL** | GitHub            |
| **OPS**              | Operación / despliegue / runbook inicial       |       — |     OPT | **OBL** | GitHub            |
| **NFR**              | Requisitos no funcionales                      |     OPT | **OBL** | **OBL** | GitHub            |
| **MILE**             | Hitos / roadmap                                |       — |     OPT | **OBL** | Jira              |
| **ENAB**             | Enablers técnicos                              |       — |     OPT | **OBL** | Jira              |
| **TEST**             | Estrategia inicial pruebas                     |     OPT | **OBL** | **OBL** | GitHub / Jira     |
| **OPENQ**            | Preguntas abiertas                             | **OBL** | **OBL** | **OBL** | GitHub / Jira     |

# Leyenda
OBL = Obligatorio
OPT = Opcional (si aplica)
— = No recomendado normalmente

 ## Artefactos de Delivery & Quality

| Acrónimo          | Nombre                                             | Tipo                   | Momento natural            |
| ----------------- | -------------------------------------------------- | ---------------------- | -------------------------- |
| **TDD**           | Test Driven Development specs/tests                | Calidad técnica        | Desarrollo                 |
| **SBE**           | Specification by Example                           | Refinamiento funcional | Análisis / Story detailing |
| **ATDD**          | Acceptance Test Driven Development                 | Validación negocio     | Antes de implementar       |
| **DoD**           | Definition of Done                                 | Gobernanza entrega     | Global proyecto            |
| **DoR** *(añado)* | Definition of Ready                                | Preparación backlog    | Refinamiento               |
| **DoC**           | Definition of Code Complete / Completion Criteria* | Cierre técnico         | Dev / QA                   |

## Asociados a los tipos de proyectos

| Artefacto      | SMALL | MEDIUM | COMPLEX | Destino principal   |
| -------------- | ----: | -----: | ------: | ------------------- |
| **DoD**        |   OBL |    OBL |     OBL | GitHub / Wiki       |
| **DoR**        |   OPT |    OBL |     OBL | GitHub / Jira       |
| **SBE**        |   OPT |    OBL |     OBL | GitHub / Jira Story |
| **ATDD**       |   OPT |    OBL |     OBL | GitHub / Jira       |
| **TDD**        |   OPT |    OBL |     OBL | Repo código         |
| **DoC**        |   OPT |    OPT |     OBL | GitHub / Wiki       |
| **TEST-STRAT** |   OPT |    OBL |     OBL | GitHub              |

## Estructura de github

repository-root/
│
├── README.md
├── CONTRIBUTING.md
├── .gitignore
│
├── docs/
│   │
│   ├── 00-governance/
│   │   ├── definition-of-done.md          # DoD
│   │   ├── definition-of-ready.md         # DoR
│   │   ├── definition-of-complete.md      # DoC
│   │   ├── working-agreements.md
│   │   ├── coding-standards.md
│   │   └── branching-strategy.md
│   │
│   ├── 01-discovery/
│   │   ├── overview.md                    # OVR
│   │   ├── scope.md
│   │   ├── assumptions.md
│   │   ├── stakeholders.md
│   │   ├── requirements-map.md           # REQMAP
│   │   └── glossary.md
│   │
│   ├── 02-architecture/
│   │   ├── overview.md                   # ARCH-L1
│   │   ├── logical-view.md
│   │   ├── physical-view.md
│   │   ├── integrations.md              # DEP
│   │   ├── security.md                  # SEC
│   │   ├── deployment.md                # OPS
│   │   ├── scalability.md
│   │   └── resilience.md
│   │
│   ├── 03-decisions/
│   │   ├── ADR-001-template.md
│   │   ├── ADR-002-auth-strategy.md
│   │   ├── ADR-003-event-bus.md
│   │   └── README.md
│   │
│   ├── 04-business-flows/
│   │   ├── sales-flow.mmd               # Mermaid
│   │   ├── onboarding-flow.mmd
│   │   ├── approval-flow.mmd
│   │   └── README.md
│   │
│   ├── 05-functional-spec/
│   │   ├── user-journeys.md
│   │   ├── business-rules.md
│   │   ├── personas.md
│   │   └── acceptance-model.md
│   │
│   ├── 06-technical-design/
│   │   ├── api-design.md               # API
│   │   ├── api-contracts/
│   │   │   ├── openapi.yaml
│   │   │   └── endpoints.md
│   │   ├── data-model.md              # DATA
│   │   ├── events.md
│   │   ├── batch-jobs.md
│   │   └── integrations-detail.md
│   │
│   ├── 07-backlog-support/
│   │   ├── epic-map.md
│   │   ├── story-map.md
│   │   ├── milestones.md              # MILE
│   │   ├── dependencies.md            # DEP
│   │   └── priorities.md
│   │
│   ├── 08-risks-questions/
│   │   ├── risks.md                   # RISK
│   │   ├── mitigations.md
│   │   ├── open-questions.md          # OPENQ
│   │   └── spikes-catalog.md          # SPIKE
│   │
│   ├── 09-quality/
│   │   ├── test-strategy.md           # TEST-STRAT
│   │   ├── non-functional-tests.md
│   │   ├── performance-tests.md
│   │   ├── security-tests.md
│   │   └── regression-model.md
│   │
│   ├── 10-specifications/
│   │   ├── sbe/                       # Specification by Example
│   │   │   ├── sales-registration.md
│   │   │   └── refund-process.md
│   │   │
│   │   ├── bdd/                       # Behavior Driven Development
│   │   │   ├── sales.feature
│   │   │   ├── login.feature
│   │   │   └── refunds.feature
│   │   │
│   │   ├── atdd/                      # Acceptance Test Driven Dev
│   │   │   ├── acceptance-sales.md
│   │   │   └── acceptance-login.md
│   │   │
│   │   └── examples/
│   │       └── canonical-cases.md
│   │
│   ├── 11-dev-guides/
│   │   ├── local-setup.md
│   │   ├── environments.md
│   │   ├── ci-cd.md
│   │   └── release-process.md
│   │
│   └── 12-operations/
│       ├── runbook.md
│       ├── monitoring.md
│       ├── alerts.md
│       ├── support-model.md
│       └── disaster-recovery.md
│
├── tests/
│   │
│   ├── unit/                          # TDD
│   ├── integration/
│   ├── e2e/
│   ├── performance/
│   ├── security/
│   └── fixtures/
│
├── scripts/
│   ├── jira_import.py
│   ├── github_import.py
│   ├── json_validator.py
│   └── generate_docs.py
│
├── prompts/
│   ├── prompt_small.txt
│   ├── prompt_medium.txt
│   ├── prompt_complex.txt
│   └── prompt_enterprise.txt
│
└── ai-output/
    ├── raw/
    ├── validated/
    └── archived/


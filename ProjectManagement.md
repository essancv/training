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


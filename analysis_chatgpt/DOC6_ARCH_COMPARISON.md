# Architecture Comparison Matrix

## 1. Matriz comparativa
| Arquitectura | Complejidad | Stack | Coste | Riesgo | Adecuación RNF |
|--------------|------------|-------|-------|--------|-----------------|
| Monolito modular | Media | Spring Boot | Bajo | Bajo | Alta |
| Microservicios | Alta | Kubernetes + Kafka | Alto | Alto | Media |
| Serverless | Alta | Cloud functions | Medio-Alto | Medio | Media |

## 2. Recomendación
Se recomienda Monolito Modular debido a:
- Control de costes IA
- Menor complejidad operativa
- Menor riesgo de sobreingeniería
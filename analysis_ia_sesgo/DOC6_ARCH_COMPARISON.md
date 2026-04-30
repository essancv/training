# Architecture Comparison Matrix
## 1. Matriz comparativa
| Arquitectura | Complejidad | Stack | Coste | Riesgo | Adecuación RNF |
| --- | --- | --- | --- | --- | --- |
| ARCH-OPT-001 Monolito modular 3-tier | Media | Spring Boot, React, PostgreSQL, Docker en VM | Media | Bajo-Medio (riesgo de crecimiento del monolito) | Alta (rendimiento, seguridad, observabilidad, costes) |

*Nota:* No se propone una opción de microservicios ni serverless por las guardrails y el tamaño MEDIUM del proyecto.

## 2. Recomendación razonada
ARCH-OPT-001 ofrece el mejor equilibrio entre simplicidad, coste y cumplimiento de NFR. Permite modularidad interna, control de costes y observabilidad sin introducir la complejidad operativa de arquitecturas distribuidas. Se recomienda adoptarla como base, dejando abierta una futura evolución hacia servicios separados solo si el volumen de tráfico, la diversidad tecnológica o los requisitos de aislamiento lo justifican.

# Architecture Comparison Matrix

## 1. Matriz comparativa
| Arquitectura | Complejidad | Stack | Coste | Riesgo | Adecuación RNF |
|---|---|---|---|---|---|
| ARCH-OPT-001 | Bajo Medio | Github Actions mas orquestador | Bajo | Medio | Alta para MEDIUM |
| ARCH-OPT-002 | Medio | Servicio API central mas Actions | Medio | Medio | Alta con mejor gobierno |

## 2. Recomendación razonada
- Para un proyecto MEDIUM con equipo pequeno y objetivo de estandarizar rapido se recomienda ARCH-OPT-001.
- Se sugiere definir criterios de evolucion a ARCH-OPT-002 como numero de repos analizados volumen de PR y necesidad de historico.
- Se evita microservicios o plataformas complejas por riesgo de sobredimensionamiento.

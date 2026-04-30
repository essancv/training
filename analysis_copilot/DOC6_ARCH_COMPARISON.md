# Architecture Comparison Matrix
## 1. Matriz comparativa
| Arquitectura | Complejidad | Stack | Coste | Riesgo | Adecuacion RNF |
| ARCH OPT 001 Monolito modular tres capas | Medio bajo | Java Spring Boot API REST PostgreSQL Docker | Bajo medio | Bajo medio | Alta para escalabilidad mantenibilidad y seguridad |
| ARCH OPT 002 Monolito hexagonal | Medio | Java Spring Boot arquitectura hexagonal PostgreSQL Docker | Medio | Medio | Alta para mantenibilidad extensibilidad y pruebas |

## 2. Recomendacion razonada
- Se recomienda ARCH OPT 001 como opcion principal por su menor complejidad operativa y coste manteniendo capacidad de cumplir los requisitos no funcionales.
- ARCH OPT 002 puede considerarse como evolucion futura cuando el producto madure y se requiera mayor extensibilidad y claridad de dominio.

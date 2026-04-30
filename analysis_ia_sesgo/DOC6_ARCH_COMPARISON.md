# Architecture Comparison Matrix

## 1. Matriz comparativa

| Arquitectura | Complejidad | Stack | Coste | Riesgo | Adecuación RNF |
| --- | --- | --- | --- | --- | --- |
| ARCH-OPT-001 – Monolito modular Python/FastAPI | Baja-media | Backend Python/FastAPI, DB ligera (SQLite/PostgreSQL), script Python | Bajo | Bajo-medio (pocos componentes, dependencia de IA y APIs) | Alta (cumple NFR-001, NFR-002, NFR-003, NFR-006) |
| ARCH-OPT-002 – Monolito Clean Architecture Python/FastAPI | Media | Igual que ARCH-OPT-001, con capas de dominio y adaptadores más definidos | Medio | Medio (mayor esfuerzo de diseño, pero mejor mantenibilidad) | Muy alta (mejor extensibilidad y testabilidad) |
| ARCH-OPT-003 – SPA React + API REST Python/FastAPI | Media | Frontend React, Backend Python/FastAPI, DB ligera | Medio | Medio (más componentes, más superficie de fallo en frontend) | Media-alta (mejor UX, pero más complejidad) |

## 2. Recomendación razonada

Considerando el tamaño MEDIUM del proyecto, el número limitado de usuarios, la dependencia de IA y la necesidad explícita de evitar sobredimensionamiento, la opción más adecuada es **ARCH-OPT-001 – Monolito modular Python/FastAPI**, complementada con buenas prácticas de diseño que acerquen el modelo a Clean Architecture sin introducir complejidad innecesaria.

ARCH-OPT-002 es una evolución natural si se prevé una vida útil larga y múltiples extensiones, mientras que ARCH-OPT-003 solo se justifica si se requiere una experiencia de usuario web más rica. Todas las opciones mantienen una pila tecnológica simple y madura, evitando stacks complejos como Kubernetes, Kafka o microservicios, en línea con los guardrails establecidos.

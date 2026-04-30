# Architecture Comparison Matrix

## 1. Matriz comparativa

| Arquitectura | Escalabilidad | Coste | Complejidad | Seguridad | Integraciones | Time-to-market | Adecuación RNF |
| --- | --- | --- | --- | --- | --- | --- | --- |
| ARCH-OPT-001 – Monolito modular en capas | Media (escalado vertical, posible replicación) | Bajo | Baja-media | Buena (pocos puntos de fallo, control centralizado) | Buena (conectores en un solo servicio) | Rápido (implementación directa) | Alta (NFR-001, NFR-002, NFR-005, NFR-006) |
| ARCH-OPT-002 – Monolito Clean/Hexagonal | Media (similar a ARCH-OPT-001) | Medio | Media | Muy buena (separación clara de capas y adaptadores) | Muy buena (adaptadores reemplazables) | Medio (más diseño inicial) | Muy alta (extensibilidad y mantenibilidad) |
| ARCH-OPT-003 – Microservicios | Alta (escalado independiente) | Alto | Alta | Variable (más superficie de ataque, requiere disciplina) | Alta pero compleja (APIs entre servicios) | Lento (mayor esfuerzo de diseño y DevOps) | Baja para RNF-006 (simplicidad), potencialmente excesiva para NFR actuales |

## 2. Recomendación razonada

Considerando el tamaño MEDIUM del proyecto, la complejidad funcional moderada, la ausencia de requisitos de escalabilidad extrema y la necesidad explícita de evitar sobredimensionamiento, la opción más adecuada es **ARCH-OPT-001 – Monolito modular en capas**, con la posibilidad de aplicar principios de Clean/Hexagonal (ARCH-OPT-002) de forma incremental.

ARCH-OPT-001 ofrece un equilibrio óptimo entre simplicidad, coste y capacidad de integración con IA, GitHub y Jira, cumpliendo los RNF de rendimiento, seguridad y extensibilidad sin introducir complejidad innecesaria. ARCH-OPT-003 se considera sobredimensionada para el contexto actual y solo debería contemplarse si, en el futuro, el sistema se convierte en una plataforma de uso masivo con múltiples equipos y cargas altamente variables.

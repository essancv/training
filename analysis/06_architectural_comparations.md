# Architecture Comparison Matrix

## 1. Matriz comparativa
| Arquitectura | Escalabilidad | Coste | Complejidad | Seguridad | Integraciones | Time-to-market | Adecuación RNF |
|--------------|---------------|-------|--------------|-----------|---------------|----------------|-----------------|
| Monolito Modular | Media | Bajo | Baja | Media | Media | Alto | Media |
| Microservices Event-Driven | Muy Alta | Alto | Alta | Alta | Muy Alta | Medio | Muy Alta |
| Hexagonal | Alta | Medio | Media-Alta | Alta | Alta | Medio-Alto | Alta |
| Serverless Event-Driven | Alta | Variable | Media | Alta | Alta | Alto | Alta |
| Híbrida Hexagonal + Event-Driven | Muy Alta | Medio-Alto | Alta | Muy Alta | Muy Alta | Medio | Muy Alta |

## 2. Recomendación razonada

El análisis comparativo de las arquitecturas propuestas muestra que el sistema pertenece claramente a una categoría **COMPLEX (DOC4)** debido a la combinación de IA externa, CI/CD, observabilidad, control de costes y múltiples integraciones críticas.

Las opciones más equilibradas son:

- **Arquitectura Hexagonal (ARCH-OPT-003)**: ofrece el mejor equilibrio entre mantenibilidad, desacoplamiento y control del dominio de negocio. Es especialmente adecuada para estabilizar el core de análisis (DOMAIN-001).

- **Arquitectura Híbrida Hexagonal + Event-Driven (ARCH-OPT-005)**: representa la opción más completa a nivel de escalabilidad y evolución futura, combinando un núcleo robusto con capacidades event-driven para integración con GitHub y CI/CD.

Las opciones puramente monolíticas presentan limitaciones claras en escalabilidad y evolución, mientras que las puramente event-driven o serverless introducen complejidad operativa elevada para el estado actual del sistema.

Desde la perspectiva estricta de requisitos no funcionales (RNF), la arquitectura híbrida ofrece la mayor cobertura global, especialmente en escalabilidad, trazabilidad y extensibilidad.
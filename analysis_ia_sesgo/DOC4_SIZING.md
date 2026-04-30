# Project Sizing Report
## 1. Introducción
Este informe estima el tamaño del proyecto considerando complejidad funcional, integración, NFR y uso de IA, para orientar la arquitectura y el backlog.

## 2. Tabla de criterios
| Criterio | Valor | Justificación | Impacto |
| --- | --- | --- | --- |
| Número de dominios funcionales | 4 | Core análisis, integración Git/CI, configuración/costes, feedback/observabilidad | Medio |
| Número de flujos clave | 4 | Flujos bien definidos pero acotados | Medio |
| Complejidad de IA | Alta | Integración con proveedor externo, prompts modulares, explicabilidad | Alto |
| Integraciones externas | 3 | GitHub, CI/CD, proveedor de IA | Medio |
| NFR críticos | 5 | Rendimiento, disponibilidad, seguridad, costes, trazabilidad | Alto |
| Alcance tecnológico inicial | Limitado | Java Spring Boot inicialmente, extensible a futuro | Medio |

## 3. Clasificación final
- Clasificación propuesta: MEDIUM.
- Justificación: El número de dominios y flujos es moderado, pero la integración con IA y los requisitos de seguridad, rendimiento y control de costes elevan la complejidad por encima de un proyecto SMALL. Sin embargo, el alcance funcional sigue siendo acotado y centrado en un caso de uso principal (análisis de PR).

## 4. Observaciones
- La clasificación MEDIUM implica priorizar una arquitectura monolítica modular y evitar microservicios o infraestructuras sobredimensionadas.
- La extensibilidad tecnológica debe planificarse como roadmap incremental, no como requisito de primera versión.

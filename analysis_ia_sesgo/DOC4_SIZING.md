# Project Sizing Report

## 1. Introducción

Este informe estima el tamaño y la complejidad del proyecto en función de los dominios funcionales identificados, los requisitos funcionales y no funcionales, y el alcance descrito. La clasificación resultante se utilizará para seleccionar opciones de arquitectura y pila tecnológica acordes con los guardrails de simplicidad.

## 2. Tabla de criterios

| Criterio | Valor | Justificación | Impacto |
| --- | --- | --- | --- |
| Número de dominios funcionales | 6 | DOMAIN-001 a DOMAIN-006 cubren captura, prompt, IA, JSON, integraciones y configuración/seguridad. | Complejidad funcional moderada. |
| Integraciones externas | 3 | Proveedor de IA, GitHub, Jira. | Aumenta complejidad técnica y riesgos de integración. |
| Volumen de usuarios | Bajo-medio | Principalmente analistas, POs y algunos desarrolladores. | No requiere alta escalabilidad. |
| Frecuencia de uso | Media | Uso por proyecto o iteración, no continuo masivo. | Carga predecible. |
| Volumen de datos por ejecución | Bajo | Un conjunto de requisitos y un JSON de análisis por ejecución. | No requiere almacenamiento masivo. |
| Complejidad de lógica de negocio | Media | Validaciones, trazabilidad, ensamblado de JSON, integración con APIs. | Justifica modularidad, no arquitecturas distribuidas complejas. |
| Dependencia de IA | Alta | El valor depende de la calidad del modelo y del prompt manual. | Requiere robustez en manejo de errores y validación de resultados. |
| Requisitos de rendimiento | Moderados | Respuesta en < 1–2 minutos aceptable. | No se requiere escalado extremo. |
| Tamaño del equipo esperado | 3–6 devs | Proyecto típico de herramienta interna o producto especializado. | No justifica equipos grandes ni microservicios. |
| Horizonte temporal | Medio | Producto con evolución, pero no plataforma masiva. | Arquitectura extensible pero simple. |

## 3. Clasificación final

- Clasificación de tamaño global: MEDIUM (baja).
- Complejidad funcional: Media.
- Complejidad técnica: Media (por integraciones y uso de IA, pero con volumen de uso limitado).

## 4. Observaciones

- El proyecto tiene una complejidad suficiente para requerir una arquitectura modular clara, pero no presenta requisitos de escalabilidad extrema ni equipos grandes.
- La principal fuente de complejidad reside en la correcta definición del esquema JSON, la trazabilidad y la robustez frente a errores de IA y APIs externas.
- La clasificación MEDIUM respalda la elección de una arquitectura monolítica modular o en capas, con una pila tecnológica simple y madura.

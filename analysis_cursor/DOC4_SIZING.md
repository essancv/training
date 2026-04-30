# Project Sizing Report

## 1. Introducción
La estimacion se realiza a nivel inicial para un equipo de 5 desarrolladores en un proyecto de integracion GitHub Actions mas servicio de analisis con LLM y herramientas de calidad.

## 2. Tabla de criterios
| Criterio | Valor | Justificación | Impacto |
|---|---|---|---|
| Integraciones externas | Media | GitHub API y proveedor LLM y posibles herramientas SAST | Aumenta riesgo y pruebas |
| Complejidad funcional | Media | Orquestacion prompts reporte idempotencia y configuracion por repo | Requiere diseño cuidadoso |
| RNF seguridad y privacidad | Alta | Redaccion de secretos y control de datos hacia LLM | Aumenta esfuerzo |
| Variabilidad por tecnologia | Media | Reglas Java Spring Boot y React mas extensible | Modularidad necesaria |
| Operacion y observabilidad | Media | Logs metricas auditoria rate limit | Trabajo adicional |
| UI propia | Baja | No se requiere UI completa si se usa comentarios en PR | Reduce esfuerzo |

## 3. Clasificación final
MEDIUM

## 4. Observaciones
- Se recomienda iniciar con un MVP centrado en Java Spring Boot y diffs moderados.
- Minimizar alcance para evitar sobredimensionamiento.
- Usar herramientas deterministas para estilo y seguridad y reservar LLM para analisis contextual.

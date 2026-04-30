# Story Point Costing (DOC10)
## 1. Relación entre sizing (horas) y Story Points
- 1 SP ≈ 4 horas de trabajo efectivo.
- 2 SP ≈ 1 día.
- 3 SP ≈ 1.5 días.
- 5 SP ≈ 2.5 días.
- 8 SP ≈ 1 semana.

## 2. Estimación por historia
### STORY-001 Análisis automático de PR en GitHub
- Horas estimadas: 40 horas.
- SP estimados: 10 SP (aprox. 1.25 semanas, incluye integración GitHub y CI/CD básica).
- Justificación: Implementación de flujo completo FLOW-001, integración con webhooks, IA y comentarios en PR.
- Complejidad técnica: Alta (integración externa, IA, rendimiento).
- Dependencias: DEP-001, DEP-002, RISK-002.

### STORY-002 Informe detallado de análisis
- Horas estimadas: 24 horas.
- SP estimados: 6 SP.
- Justificación: Diseño de formato, generación y presentación de informe.
- Complejidad técnica: Media.
- Dependencias: STORY-001.

### STORY-003 Análisis vía API REST
- Horas estimadas: 24 horas.
- SP estimados: 6 SP.
- Justificación: Endpoints REST, validación de API key, integración con motor de análisis.
- Complejidad técnica: Media.
- Dependencias: infraestructura de backend.

### STORY-004 Configuración de límites de coste por repositorio
- Horas estimadas: 32 horas.
- SP estimados: 8 SP.
- Justificación: Modelo de configuración, UI básica, lógica de validación en FLOW-004.
- Complejidad técnica: Media-Alta.
- Dependencias: DOMAIN-003, RISK-003.

### STORY-005 Registro de feedback sobre resultados
- Horas estimadas: 16 horas.
- SP estimados: 4 SP.
- Justificación: UI simple, almacenamiento de feedback, asociación a análisis.
- Complejidad técnica: Media.
- Dependencias: DOMAIN-004.

### STORY-006 Observabilidad de análisis
- Horas estimadas: 24 horas.
- SP estimados: 6 SP.
- Justificación: Métricas, logs, paneles básicos.
- Complejidad técnica: Media.
- Dependencias: DEP-003.

## 3. Coste total del backlog
- Total SP inicial (STORY-001 a STORY-006): 40 SP.
- Interpretación: Aproximadamente 4 a 5 semanas de trabajo de un equipo pequeño, considerando solapamientos y tareas técnicas adicionales.

## 4. Observaciones y riesgos
- Las estimaciones son iniciales y deben refinarse tras el diseño detallado.
- Riesgos principales: integración con IA (RISK-001), rendimiento en PR grandes (RISK-002) y control de costes (RISK-003).

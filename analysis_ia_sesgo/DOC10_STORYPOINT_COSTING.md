# Story Point Costing (DOC10)

## 1. Relación entre sizing (horas) y Story Points
- **Explicación del modelo:**
  - Se adopta un modelo de Story Points relativo basado en complejidad, esfuerzo y riesgo.
  - Para facilitar la planificación inicial, se establece una equivalencia aproximada entre horas y Story Points, sin perder la naturaleza relativa de los puntos.
- **Conversión propuesta:**
  - 1 SP ≈ 4 horas de trabajo efectivo.
  - Tareas muy pequeñas (≤ 4 horas) se consideran 1 SP.
  - Tareas medianas (entre 8 y 12 horas) se consideran 2–3 SP.
  - Tareas grandes (16–24 horas) se consideran 5–6 SP y se recomienda dividirlas si superan ese rango.

## 2. Estimación por historia

### STORY-001 – Captura de requisitos
- **Horas estimadas:** 40 horas.
- **SP estimados:** 10 SP.
- **Justificación:** Incluye diseño del modelo de requisitos, interfaz de captura y asignación de identificadores.
- **Complejidad técnica:** Media.
- **Dependencias:** Ninguna previa.

### STORY-002 – Construcción de prompt estándar
- **Horas estimadas:** 32 horas.
- **SP estimados:** 8 SP.
- **Justificación:** Definición de estructura del prompt, reglas anti-sobredimensionamiento y pruebas con la IA.
- **Complejidad técnica:** Media.
- **Dependencias:** STORY-001.

### STORY-003 – Generación de JSON unificado
- **Horas estimadas:** 64 horas.
- **SP estimados:** 16 SP.
- **Justificación:** Implementación del motor de consolidación DOC00–DOC10 y manejo de versiones.
- **Complejidad técnica:** Media-alta.
- **Dependencias:** STORY-001, STORY-002.

### STORY-004 – Validación de esquema JSON
- **Horas estimadas:** 32 horas.
- **SP estimados:** 8 SP.
- **Justificación:** Definición de JSON Schema, validación y manejo de errores.
- **Complejidad técnica:** Media.
- **Dependencias:** STORY-003.

### STORY-005 – Sizing y clasificación del proyecto
- **Horas estimadas:** 24 horas.
- **SP estimados:** 6 SP.
- **Justificación:** Implementación de criterios de tamaño y lógica de clasificación.
- **Complejidad técnica:** Media.
- **Dependencias:** STORY-003.

### STORY-006 – Estimación de costes y riesgos
- **Horas estimadas:** 24 horas.
- **SP estimados:** 6 SP.
- **Justificación:** Modelo de costes, registro de riesgos y dependencias.
- **Complejidad técnica:** Media.
- **Dependencias:** STORY-005.

### STORY-007 – Script de integración con GitHub
- **Horas estimadas:** 32 horas.
- **SP estimados:** 8 SP.
- **Justificación:** Desarrollo del script, manejo de credenciales y pruebas de subida.
- **Complejidad técnica:** Media.
- **Dependencias:** STORY-003, STORY-004.

### STORY-008 – Script de integración con Jira
- **Horas estimadas:** 48 horas.
- **SP estimados:** 12 SP.
- **Justificación:** Mapeo de backlog a issues, manejo de errores y pruebas.
- **Complejidad técnica:** Media-alta.
- **Dependencias:** STORY-003, STORY-004.

## 3. Coste total del backlog
- **SP totales:**
  - STORY-001: 10
  - STORY-002: 8
  - STORY-003: 16
  - STORY-004: 8
  - STORY-005: 6
  - STORY-006: 6
  - STORY-007: 8
  - STORY-008: 12
  - **Total:** 74 SP.

- **Horas totales:**
  - STORY-001: 40
  - STORY-002: 32
  - STORY-003: 64
  - STORY-004: 32
  - STORY-005: 24
  - STORY-006: 24
  - STORY-007: 32
  - STORY-008: 48
  - **Total:** 296 horas.

- **Coste estimado:**
  - Dependerá de la tarifa por hora. Por ejemplo, a 60 EUR/hora, el coste aproximado sería 296 × 60 ≈ 17 760 EUR.

## 4. Observaciones y riesgos
- Las estimaciones son iniciales y deberán refinarse tras las primeras iteraciones y prototipos.
- La relación horas–Story Points es una guía; el equipo puede ajustar la escala según su experiencia.
- Riesgo de subestimación en las integraciones con Jira si el modelo de issues es complejo.
- Se recomienda revisar periódicamente la velocidad del equipo (SP por iteración) para ajustar la planificación.

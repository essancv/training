# Story Point Costing (DOC10)

## 1. Relación entre sizing (horas) y Story Points

- Explicación del modelo:
  - Se utilizará un modelo de Story Points (SP) relativo basado en complejidad, esfuerzo e incertidumbre.
  - Para facilitar la planificación inicial, se establece una conversión aproximada entre horas ideales y SP, sabiendo que no es una equivalencia exacta sino una guía.
- Conversión propuesta:
  - 1 SP ≈ 4 horas de trabajo efectivo de un desarrollador.
  - Tareas muy pequeñas (< 2 horas) se agrupan o se consideran 0.5–1 SP según el criterio del equipo.
  - Tareas grandes (> 5 SP) deben dividirse en historias más pequeñas.

## 2. Estimación por historia

A continuación se presentan estimaciones iniciales para algunas historias clave del backlog (DOC9). Estas estimaciones son orientativas y deberán refinarse en sesiones de planning con el equipo.

### STORY-001 – Introducir requisitos estructurados
- Horas estimadas: 8 horas.
- SP estimados: 2 SP.
- Justificación: Desarrollo de formulario/API sencillo, validaciones básicas, pruebas.
- Complejidad técnica: Baja.
- Dependencias: Ninguna.

### STORY-002 – Validar requisitos
- Horas estimadas: 8 horas.
- SP estimados: 2 SP.
- Justificación: Reglas de validación, mensajes de error, pruebas.
- Complejidad técnica: Baja-media.
- Dependencias: STORY-001.

### STORY-003 – Gestionar plantillas de prompt
- Horas estimadas: 16 horas.
- SP estimados: 4 SP.
- Justificación: CRUD de plantillas, almacenamiento, versionado ligero.
- Complejidad técnica: Media.
- Dependencias: Ninguna fuerte (puede ir en paralelo con STORY-001/002).

### STORY-004 – Introducir prompt manual
- Horas estimadas: 12 horas.
- SP estimados: 3 SP.
- Justificación: UI/API para seleccionar/editar plantillas, validaciones de guardrails.
- Complejidad técnica: Media.
- Dependencias: STORY-003.

### STORY-005 – Ejecutar análisis IA
- Horas estimadas: 20 horas.
- SP estimados: 5 SP.
- Justificación: Orquestación de llamada a IA, manejo de errores, timeouts.
- Complejidad técnica: Media-alta.
- Dependencias: STORY-001, STORY-004.

### STORY-006 – Validar respuesta de IA
- Horas estimadas: 16 horas.
- SP estimados: 4 SP.
- Justificación: Implementación de validación contra esquema JSON, gestión de errores.
- Complejidad técnica: Media.
- Dependencias: STORY-005.

### STORY-007 – Ensamblar JSON unificado
- Horas estimadas: 16 horas.
- SP estimados: 4 SP.
- Justificación: Construcción del JSON final, metadatos, estructura DOC00–DOC10.
- Complejidad técnica: Media.
- Dependencias: STORY-006.

### STORY-008 – Trazabilidad entre requisitos y artefactos
- Horas estimadas: 20 horas.
- SP estimados: 5 SP.
- Justificación: Modelo de IDs, referencias cruzadas, validaciones de consistencia.
- Complejidad técnica: Media-alta.
- Dependencias: STORY-001, STORY-007.

### STORY-009 – Publicación en GitHub
- Horas estimadas: 16 horas.
- SP estimados: 4 SP.
- Justificación: Script Python, llamadas a API, manejo de errores.
- Complejidad técnica: Media.
- Dependencias: STORY-007, STORY-011.

### STORY-010 – Publicación en Jira
- Horas estimadas: 20 horas.
- SP estimados: 5 SP.
- Justificación: Script Python, mapeo de backlog a épicas/historias/tareas/spikes.
- Complejidad técnica: Media-alta.
- Dependencias: STORY-007, STORY-011.

### STORY-011 – Gestión de credenciales y configuración
- Horas estimadas: 12 horas.
- SP estimados: 3 SP.
- Justificación: Lectura de variables de entorno, ficheros de configuración, validaciones.
- Complejidad técnica: Media.
- Dependencias: Ninguna.

### STORY-012 – Logging y auditoría
- Horas estimadas: 12 horas.
- SP estimados: 3 SP.
- Justificación: Configuración de logging, formatos, almacenamiento básico.
- Complejidad técnica: Media.
- Dependencias: Puede apoyarse en la infraestructura básica del backend.

## 3. Coste total del backlog

- SP totales (historias STORY-001 a STORY-012):
  - 2 + 2 + 4 + 3 + 5 + 4 + 4 + 5 + 4 + 5 + 3 + 3 = **40 SP**.
- Horas totales estimadas:
  - 8 + 8 + 16 + 12 + 20 + 16 + 16 + 20 + 16 + 20 + 12 + 12 = **176 horas**.
- Coste estimado (ejemplo):
  - Suponiendo una tarifa media de 60 €/hora:
  - 176 horas × 60 €/hora = **10.560 €** (desarrollo puro, sin incluir gestión de proyecto, QA adicional ni contingencias).

## 4. Observaciones y riesgos

- Las estimaciones son de alta incertidumbre y deben refinarse en sesiones de planning con el equipo real.
- Algunas historias (por ejemplo, STORY-005, STORY-008, STORY-010) pueden verse afectadas por la complejidad real de las APIs de IA, GitHub y Jira.
- El modelo 1 SP ≈ 4 horas es una aproximación inicial; el equipo puede ajustar esta relación según su velocidad histórica.
- Es recomendable añadir un margen de contingencia (por ejemplo, 20%) sobre el coste total para cubrir riesgos técnicos y cambios de alcance.

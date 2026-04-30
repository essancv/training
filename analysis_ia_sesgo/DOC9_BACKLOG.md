# Initial Product Backlog

## 1. Épicas

- EPIC-001 – Captura y validación de requisitos (DOMAIN-001, FR-001).
- EPIC-002 – Gestión de prompt manual y guardrails (DOMAIN-002, FR-002, FR-007).
- EPIC-003 – Orquestación de IA y generación de análisis (DOMAIN-003, FR-003).
- EPIC-004 – Ensamblado del JSON y trazabilidad (DOMAIN-004, FR-004, FR-005).
- EPIC-005 – Integración con GitHub y Jira (DOMAIN-005, FR-006).
- EPIC-006 – Configuración, seguridad y observabilidad (DOMAIN-006, NFR-001–NFR-006).

## 2. Features

- FEAT-001 – Formulario/API de entrada de requisitos (EPIC-001).
- FEAT-002 – Motor de validación básica de requisitos (EPIC-001).
- FEAT-003 – Gestión de plantillas de prompt manual (EPIC-002).
- FEAT-004 – Validación de prompt y guardrails anti-sesgo (EPIC-002).
- FEAT-005 – Orquestador de llamada a la IA (EPIC-003).
- FEAT-006 – Validador de respuesta de IA (EPIC-003).
- FEAT-007 – Ensamblador de documentos DOC00–DOC10 en JSON (EPIC-004).
- FEAT-008 – Módulo de trazabilidad entre IDs (EPIC-004).
- FEAT-009 – Script Python para GitHub (EPIC-005).
- FEAT-010 – Script Python para Jira (EPIC-005).
- FEAT-011 – Gestión de configuración y credenciales (EPIC-006).
- FEAT-012 – Logging y auditoría básica (EPIC-006).

## 3. Historias de usuario

- STORY-001 – Introducir requisitos estructurados (FEAT-001, FR-001)
  - Como analista funcional quiero introducir requisitos en un formulario o API para que el sistema pueda analizarlos.

- STORY-002 – Validar requisitos (FEAT-002, FR-001)
  - Como analista quiero que el sistema valide que los requisitos tienen el formato y campos mínimos necesarios.

- STORY-003 – Gestionar plantillas de prompt (FEAT-003, FR-002)
  - Como arquitecto quiero definir y mantener plantillas de prompt para que los analistas las usen de forma consistente.

- STORY-004 – Introducir prompt manual (FEAT-004, FR-002, FR-007)
  - Como analista quiero introducir un prompt manual basado en plantilla para que la IA genere un análisis coherente y sin sobredimensionamiento.

- STORY-005 – Ejecutar análisis IA (FEAT-005, FR-003)
  - Como analista quiero lanzar el análisis IA y obtener los documentos DOC00–DOC10 en una sola ejecución.

- STORY-006 – Validar respuesta de IA (FEAT-006, FR-003)
  - Como desarrollador quiero que el sistema valide la estructura de la respuesta de la IA para asegurar que cumple el esquema JSON.

- STORY-007 – Ensamblar JSON unificado (FEAT-007, FR-005)
  - Como desarrollador quiero disponer de un único JSON con todos los documentos y metadatos para integrarlo fácilmente con otras herramientas.

- STORY-008 – Trazabilidad entre requisitos y artefactos (FEAT-008, FR-004)
  - Como product owner quiero que cada requisito tenga enlaces claros a dominios, flujos, decisiones de arquitectura, riesgos y backlog.

- STORY-009 – Publicación en GitHub (FEAT-009, FR-006)
  - Como desarrollador quiero que el script cree o actualice repositorios/archivos/issues en GitHub a partir del JSON.

- STORY-010 – Publicación en Jira (FEAT-010, FR-006)
  - Como product owner quiero que el script cree épicas, historias, tareas y spikes en Jira a partir del backlog generado.

- STORY-011 – Gestión de credenciales y configuración (FEAT-011, NFR-002)
  - Como administrador quiero configurar credenciales y parámetros de integración de forma segura y centralizada.

- STORY-012 – Logging y auditoría (FEAT-012, NFR-003)
  - Como responsable de proyecto quiero disponer de logs de ejecuciones, errores y cambios de configuración.

- SPIKE-001 – Evaluación de proveedor de IA (EPIC-003)
  - Investigar opciones de modelos de IA, límites de contexto, costes y calidad de resultados.

- SPIKE-002 – Prueba de integración con APIs de GitHub y Jira (EPIC-005)
  - Validar autenticación, límites de rate y mapeo de campos.

## 4. Dependencias

- STORY-002 depende de STORY-001.
- STORY-004 depende de STORY-003.
- STORY-005 depende de STORY-001 y STORY-004.
- STORY-006 depende de STORY-005.
- STORY-007 y STORY-008 dependen de STORY-006.
- STORY-009 y STORY-010 dependen de STORY-007 y STORY-008.
- STORY-011 y STORY-012 son prerequisitos operativos para STORY-009 y STORY-010.

## 5. Priorización MoSCoW

- Must have:
  - STORY-001, STORY-002, STORY-003, STORY-004, STORY-005, STORY-006, STORY-007, STORY-008, STORY-009, STORY-010, STORY-011.
- Should have:
  - STORY-012, SPIKE-001, SPIKE-002.
- Could have:
  - Mejoras de UX, dashboards, integraciones adicionales.
- Won't have (por ahora):
  - Arquitecturas de microservicios, Kubernetes, Kafka, NoSQL distribuido.

## 6. Registro de dudas, acciones y refinamientos para JIRA

### JIRA-ITEM-001
- Origen: DOC00, DOC1.
- Tipo: duda.
- Descripción: Definir el formato exacto de los requisitos de entrada (JSON, Markdown, formulario) y si se soportarán múltiples formatos.
- Impacto: Medio.
- Prioridad sugerida: Alta.
- Propuesta de resolución: Taller con stakeholders para acordar formato estándar y ejemplos.

### JIRA-ITEM-002
- Origen: DOC00, DOC1, DOC2.
- Tipo: acción.
- Descripción: Diseñar y documentar el esquema JSON unificado (DOC00–DOC10) con versionado.
- Impacto: Alto.
- Prioridad sugerida: Alta.
- Propuesta de resolución: Crear un documento de especificación de esquema y validadores automáticos.

### JIRA-ITEM-003
- Origen: DOC5, DOC6.
- Tipo: aclaración.
- Descripción: Confirmar si se requiere una UI web rica (SPA) o basta con una UI mínima/CLI.
- Impacto: Medio.
- Prioridad sugerida: Media.
- Propuesta de resolución: Entrevista con usuarios finales para entender frecuencia de uso y perfil técnico.

### JIRA-ITEM-004
- Origen: DOC7.
- Tipo: riesgo.
- Descripción: Dependencia fuerte del prompt manual y variabilidad de resultados.
- Impacto: Alto.
- Prioridad sugerida: Alta.
- Propuesta de resolución: Definir proceso de revisión de prompts y conjunto de prompts de referencia.

### JIRA-ITEM-005
- Origen: DOC8.
- Tipo: refinamiento.
- Descripción: Ajustar estimaciones de esfuerzo por dominio tras un primer ciclo de diseño detallado.
- Impacto: Medio.
- Prioridad sugerida: Media.
- Propuesta de resolución: Revisar estimaciones tras la fase de diseño técnico inicial.

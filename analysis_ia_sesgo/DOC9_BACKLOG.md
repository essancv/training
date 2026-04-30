# Initial Product Backlog

## 1. Épicas

- **EPIC-001 – Captura y normalización de requisitos**
- **EPIC-002 – Orquestación de prompt y análisis IA**
- **EPIC-003 – Ensamblado del JSON y trazabilidad**
- **EPIC-004 – Integración con GitHub y Jira**
- **EPIC-005 – Gobernanza, seguridad y configuración**

## 2. Features

- **FEAT-001 – Formulario/API de entrada de requisitos** (EPIC-001)
- **FEAT-002 – Motor de validación básica de requisitos** (EPIC-001)
- **FEAT-003 – Generador de prompt estructurado** (EPIC-002)
- **FEAT-004 – Conector con modelo de IA** (EPIC-002)
- **FEAT-005 – Ensamblador de documentos DOC0–DOC9 en JSON** (EPIC-003)
- **FEAT-006 – Módulo de trazabilidad entre IDs** (EPIC-003)
- **FEAT-007 – Script Python para GitHub** (EPIC-004)
- **FEAT-008 – Script Python para Jira** (EPIC-004)
- **FEAT-009 – Gestión de configuración y credenciales** (EPIC-005)
- **FEAT-010 – Logging y auditoría básica** (EPIC-005)

## 3. Historias de usuario

- **STORY-001 – Captura de requisitos** (FEAT-001, FR-001)
  - Como analista funcional quiero introducir requisitos en un formulario o API para que el sistema pueda analizarlos.
  - Criterios de aceptación:
    - Se pueden introducir requisitos en formato estructurado.
    - El sistema valida campos obligatorios.

- **STORY-002 – Validación básica de requisitos** (FEAT-002, FR-001)
  - Como analista quiero que el sistema detecte requisitos vacíos o mal formados para corregirlos antes del análisis IA.

- **STORY-003 – Generación de prompt estructurado** (FEAT-003, FR-002, FR-007)
  - Como arquitecto quiero que el sistema genere un prompt estándar con reglas anti-sesgo y anti-sobredimensionamiento para obtener análisis consistentes.

- **STORY-004 – Ejecución de análisis IA** (FEAT-004, FR-003)
  - Como analista quiero lanzar el análisis IA y obtener los documentos DOC0–DOC9 en una sola ejecución.

- **STORY-005 – Ensamblado de JSON unificado** (FEAT-005, FR-005)
  - Como desarrollador quiero disponer de un único JSON con todos los documentos y metadatos para integrarlo fácilmente con otras herramientas.

- **STORY-006 – Trazabilidad entre requisitos y artefactos** (FEAT-006, FR-004)
  - Como product owner quiero que cada requisito tenga enlaces claros a dominios, flujos, decisiones de arquitectura, riesgos y backlog para entender el impacto de los cambios.

- **STORY-007 – Publicación en GitHub** (FEAT-007, FR-006)
  - Como desarrollador quiero que el script cree o actualice repositorios/archivos/issues en GitHub a partir del JSON para iniciar el desarrollo rápidamente.

- **STORY-008 – Publicación en Jira** (FEAT-008, FR-006)
  - Como product owner quiero que el script cree epics, historias, tareas y spikes en Jira a partir del backlog generado.

- **STORY-009 – Gestión de credenciales y configuración** (FEAT-009, NFR-002)
  - Como administrador quiero configurar credenciales y parámetros de integración de forma segura y centralizada.

- **STORY-010 – Logging y auditoría** (FEAT-010, NFR-003)
  - Como responsable de proyecto quiero disponer de logs de ejecuciones, errores y cambios de configuración para auditoría.

- **SPIKE-001 – Evaluación de proveedor de IA** (EPIC-002)
  - Investigar opciones de modelos de IA, límites de contexto, costes y calidad de resultados.

- **SPIKE-002 – Prueba de integración con APIs de GitHub y Jira** (EPIC-004)
  - Validar autenticación, límites de rate y mapeo de campos.

## 4. Dependencias

- STORY-003 depende de STORY-001 y STORY-002 (se necesitan requisitos validados para generar el prompt).
- STORY-004 depende de STORY-003 (se necesita prompt para llamar a la IA).
- STORY-005 y STORY-006 dependen de STORY-004 (se necesitan documentos generados para ensamblar el JSON y la trazabilidad).
- STORY-007 y STORY-008 dependen de STORY-005 y STORY-006 (se necesita JSON completo y trazable para publicar en GitHub/Jira).
- STORY-009 y STORY-010 son prerequisitos operativos para STORY-007 y STORY-008.

## 5. Priorización MoSCoW

- **Must have**:
  - STORY-001, STORY-002, STORY-003, STORY-004, STORY-005, STORY-006, STORY-007, STORY-008, STORY-009.
- **Should have**:
  - STORY-010, SPIKE-001, SPIKE-002.
- **Could have**:
  - Extensiones futuras (por ejemplo, integración con otras herramientas ALM, UI avanzada) – no incluidas aún como historias.
- **Won't have (por ahora)**:
  - Arquitectura de microservicios y despliegues distribuidos complejos.

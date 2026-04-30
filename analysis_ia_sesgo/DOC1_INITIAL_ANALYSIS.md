# Initial Requirements Analysis

## 1. Resumen ejecutivo
El proyecto consiste en diseñar y definir un sistema que, a partir de requisitos funcionales proporcionados por un usuario y un prompt elaborado manualmente, invoque una IA para generar un análisis inicial completo de la solución. La IA producirá un único JSON estructurado que incluirá evaluación de requisitos, análisis, dominios, flujos, tamaño, arquitectura, riesgos, costes y backlog. Un script en Python permitirá subir automáticamente dicho JSON a GitHub y Jira. El enfoque prioriza simplicidad arquitectónica, trazabilidad completa y un formato de salida estable.

## 2. Objetivos del proyecto
- **OBJ-001:** Permitir que un usuario introduzca requisitos funcionales y obtenga un análisis inicial estructurado de la solución mediante IA.
- **OBJ-002:** Garantizar la trazabilidad entre requisitos de entrada y todos los artefactos generados (dominios, flujos, arquitectura, riesgos, costes, backlog).
- **OBJ-003:** Generar un único JSON con estructura inmutable y versionada, apto para integración automática con GitHub y Jira.
- **OBJ-004:** Evitar sesgos y sobredimensionamiento arquitectónico y tecnológico, priorizando soluciones simples y proporcionales al tamaño del proyecto.
- **OBJ-005:** Disponer de un script en Python que automatice la subida del JSON a GitHub y la creación/actualización de elementos en Jira.

## 3. Alcance
### In scope
- Definición del modelo de datos y esquema JSON de salida (DOC00–DOC10).
- Definición de la lógica de trazabilidad entre requisitos y artefactos generados.
- Diseño conceptual del prompt que se usará con la IA (aunque su redacción final sea manual).
- Integración mediante script en Python con:
  - Repositorio GitHub (subida de archivo JSON).
  - Jira (creación/actualización de issues a partir del backlog).
- Definición de reglas anti-sesgo y anti-sobredimensionamiento arquitectónico y tecnológico.

### Out of scope
- Entrenamiento o ajuste fino del modelo de IA subyacente.
- Desarrollo de una interfaz gráfica de usuario completa (se asume al menos un canal técnico, p.ej. API o CLI).
- Gestión avanzada de permisos y roles en GitHub y Jira (se asume que las credenciales ya existen y son válidas).
- Automatización de despliegues de la solución analizada (CI/CD de la solución objetivo no forma parte del alcance).

## 4. Actores y stakeholders
- **ACT-001 – Usuario analista / Product Owner:** Proporciona los requisitos funcionales y revisa el análisis generado.
- **ACT-002 – Equipo de arquitectura / ingeniería:** Revisa y valida las propuestas de arquitectura y costes generadas por la IA.
- **ACT-003 – Administrador de herramientas (DevOps / Tools):** Configura credenciales y parámetros del script de integración con GitHub y Jira.
- **ACT-004 – Sistema de IA (LLM):** Genera el análisis y el JSON estructurado a partir del prompt y los requisitos.
- **ACT-005 – Repositorio GitHub:** Almacena el JSON como artefacto versionado.
- **ACT-006 – Jira:** Recibe y gestiona las épicas, historias y tareas generadas.

## 5. Supuestos y restricciones
- **Supuestos:**
  - Los requisitos funcionales se proporcionan en un idioma soportado por la IA (por ejemplo, español o inglés).
  - Existen credenciales válidas para GitHub y Jira, gestionadas de forma segura.
  - El modelo de IA es capaz de seguir instrucciones estructuradas y generar JSON válido.
- **Restricciones:**
  - El JSON de salida debe cumplir un esquema estable para no romper el script de integración.
  - Se deben evitar arquitecturas complejas (microservicios, Kubernetes, event streaming) salvo justificación explícita.
  - El sistema debe ser independiente de un proveedor concreto de IA en la medida de lo posible (diseño agnóstico).

## 6. Requisitos funcionales reorganizados
- **FR-001 – Ingesta de requisitos:** El sistema deberá aceptar un conjunto de requisitos funcionales de usuario en un formato estructurado y asociarles identificadores únicos (FR-XXX).
- **FR-002 – Generación de análisis por IA:** El sistema deberá enviar los requisitos y un prompt predefinido a una IA para obtener un análisis inicial de la solución.
- **FR-003 – Generación de JSON único:** El sistema deberá consolidar el resultado de la IA en un único JSON que contenga los documentos DOC00–DOC10.
- **FR-004 – Trazabilidad:** El sistema deberá mantener trazabilidad entre cada requisito funcional y los elementos generados (dominios, flujos, arquitectura, riesgos, costes, backlog).
- **FR-005 – Integración con GitHub:** El sistema deberá proporcionar un script en Python que suba el JSON generado a un repositorio GitHub.
- **FR-006 – Integración con Jira:** El sistema deberá proporcionar un script en Python que cree o actualice issues en Jira a partir del backlog contenido en el JSON.
- **FR-007 – Validación de esquema:** El sistema deberá validar que el JSON generado cumple el esquema definido antes de intentar subirlo a GitHub o Jira.

## 7. Requisitos no funcionales reorganizados
- **NFR-001 – Anti-sobredimensionamiento:** El sistema deberá priorizar arquitecturas y stacks simples para proyectos SMALL o MEDIUM, evitando tecnologías complejas salvo justificación explícita.
- **NFR-002 – Confiabilidad del formato:** El esquema JSON deberá ser versionado y estable, permitiendo validación automática.
- **NFR-003 – Seguridad:** El script de integración deberá gestionar credenciales de GitHub y Jira de forma segura (por ejemplo, variables de entorno, vault).
- **NFR-004 – Usabilidad técnica:** La ejecución del proceso (invocación de IA y script) deberá ser reproducible mediante comandos claros y documentación mínima.
- **NFR-005 – Trazabilidad:** Todos los artefactos generados deberán referenciar los identificadores de requisitos de origen cuando aplique.

## 8. Flujos de negocio preliminares
- **FLOW-001 – Análisis de requisitos con IA:** Desde la introducción de requisitos hasta la obtención del JSON completo.
- **FLOW-002 – Publicación del JSON en GitHub:** Desde la validación del JSON hasta su subida al repositorio.
- **FLOW-003 – Creación de backlog en Jira:** Desde el JSON validado hasta la creación/actualización de issues.

## 9. Preguntas abiertas
1. ¿Se requiere una interfaz de usuario específica o bastará con una API/CLI para orquestar el proceso?
2. ¿Qué volumen máximo de requisitos se espera manejar en una sola ejecución?
3. ¿Se deben soportar múltiples proyectos simultáneos (multi-tenant) o solo un proyecto a la vez?
4. ¿Qué convenciones de nomenclatura se usarán en GitHub (nombres de archivos, ramas) y en Jira (proyectos, tipos de issue)?
5. ¿Se requiere auditoría detallada de cada ejecución (logs, histórico de versiones del JSON)?

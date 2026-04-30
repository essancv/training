# Initial Requirements Analysis

## 1. Resumen ejecutivo

El proyecto consiste en diseñar y construir una solución que, a partir de requisitos funcionales proporcionados por un usuario, genere mediante IA un análisis estructurado de un proyecto software y lo entregue en un único JSON listo para ser integrado automáticamente con GitHub y Jira mediante un script en Python. La solución debe asegurar trazabilidad entre requisitos y todos los artefactos generados, y debe evitar sesgos y sobredimensionamiento arquitectónico innecesario. Se trata de una herramienta de apoyo al análisis y diseño inicial de proyectos, orientada a equipos de desarrollo y gestión.

## 2. Objetivos del proyecto

- FR-001: Permitir la captura de requisitos funcionales de usuario como entrada estructurada.
- FR-002: Construir un prompt estructurado y controlado para un modelo de IA.
- FR-003: Generar un conjunto de documentos de análisis (DOC0–DOC9) en un único JSON.
- FR-004: Mantener trazabilidad explícita entre requisitos y todos los artefactos generados.
- FR-005: Producir un JSON compatible con procesos de integración con GitHub y Jira.
- FR-006: Disponer de un script en Python que suba automáticamente el JSON a GitHub y Jira.
- FR-007: Incorporar en el prompt directrices para evitar sesgos y sobredimensionamiento arquitectónico.

## 3. Alcance

### In scope

- **Captura de requisitos** en formato estructurado (por ejemplo, formulario o fichero).
- **Generación de prompt** para IA, incluyendo reglas de trazabilidad y anti-sesgo.
- **Invocación a un modelo de IA** para producir los documentos DOC0–DOC9.
- **Generación de un único JSON** con todos los documentos y metadatos.
- **Trazabilidad** entre requisitos (FR-XXX, NFR-XXX) y artefactos (DOMAIN-XXX, FLOW-XXX, ARCH-OPT-XXX, RISK-XXX, EPIC-XXX, STORY-XXX).
- **Script en Python** para integración con GitHub (por ejemplo, repositorios, ficheros, issues) y Jira (epics, historias, tareas, spikes).
- **Gestión básica de errores** en la generación del JSON y en las integraciones.

### Out of scope

- Entrenamiento o fine-tuning del modelo de IA (se asume uso de un modelo existente).
- Gestión avanzada de permisos y roles en GitHub/Jira más allá de la autenticación necesaria.
- Interfaz de usuario compleja (por ejemplo, dashboards avanzados); se asume una interfaz mínima o API.
- Análisis legal o de cumplimiento normativo detallado (más allá de buenas prácticas generales).
- Orquestación multi-IA o pipelines distribuidos complejos.

## 4. Actores y stakeholders

- **Actor: Analista funcional / Product Owner**
  - Proporciona los requisitos de entrada y revisa el análisis generado.
- **Actor: Equipo de desarrollo**
  - Consume el JSON generado para planificar el desarrollo y poblar GitHub/Jira.
- **Actor: Administrador de herramientas (DevOps)**
  - Configura credenciales y parámetros de integración con GitHub y Jira.
- **Stakeholder: Dirección de proyecto / PMO**
  - Interesado en la calidad del análisis, estimaciones de coste y riesgos.
- **Stakeholder: Seguridad / Compliance**
  - Interesado en el uso adecuado de la IA y la protección de datos.

## 5. Supuestos y restricciones

- **Supuestos**:
  - Se dispone de acceso a un modelo de IA capaz de procesar prompts complejos y generar texto estructurado.
  - GitHub y Jira están accesibles mediante sus APIs estándar.
  - Los usuarios tienen permisos suficientes en GitHub/Jira para crear y modificar artefactos.

- **Restricciones**:
  - El sistema debe generar un único JSON por ejecución de análisis.
  - El prompt debe incluir reglas explícitas para evitar sobredimensionamiento arquitectónico.
  - El tiempo de respuesta debe ser razonable para uso interactivo (por ejemplo, menos de 1–2 minutos por análisis estándar).
  - El sistema debe ser fácilmente extensible a nuevos tipos de artefactos o herramientas de ALM.

## 6. Requisitos funcionales reorganizados

- **FR-001 – Captura de requisitos**: El sistema deberá aceptar requisitos funcionales de usuario en formato estructurado.
- **FR-002 – Construcción de prompt**: El sistema deberá construir un prompt estructurado y versionado para la IA.
- **FR-003 – Generación de análisis DOC0–DOC9**: El sistema deberá generar los documentos de análisis definidos (DOC0 a DOC9).
- **FR-004 – Trazabilidad completa**: El sistema deberá mantener trazabilidad entre requisitos y artefactos generados.
- **FR-005 – JSON unificado**: El sistema deberá producir un único JSON con todos los documentos y metadatos.
- **FR-006 – Integración GitHub/Jira**: El sistema deberá incluir un script en Python para subir el JSON a GitHub y Jira.
- **FR-007 – Anti-sesgo y anti-sobredimensionamiento**: El sistema deberá incorporar reglas para evitar sesgos y sobredimensionamiento arquitectónico.

## 7. Requisitos no funcionales reorganizados

- **NFR-001 – Rendimiento**: El sistema deberá completar un análisis estándar en un tiempo aceptable (objetivo: < 60–120 segundos).
- **NFR-002 – Seguridad**: El sistema deberá gestionar credenciales de GitHub y Jira de forma segura (por ejemplo, variables de entorno, vault).
- **NFR-003 – Trazabilidad técnica**: El sistema deberá registrar metadatos (timestamps, IDs de ejecución, versiones de prompt) para auditoría.
- **NFR-004 – Robustez**: El sistema deberá manejar errores de la IA y de las APIs externas con mensajes claros y reintentos cuando sea apropiado.
- **NFR-005 – Extensibilidad**: El diseño deberá permitir añadir nuevos tipos de documentos o integraciones sin cambios disruptivos.
- **NFR-006 – Neutralidad y simplicidad arquitectónica**: El sistema deberá priorizar arquitecturas simples salvo justificación explícita en los requisitos analizados.

## 8. Flujos de negocio preliminares

- **FLOW-001 – Ejecución de análisis**:
  - El analista introduce los requisitos.
  - El sistema construye el prompt y llama a la IA.
  - La IA devuelve el JSON con DOC0–DOC9.
  - El analista revisa el resultado.

- **FLOW-002 – Publicación en GitHub/Jira**:
  - El analista confirma el análisis.
  - El script en Python lee el JSON.
  - Se crean/actualizan artefactos en GitHub y Jira.

## 9. Preguntas abiertas

1. ¿Se requiere interfaz gráfica de usuario, API REST, o ambas?
2. ¿Qué estructura exacta debe tener el JSON para integrarse con los flujos actuales de GitHub/Jira del cliente?
3. ¿Se deben soportar múltiples proyectos/repositorios de destino o solo uno por ejecución?
4. ¿Qué políticas de logging y auditoría se requieren (nivel de detalle, retención)?
5. ¿Se desea soporte multi-idioma para los artefactos generados (por ejemplo, inglés/español)?

# Initial Requirements Analysis

## 1. Resumen ejecutivo

El proyecto tiene como objetivo construir una solución que, a partir de requisitos funcionales proporcionados por un usuario y un prompt manualmente elaborado, invoque una IA para generar un análisis estructurado de la solución a desarrollar. La IA debe producir un único JSON inmutable (en cuanto a estructura) que contenga múltiples artefactos de análisis (DOC00–DOC10) y que pueda ser consumido por un script en Python para crear o actualizar artefactos en GitHub y Jira. El sistema debe asegurar trazabilidad entre requisitos y todos los artefactos generados, y el prompt debe minimizar sesgos y sobredimensionamiento arquitectónico.

## 2. Objetivos del proyecto

- FR-001: Permitir la captura de requisitos funcionales de usuario en un formato estructurado.
- FR-002: Permitir la introducción y gestión de un prompt manual siguiendo una plantilla definida.
- FR-003: Invocar a una IA para generar un análisis estructurado que cubra DOC00–DOC10.
- FR-004: Garantizar trazabilidad entre requisitos y todos los artefactos generados.
- FR-005: Obtener un único JSON estructurado e inmutable (en cuanto a esquema) con todos los artefactos.
- FR-006: Desarrollar un script en Python para subir el JSON a GitHub y Jira.
- FR-007: Incorporar en el prompt directrices anti-sesgo y anti-sobredimensionamiento.

## 3. Alcance

### In scope

- Captura y validación básica de requisitos funcionales.
- Gestión de prompt manual (plantilla, validación básica, asociación a requisitos).
- Integración con un modelo de IA para generar el análisis DOC00–DOC10.
- Ensamblado de un único JSON con todos los documentos y metadatos.
- Modelo de trazabilidad entre requisitos y artefactos generados.
- Script en Python para integración con GitHub y Jira.
- Aplicación de guardrails de arquitectura y pila tecnológica en el contenido generado por la IA.

### Out of scope

- Generación automática del prompt (el prompt se elabora manualmente).
- Entrenamiento o fine-tuning del modelo de IA.
- Gestión avanzada de permisos y flujos complejos en GitHub/Jira más allá de la creación/actualización básica.
- Interfaces de usuario avanzadas (dashboards complejos); se asume una UI sencilla o API.

## 4. Actores y stakeholders

- Analista funcional / Product Owner: Introduce requisitos, define el prompt, revisa el análisis generado.
- Arquitecto de software: Define plantillas de prompt, guardrails de arquitectura y pila tecnológica.
- Equipo de desarrollo: Consume el JSON y los artefactos en GitHub/Jira para implementar la solución.
- DevOps / Administrador de herramientas: Configura credenciales y parámetros de integración con GitHub y Jira.
- Dirección de proyecto / PMO: Interesada en estimaciones de tamaño, costes y riesgos.

## 5. Supuestos y restricciones

- Supuestos:
  - Se dispone de acceso a un modelo de IA capaz de procesar prompts complejos y generar texto estructurado.
  - GitHub y Jira están accesibles mediante sus APIs estándar.
  - El equipo de proyecto puede definir y mantener plantillas de prompt.

- Restricciones:
  - El prompt se genera manualmente, aunque siguiendo una plantilla.
  - El sistema debe producir un único JSON por ejecución de análisis.
  - El esquema JSON debe ser estable y versionado.
  - Se deben respetar guardrails de simplicidad arquitectónica y tecnológica.

## 6. Requisitos funcionales reorganizados

- FR-001 – Captura de requisitos: El sistema deberá aceptar requisitos funcionales en formato estructurado.
- FR-002 – Gestión de prompt manual: El sistema deberá permitir introducir y validar un prompt manual basado en una plantilla.
- FR-003 – Generación de análisis DOC00–DOC10: El sistema deberá invocar a la IA para generar los documentos de análisis.
- FR-004 – Trazabilidad: El sistema deberá mantener trazabilidad entre requisitos y artefactos generados.
- FR-005 – JSON unificado e inmutable (esquema): El sistema deberá producir un único JSON siguiendo un esquema versionado.
- FR-006 – Integración GitHub/Jira: El sistema deberá incluir un script en Python para publicar el JSON en GitHub y Jira.
- FR-007 – Guardrails en el prompt: El prompt deberá incluir reglas anti-sesgo y anti-sobredimensionamiento.

## 7. Requisitos no funcionales reorganizados

- NFR-001 – Estabilidad del esquema JSON: El esquema deberá estar versionado y validable.
- NFR-002 – Seguridad: Gestión segura de credenciales de GitHub y Jira.
- NFR-003 – Rendimiento: Tiempo de respuesta razonable (por ejemplo, < 1–2 minutos por análisis estándar).
- NFR-004 – Robustez: Manejo de errores de IA y APIs externas con mensajes claros y reintentos cuando proceda.
- NFR-005 – Extensibilidad: Posibilidad de añadir nuevos tipos de artefactos o integraciones sin rediseñar el sistema.
- NFR-006 – Simplicidad arquitectónica y tecnológica: Priorizar arquitecturas y stacks simples acordes al tamaño del proyecto.

## 8. Flujos de negocio preliminares

- FLOW-001 – Ejecución de análisis IA:
  - El analista introduce requisitos.
  - El analista introduce o selecciona un prompt manual basado en plantilla.
  - El sistema valida requisitos y prompt.
  - El sistema invoca a la IA.
  - El sistema recibe los documentos DOC00–DOC10.
  - El sistema ensambla el JSON unificado y lo presenta para revisión.

- FLOW-002 – Publicación en GitHub y Jira:
  - El analista aprueba el análisis.
  - Se ejecuta el script en Python con el JSON como entrada.
  - El script crea/actualiza artefactos en GitHub.
  - El script crea/actualiza artefactos en Jira.
  - El script registra resultados y enlaces.

## 9. Preguntas abiertas

1. ¿Qué nivel de detalle se espera en cada documento (por ejemplo, número mínimo de flujos, granularidad de historias)?
2. ¿Qué estructura exacta tendrá el backlog (épicas, historias, tareas, spikes) en el JSON?
3. ¿Se requiere soporte multi-idioma en la salida de la IA?
4. ¿Qué políticas de logging y auditoría se necesitan (nivel de detalle, retención)?
5. ¿Se deben soportar múltiples proyectos/repositorios de destino en GitHub/Jira por ejecución?

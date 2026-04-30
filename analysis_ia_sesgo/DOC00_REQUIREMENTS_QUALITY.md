# Requirements Quality Assessment (DOC00)

## 1. Introducción

Este documento evalúa la calidad de los requisitos del proyecto cuyo objetivo es construir una solución que, a partir de requisitos funcionales proporcionados por un usuario y un prompt desarrollado manualmente, invoque una IA para generar un análisis inicial de la solución a desarrollar, produciendo un único JSON estructurado que cubra múltiples artefactos de análisis y que pueda subirse automáticamente a GitHub y Jira mediante un script en Python.

La evaluación se centra en claridad, completitud, consistencia, verificabilidad, factibilidad y riesgos derivados, tomando como base los requisitos textuales proporcionados.

## 2. Evaluación global de calidad

- Claridad: Media. La intención general del sistema es comprensible, pero hay ambigüedades en el nivel de detalle esperado de cada artefacto, en el modelo de trazabilidad y en el grado de "inmutabilidad" del JSON.
- Completitud: Media-baja. Se describen los grandes bloques de salida (evaluación de requisitos, análisis, dominios, flujos, tamaño, arquitectura, riesgos, costes, backlog), pero faltan detalles sobre formatos exactos, validaciones, errores y comportamiento en casos límite.
- Consistencia: Media-alta. No se observan contradicciones directas, aunque la mención a "prompt adecuado" y a "esquemas JSON necesarios" podría interpretarse como generación automática de prompt, lo que se ha aclarado como manual.
- Verificabilidad: Media-baja. No se definen criterios de aceptación cuantitativos (por ejemplo, número mínimo de riesgos, granularidad de historias, estructura exacta del JSON) ni métricas de calidad.
- Factibilidad: Alta. El uso de una IA para generar análisis estructurado y un script en Python para integrarse con GitHub y Jira es técnicamente viable con tecnologías actuales.
- Riesgos derivados: Riesgo de interpretaciones dispares sobre el alcance del análisis, la trazabilidad y la inmutabilidad del JSON; riesgo de sobredimensionamiento arquitectónico si no se aplican guardrails claros.

## 3. Análisis individual de requisitos

### FR-001 – Entrada de requisitos funcionales
- Texto original: "Vamos a crear un solución que, a partir de unos requisitos funcionales determinados por un usuario..."
- Problemas detectados:
  - No se especifica el formato de los requisitos (texto libre, plantilla, JSON, Markdown).
  - No se indica el canal de entrada (UI, API, fichero).
- Evaluación:
  - Claridad: Media.
  - Verificabilidad: Media-baja.
  - Riesgo: Medio (riesgo de incompatibilidad de formatos y errores de interpretación).
- Reescritura recomendada:
  - "FR-001: El sistema deberá aceptar como entrada un conjunto de requisitos funcionales proporcionados por el usuario en un formato estructurado predefinido (por ejemplo, JSON o Markdown) a través de una interfaz (UI o API) documentada."

### FR-002 – Uso de prompt manual
- Texto original: "...y con un prompt adecuado que se generará manualmente, se lo pasemos a una IA..."
- Problemas detectados:
  - "Adecuado" es vago; no se definen pautas, estructura ni criterios de calidad del prompt.
  - No se especifica cómo se valida o versiona el prompt.
- Evaluación:
  - Claridad: Media-baja.
  - Verificabilidad: Baja.
  - Riesgo: Medio-alto (la calidad del resultado depende fuertemente del prompt manual).
- Reescritura recomendada:
  - "FR-002: El sistema deberá permitir introducir un prompt manual, siguiendo una plantilla y directrices definidas en la documentación del proyecto, y asociarlo a un conjunto de requisitos para su envío a la IA."

### FR-003 – Análisis inicial mediante IA
- Texto original: "...para que realice un análisis inicial de la solución a desarrollar, cubriendo los siguientes aspecto: [lista de aspectos]"
- Problemas detectados:
  - No se define el nivel de detalle esperado en cada aspecto.
  - No se especifica el idioma de salida ni la longitud máxima.
- Evaluación:
  - Claridad: Media.
  - Verificabilidad: Media-baja.
  - Riesgo: Medio (salidas heterogéneas difíciles de procesar automáticamente).
- Reescritura recomendada:
  - "FR-003: El sistema deberá invocar a la IA con los requisitos y el prompt manual para generar un análisis estructurado que cubra, como mínimo, los siguientes artefactos: evaluación de calidad de requisitos, análisis inicial, mapa de dominio y bloques funcionales, flujos de negocio, tamaño del proyecto, opciones de arquitectura, comparación de arquitecturas, riesgos y dependencias, costes iniciales y backlog inicial (épicas, historias, tareas, spikes)."

### FR-004 – Trazabilidad entre requisitos y artefactos
- Texto original: "La IA asegure la trazabilidad entre requerimientos y todos los aspectos mencionados."
- Problemas detectados:
  - No se define el modelo de trazabilidad (matriz, referencias cruzadas, IDs).
  - No se especifica si la trazabilidad es bidireccional.
- Evaluación:
  - Claridad: Media.
  - Verificabilidad: Media.
  - Riesgo: Alto (sin modelo claro, la trazabilidad puede ser inconsistente).
- Reescritura recomendada:
  - "FR-004: El sistema deberá garantizar que cada requisito funcional identificado (FR-XXX) esté referenciado explícitamente en los artefactos generados (dominios, flujos, decisiones de arquitectura, riesgos, backlog, etc.), y que dichos artefactos incluyan referencias inversas a los requisitos de origen, manteniendo una trazabilidad bidireccional."

### FR-005 – Generación de un único JSON inmutable
- Texto original: "La IA ha de generar un único JSON con todos estos aspectos que posteriormente se podrán subir automáticamente a github y jira." y "El prompt ha de incorporar los esquemas JSON necesarios para que la salida de la IA sea inmutable."
- Problemas detectados:
  - "Inmutable" no está definido (¿inmutable en estructura, en contenido, en el tiempo?).
  - No se especifica el esquema JSON ni su versionado.
- Evaluación:
  - Claridad: Media.
  - Verificabilidad: Media.
  - Riesgo: Medio-alto (cambios futuros en el esquema pueden romper integraciones).
- Reescritura recomendada:
  - "FR-005: El sistema deberá obtener de la IA un único documento JSON que siga un esquema predefinido y versionado, incluido explícitamente en el prompt, de forma que la estructura del JSON sea estable y validable automáticamente."

### FR-006 – Script en Python para GitHub y Jira
- Texto original: "El sistema desarrollará un script en python para subir automáticamente a github y jira el JSON generado por la IA."
- Problemas detectados:
  - No se detallan flujos concretos de creación/actualización en GitHub/Jira.
  - No se mencionan aspectos de seguridad, reintentos o logging.
- Evaluación:
  - Claridad: Media.
  - Verificabilidad: Media.
  - Riesgo: Medio-alto (riesgos de seguridad y fallos de integración).
- Reescritura recomendada:
  - "FR-006: El sistema deberá incluir un script en Python que consuma el JSON generado y cree o actualice automáticamente artefactos en GitHub y Jira (por ejemplo, ficheros, issues, épicas, historias), gestionando autenticación segura, manejo de errores y registro de resultados."

### FR-007 – Anti-sesgo y anti-sobredimensionamiento
- Texto original: "El prompt ha de evitar cualquier tipo de sesgo y sobredimensionamiento innecesario."
- Problemas detectados:
  - "Cualquier tipo de sesgo" es demasiado amplio y difícil de verificar.
  - No se define qué se considera sobredimensionamiento.
- Evaluación:
  - Claridad: Baja.
  - Verificabilidad: Baja.
  - Riesgo: Medio (expectativas poco realistas sobre control de sesgo).
- Reescritura recomendada:
  - "FR-007: El prompt deberá incluir directrices explícitas para priorizar arquitecturas y pilas tecnológicas simples y proporcionales al tamaño del proyecto, evitando propuestas de microservicios u otras arquitecturas complejas salvo que los requisitos indiquen necesidades de escalabilidad extrema o integraciones distribuidas. Asimismo, deberá minimizar formulaciones que introduzcan sesgos injustificados en las recomendaciones."

### NFR-001 – Estabilidad del esquema JSON
- Texto original implícito: "inmutable".
- Problemas detectados:
  - No se define cómo se gestionan cambios de versión.
- Evaluación:
  - Claridad: Media-baja.
  - Verificabilidad: Media.
  - Riesgo: Medio.
- Reescritura recomendada:
  - "NFR-001: El esquema JSON deberá estar versionado y documentado, y los cambios deberán ser compatibles hacia atrás o gestionados mediante migraciones explícitas."

### NFR-002 – Seguridad e integraciones
- Texto original implícito: uso de GitHub y Jira.
- Problemas detectados:
  - No se mencionan requisitos de protección de credenciales ni de cumplimiento.
- Evaluación:
  - Claridad: Baja.
  - Verificabilidad: Media-baja.
  - Riesgo: Medio-alto.
- Reescritura recomendada:
  - "NFR-002: El sistema deberá gestionar credenciales de GitHub y Jira de forma segura (por ejemplo, variables de entorno, vault) y no deberá registrar información sensible en logs."

## 4. Ambigüedades y contradicciones

- Ambigüedad en el formato de entrada de requisitos y del prompt manual.
- Ambigüedad en el significado de "inmutable" aplicado al JSON.
- Ambigüedad en el alcance de "evitar cualquier tipo de sesgo".
- Ambigüedad inicial (ya aclarada) sobre si el prompt se genera manual o automáticamente.
- No se especifica si el sistema tendrá interfaz gráfica, API, o ambas.

No se detectan contradicciones directas, pero sí riesgo de interpretaciones distintas sobre el nivel de detalle de los artefactos generados y sobre el grado de automatización deseado.

## 5. Preguntas de refinamiento

1. ¿En qué formato exacto se introducirán los requisitos funcionales (JSON, Markdown, formulario web, fichero adjunto)?
2. ¿Cómo se gestionará el ciclo de vida del prompt manual (plantillas, versionado, revisión)?
3. ¿Qué estructura exacta debe tener el JSON unificado (campos obligatorios, tipos, relaciones)?
4. ¿Qué tipos de artefactos concretos se crearán en GitHub (repos, ficheros, issues) y en Jira (épicas, historias, tareas, spikes)?
5. ¿Qué idiomas deben soportarse para la salida de la IA (solo español, también inglés)?
6. ¿Qué límites de tiempo de respuesta son aceptables para la generación del análisis completo?
7. ¿Se requiere almacenamiento histórico de los análisis generados o solo generación puntual? 

## 6. Recomendaciones de mejora

- Definir un modelo de datos claro para requisitos, prompt y JSON de salida, incluyendo esquemas y ejemplos.
- Establecer plantillas de prompt y guías de redacción para reducir variabilidad y sesgos.
- Formalizar el modelo de trazabilidad (identificadores, relaciones, validaciones automáticas).
- Especificar requisitos no funcionales clave: rendimiento, seguridad, logging, auditoría, extensibilidad.
- Documentar claramente los guardrails de arquitectura y pila tecnológica para evitar sobredimensionamiento.
- Definir criterios de aceptación para cada artefacto generado (DOC00–DOC10) y para la integración con GitHub/Jira.

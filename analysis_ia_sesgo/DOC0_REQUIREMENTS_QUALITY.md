# Requirements Quality Assessment (DOC00)

## 1. Introducción

Este documento evalúa la calidad de los requisitos iniciales para un sistema que, a partir de requisitos funcionales proporcionados por un usuario, genera mediante IA un análisis estructurado de proyecto y un JSON único que se subirá automáticamente a GitHub y Jira mediante un script en Python. La evaluación se centra en claridad, completitud, consistencia, verificabilidad y factibilidad, así como en los riesgos derivados de posibles carencias.

## 2. Evaluación global de calidad

- Claridad: Media. La intención general del sistema está clara, pero faltan definiciones precisas de entradas, salidas, actores y restricciones técnicas.
- Completitud: Baja-media. Se describen los grandes bloques de análisis que debe producir la IA, pero no se detallan formatos, reglas de trazabilidad ni casos de uso límite.
- Consistencia: Media-alta. No se observan contradicciones directas, aunque hay solapamientos entre "análisis inicial" y otros apartados que podrían generar interpretaciones distintas.
- Verificabilidad: Media-baja. No se especifican criterios de aceptación cuantificables (por ejemplo, qué significa exactamente "evitar sesgo" o "asegurar trazabilidad").
- Factibilidad: Alta. La solución es conceptualmente viable con tecnologías actuales de IA y herramientas de integración (Python, GitHub, Jira), aunque se requieren más detalles técnicos.
- Riesgos derivados: Riesgo de interpretaciones ambiguas del alcance, de trazabilidad incompleta y de sobredimensionamiento o infra-dimensionamiento de la arquitectura por falta de RNF explícitos.

## 3. Análisis individual de requisitos

### FR-001
- Texto original: "Vamos a crear un solución que, a partir de unos requisitos funcionales determinados por un usuario..."
- Problemas detectados: No se especifica el formato de los requisitos (texto libre, plantilla, fichero), ni el canal de entrada.
- Evaluación: 
  - Claridad: Media
  - Verificabilidad: Media-baja
  - Riesgo: Medio (riesgo de incompatibilidad de formatos y errores de parsing).
- Reescritura recomendada: "FR-001: El sistema deberá aceptar como entrada un conjunto de requisitos funcionales en formato texto estructurado (por ejemplo, JSON o Markdown) proporcionados por el usuario a través de una interfaz definida."

### FR-002
- Texto original: "...y con un prompt adecuado se los pasemos a una IA para que realice un análisis inicial de la solución a desarrollar..."
- Problemas detectados: "Prompt adecuado" es vago; no se definen reglas de construcción, idioma, ni control de versiones del prompt.
- Evaluación:
  - Claridad: Media-baja
  - Verificabilidad: Baja
  - Riesgo: Medio-alto (cambios en el prompt pueden alterar drásticamente los resultados).
- Reescritura recomendada: "FR-002: El sistema deberá construir automáticamente un prompt estructurado y versionado, a partir de los requisitos de entrada, para enviarlo a un modelo de IA que genere el análisis inicial de la solución."

### FR-003
- Texto original: "...para que realice un análisis inicial de la solución a desarrollar cubriendo los siguientes aspecto: [lista de aspectos]"
- Problemas detectados: No se define el nivel de detalle esperado por cada aspecto ni el formato de salida.
- Evaluación:
  - Claridad: Media
  - Verificabilidad: Media-baja
  - Riesgo: Medio (salidas heterogéneas difíciles de procesar automáticamente).
- Reescritura recomendada: "FR-003: El sistema deberá generar, mediante IA, un análisis estructurado que cubra, como mínimo, los siguientes documentos: evaluación de calidad de requisitos, análisis inicial, mapa de dominio y bloques funcionales, flujos de negocio, tamaño del proyecto, opciones de arquitectura, comparación de arquitecturas, riesgos y dependencias, costes iniciales y backlog inicial."

### FR-004
- Texto original: "La IA ha de asegurar la trazabilidad entre requerimientos y todos los aspectos mencionados."
- Problemas detectados: No se define cómo se representa la trazabilidad (matriz, identificadores, enlaces) ni el nivel de granularidad.
- Evaluación:
  - Claridad: Media
  - Verificabilidad: Media
  - Riesgo: Alto (sin un modelo de trazabilidad claro, se puede perder el vínculo entre requisitos y artefactos).
- Reescritura recomendada: "FR-004: El sistema deberá mantener trazabilidad bidireccional entre cada requisito funcional identificado (por ejemplo, FR-XXX) y los elementos generados en los documentos de análisis (dominios, flujos, decisiones de arquitectura, riesgos, historias de usuario, etc.), utilizando identificadores únicos y referencias explícitas."

### FR-005
- Texto original: "La IA ha de generar un único JSON con todos estos aspectos que posteriormente se podrán subir automáticamente a github y jira."
- Problemas detectados: No se especifica el esquema JSON, ni la compatibilidad con las APIs de GitHub y Jira.
- Evaluación:
  - Claridad: Media
  - Verificabilidad: Media
  - Riesgo: Medio (riesgo de incompatibilidad con integraciones futuras).
- Reescritura recomendada: "FR-005: El sistema deberá generar un único documento JSON que contenga todos los artefactos de análisis estructurados según un esquema predefinido y versionado, apto para ser consumido por procesos de integración con GitHub y Jira."

### FR-006
- Texto original: "El sistema desarrollará un script en python para subir automáticamente a github y jira el JSON generado por la IA."
- Problemas detectados: No se definen credenciales, seguridad, ni estrategia de errores/reintentos.
- Evaluación:
  - Claridad: Media
  - Verificabilidad: Media
  - Riesgo: Medio-alto (riesgos de seguridad y fallos de integración).
- Reescritura recomendada: "FR-006: El sistema deberá incluir un componente de integración implementado en Python que consuma el JSON generado y cree/actualice automáticamente artefactos en GitHub y Jira, gestionando autenticación segura, manejo de errores y trazabilidad de operaciones."

### FR-007
- Texto original: "El prompt ha de evitar cualquier tipo de sesgo y sobredimensionamiento innecesario."
- Problemas detectados: "Cualquier tipo de sesgo" es demasiado amplio; no se define cómo medir o mitigar el sesgo ni qué se considera sobredimensionamiento.
- Evaluación:
  - Claridad: Baja
  - Verificabilidad: Baja
  - Riesgo: Medio (expectativas poco realistas sobre control de sesgo en IA).
- Reescritura recomendada: "FR-007: El sistema deberá aplicar directrices explícitas en el prompt para priorizar arquitecturas simples y evitar propuestas de sobredimensionamiento tecnológico, salvo que los requisitos indiquen necesidades de escalabilidad o complejidad excepcionales. Asimismo, deberá minimizar formulaciones que introduzcan sesgos injustificados en las recomendaciones."

### NFR-001
- Texto original implícito: Robustez y ausencia de sesgos en el análisis.
- Problemas detectados: No se definen métricas de calidad, rendimiento ni seguridad.
- Evaluación:
  - Claridad: Baja
  - Verificabilidad: Baja
  - Riesgo: Medio.
- Reescritura recomendada: "NFR-001: El sistema deberá producir resultados consistentes para entradas equivalentes, con tiempos de respuesta aceptables (por ejemplo, menos de 30 segundos para un conjunto estándar de requisitos) y sin exponer credenciales ni datos sensibles en los artefactos generados."

## 4. Ambigüedades y contradicciones

- Ambigüedad en el formato de entrada de requisitos (texto libre vs. estructura).
- Ambigüedad en el nivel de detalle de cada documento generado (por ejemplo, profundidad del mapa de dominio o del backlog).
- Ambigüedad en el concepto de "evitar cualquier tipo de sesgo" y "sobredimensionamiento innecesario".
- No se especifica si el sistema tendrá interfaz de usuario, API, o ambos.
- No se detallan restricciones de seguridad, cumplimiento normativo o protección de datos.

No se detectan contradicciones explícitas, pero sí posibles interpretaciones divergentes sobre el alcance del análisis (por ejemplo, si debe incluir también diseño detallado o solo análisis de alto nivel).

## 5. Preguntas de refinamiento

1. ¿En qué formato exacto proporcionará el usuario los requisitos (Markdown, JSON, documento de texto, formulario web)?
2. ¿Qué nivel de detalle se espera en cada uno de los documentos generados (por ejemplo, número mínimo de flujos, granularidad de historias de usuario)?
3. ¿Qué restricciones de seguridad y cumplimiento (por ejemplo, GDPR, políticas internas) aplican al uso de la IA y a los datos procesados?
4. ¿Qué versiones de GitHub y Jira (Cloud/Server/Data Center) se deben soportar y qué tipo de artefactos se crearán (issues, epics, repos, ramas, PRs)?
5. ¿Existe un presupuesto temporal o económico objetivo para el desarrollo de este sistema?
6. ¿Qué idiomas deben soportarse para los requisitos de entrada y para los artefactos generados?
7. ¿Se requiere almacenamiento histórico de los análisis generados o solo generación puntual bajo demanda?

## 6. Recomendaciones de mejora

- Definir un modelo de datos claro para los requisitos de entrada y para el JSON de salida, incluyendo esquemas y ejemplos.
- Establecer criterios de aceptación medibles para cada documento generado (por ejemplo, número mínimo de riesgos identificados, estructura obligatoria del backlog).
- Especificar requisitos no funcionales clave: rendimiento, seguridad, auditoría, trazabilidad, internacionalización.
- Formalizar la política de "no sobredimensionamiento" en forma de reglas de decisión arquitectónica basadas en tamaño y complejidad del proyecto.
- Detallar el alcance de las integraciones con GitHub y Jira, incluyendo flujos de autenticación, mapeo de campos y manejo de errores.
- Introducir identificadores únicos para requisitos, dominios, flujos, riesgos y elementos de backlog desde el inicio para facilitar la trazabilidad.

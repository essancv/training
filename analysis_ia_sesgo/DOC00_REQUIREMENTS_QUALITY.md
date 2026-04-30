# Requirements Quality Assessment (DOC00)

## 1. Introducción
Este documento evalúa la calidad de los requisitos proporcionados para un sistema cuyo objetivo es, a partir de requisitos funcionales definidos por un usuario y un prompt manualmente elaborado, invocar una IA que genere un análisis inicial de la solución y produzca un único JSON estructurado, apto para su subida automática a GitHub y Jira mediante un script en Python.

## 2. Evaluación global de calidad
- **Claridad:** Media. El objetivo general es entendible, pero faltan detalles sobre formatos exactos de entrada, validaciones, errores y casos límite.
- **Completitud:** Media-baja. Se definen los bloques de salida (análisis, mapa de dominio, flujos, costes, backlog, etc.), pero no se especifican todos los campos obligatorios de cada sección ni reglas de negocio detalladas.
- **Consistencia:** Alta. La intención de trazabilidad, JSON único, ausencia de sesgos y sobredimensionamiento es consistente en todo el texto.
- **Verificabilidad:** Media. Hay objetivos medibles (único JSON, trazabilidad, subida a GitHub/Jira), pero faltan criterios cuantitativos (p.ej. validaciones de esquema, códigos de error, SLAs).
- **Factibilidad:** Alta. El alcance es razonable para un proyecto SMALL/MEDIUM con una IA LLM y un script en Python.
- **Riesgos derivados:** Riesgo de ambigüedad en el diseño del esquema JSON, en la definición del prompt y en la interpretación de “evitar sesgos” y “sobredimensionamiento”.

## 3. Análisis individual de requisitos

### FR-001
- **Texto original:** "Vamos a crear un solución que, a partir de unos requisitos funcionales determinados por un usuario, y con un prompt adecuado que se generará manualmente, se lo pasemos a una IA para que realice un análisis inicial de la solución a desarrollar."
- **Problemas detectados:**
  - No se especifica el formato de los requisitos funcionales de entrada.
  - No se define el canal de interacción (API, CLI, UI).
  - "Análisis inicial" es genérico.
- **Evaluación:**
  - Claridad: Media.
  - Verificabilidad: Baja-media.
  - Riesgo: Medio.
- **Reescritura recomendada:**
  - "El sistema deberá aceptar un conjunto de requisitos funcionales en formato estructurado (por ejemplo, JSON o texto etiquetado) y, junto con un prompt definido manualmente, enviarlos a un modelo de IA para obtener un análisis inicial de la solución a desarrollar."

### FR-002
- **Texto original:** "La IA ha de asegurar la trazabilidad entre requerimientos y todos los aspectos mencionados."
- **Problemas detectados:**
  - No se define el mecanismo concreto de trazabilidad (identificadores, enlaces, matrices).
- **Evaluación:**
  - Claridad: Media.
  - Verificabilidad: Media.
  - Riesgo: Medio.
- **Reescritura recomendada:**
  - "La IA deberá mantener trazabilidad explícita entre cada requisito funcional de entrada y los elementos generados (dominios, flujos, decisiones de arquitectura, riesgos, costes y backlog), utilizando identificadores únicos de requisito (por ejemplo, FR-001, FR-002) referenciados en todas las secciones del JSON."

### FR-003
- **Texto original:** "La IA ha de generar un único JSON con todos estos aspectos que posteriormente se podrán subir automáticamente a github y jira."
- **Problemas detectados:**
  - No se especifica el esquema JSON completo ni la versión.
  - No se definen restricciones de tamaño ni validación.
- **Evaluación:**
  - Claridad: Media-alta.
  - Verificabilidad: Media.
  - Riesgo: Medio.
- **Reescritura recomendada:**
  - "La IA deberá generar un único documento JSON que contenga todas las secciones del análisis (DOC00–DOC10), siguiendo un esquema JSON versionado y estable, apto para ser consumido por un script de integración con GitHub y Jira."

### FR-004
- **Texto original:** "El sistema desarrollará un script en python para subir automáticamente a github y jira el JSON generado por la IA."
- **Problemas detectados:**
  - No se detallan credenciales, seguridad, ni estrategia de errores.
- **Evaluación:**
  - Claridad: Media.
  - Verificabilidad: Media.
  - Riesgo: Medio.
- **Reescritura recomendada:**
  - "Se deberá desarrollar un script en Python que, utilizando credenciales seguras y configurables, suba automáticamente el JSON generado por la IA a un repositorio de GitHub (como archivo versionado) y cree o actualice issues en Jira basados en el contenido del backlog."

### FR-005
- **Texto original:** "El prompt ha de evitar cualquier tipo de sesgo y sobredimensionamiento innecesario."
- **Problemas detectados:**
  - "Sesgo" y "sobredimensionamiento" no están operativamente definidos.
- **Evaluación:**
  - Claridad: Media-baja.
  - Verificabilidad: Baja.
  - Riesgo: Medio.
- **Reescritura recomendada:**
  - "El prompt deberá incluir reglas explícitas para priorizar arquitecturas y pilas tecnológicas simples y proporcionales al tamaño del proyecto, evitando propuestas de arquitecturas distribuidas complejas (por ejemplo, microservicios, Kubernetes, event streaming) salvo que existan requisitos explícitos que las justifiquen."

### FR-006
- **Texto original:** "El prompt ha de incorporar los esquemas JSON necesarios para que la salida de la IA sea inmutable."
- **Problemas detectados:**
  - "Inmutable" es ambiguo (¿no modificable por humanos? ¿estable en el tiempo?).
- **Evaluación:**
  - Claridad: Media.
  - Verificabilidad: Media.
  - Riesgo: Medio.
- **Reescritura recomendada:**
  - "El prompt deberá incluir la definición explícita de los esquemas JSON (estructura, tipos de datos y campos obligatorios) para garantizar que la IA genere siempre una salida con la misma estructura, facilitando su validación automática y reduciendo cambios no controlados en el formato."

### NFR-001
- **Texto original:** "El prompt ha de evitar cualquier tipo de sesgo y sobredimensionamiento innecesario."
- **Problemas detectados:**
  - Requisito no funcional mezclado con funcional.
- **Evaluación:**
  - Claridad: Media.
  - Verificabilidad: Baja-media.
  - Riesgo: Medio.
- **Reescritura recomendada:**
  - "El sistema deberá minimizar el riesgo de sobredimensionamiento arquitectónico y tecnológico, incluyendo en el prompt reglas de selección de arquitectura y stack basadas en el tamaño y complejidad del proyecto, priorizando soluciones simples para proyectos SMALL o MEDIUM."

## 4. Ambigüedades y contradicciones
- **Formato de entrada de requisitos:** No se especifica si será texto libre, JSON, formulario u otro formato.
- **Definición de "análisis inicial":** No se detalla el nivel de profundidad esperado (alto nivel vs. diseño detallado).
- **"Inmutabilidad" del JSON:** Ambiguo; puede interpretarse como formato estable o como prohibición de edición posterior.
- **Alcance del script Python:** No se define si debe crear ramas, pull requests, issues, o solo subir archivos.
- No se observan contradicciones directas, pero sí lagunas que pueden generar interpretaciones divergentes.

## 5. Preguntas de refinamiento
1. ¿En qué formato exacto se proporcionarán los requisitos funcionales de entrada (JSON, Markdown, texto plano estructurado)?
2. ¿Debe el sistema validar sintáctica y semánticamente los requisitos antes de enviarlos a la IA?
3. ¿Qué nivel de detalle se espera en el análisis de arquitectura (solo opciones, o también decisiones concretas de componentes)?
4. ¿Existe un límite de tamaño para el JSON generado (por ejemplo, para evitar problemas en Jira o GitHub)?
5. ¿Qué políticas de seguridad y gestión de credenciales se aplicarán para el script de integración con GitHub y Jira?
6. ¿Se requiere soporte multilenguaje (p.ej. requisitos en español e inglés)?

## 6. Recomendaciones de mejora
- Definir un **esquema JSON formal** (por ejemplo, usando JSON Schema) para el documento de salida, incluyendo versiones.
- Establecer un **formato estándar de requisitos de entrada**, con identificadores únicos y campos mínimos (título, descripción, prioridad, origen).
- Especificar criterios objetivos para "evitar sobredimensionamiento" (por ejemplo, reglas de decisión basadas en tamaño SMALL/MEDIUM/LARGE).
- Incluir requisitos de **seguridad, auditoría y logging** para el script de integración.
- Documentar ejemplos de entrada y salida para facilitar pruebas y validación.

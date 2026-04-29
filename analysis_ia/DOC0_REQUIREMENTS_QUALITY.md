# Requirements Quality Assessment (DOC00)

## 1. Introducción
Este documento evalúa la calidad de los requisitos proporcionados aplicando criterios de IEEE 830 e ISO 29148: corrección, completitud, consistencia, no ambigüedad, verificabilidad, factibilidad y trazabilidad.

## 2. Evaluación global de calidad
- Claridad general: Media
- Completitud: Media
- Consistencia: Alta
- Verificabilidad: Media
- Factibilidad: Alta
- Riesgos derivados: Ambigüedad en alcance técnico, ausencia de métricas de calidad y falta de restricciones operativas.

## 3. Análisis individual de requisitos
### FR-001: Generar análisis inicial con IA
- Texto original: La IA realizará un análisis inicial de la solución.
- Problemas detectados:
  - Ambigüedad
  - Falta de información
- Evaluación:
  - Claridad: media
  - Verificabilidad: media
  - Riesgo: medio
- Reescritura recomendada (si aplica): El sistema generará un análisis inicial estructurado incluyendo objetivos, alcance, actores y supuestos.

### FR-002: Generar mapa de dominio y bloques funcionales
- Texto original: Mapa de dominio y bloques funcionales.
- Problemas detectados:
  - Falta de información
- Evaluación:
  - Claridad: media
  - Verificabilidad: alta
  - Riesgo: bajo
- Reescritura recomendada (si aplica): El sistema generará un mapa de dominios funcionales con dependencias entre bloques.

### FR-003: Generar flujos de negocio
- Texto original: flujos de negocio.
- Problemas detectados:
  - Falta de detalle
- Evaluación:
  - Claridad: media
  - Verificabilidad: alta
  - Riesgo: bajo
- Reescritura recomendada (si aplica): El sistema generará flujos de negocio principales con pasos, actores y alternativas.

### FR-004: Generar JSON único
- Texto original: La IA ha de generar un único JSON.
- Problemas detectados:
  - Sin esquema formal
- Evaluación:
  - Claridad: alta
  - Verificabilidad: alta
  - Riesgo: medio
- Reescritura recomendada (si aplica): El sistema generará un JSON válido conforme a esquema versionado.

### FR-005: Subida automática a GitHub y Jira
- Texto original: Script en python para subir automáticamente a github y jira.
- Problemas detectados:
  - Solución disfrazada de requisito
- Evaluación:
  - Claridad: media
  - Verificabilidad: alta
  - Riesgo: medio
- Reescritura recomendada (si aplica): El sistema permitirá publicar automáticamente artefactos en GitHub y Jira mediante mecanismo automatizado.

## 4. Ambigüedades y contradicciones detectadas
- No se define tipo de usuario ni perfiles.
- No se especifica si habrá interfaz gráfica o solo script/API.
- No se define versión de GitHub/Jira ni modelo cloud/on-premise.
- No se indican SLAs ni tiempos máximos de respuesta.
- No se especifica proveedor IA ni límites de coste.

## 5. Preguntas de refinamiento necesarias
- Q-001: ¿Quiénes serán los usuarios objetivo del sistema?
- Q-002: ¿Se requiere UI web además de script?
- Q-003: ¿Qué versiones de Jira y GitHub se soportarán?
- Q-004: ¿Cuál es el volumen esperado de ejecuciones mensuales?
- Q-005: ¿Qué nivel de trazabilidad y auditoría se requiere?

## 6. Recomendaciones de mejora
Definir actores, métricas de rendimiento, criterios de aceptación, restricciones de seguridad, esquema JSON oficial, prioridades MoSCoW y estrategia multi proveedor IA.
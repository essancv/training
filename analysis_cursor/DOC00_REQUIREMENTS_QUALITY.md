# Requirements Quality Assessment (DOC00)

## 1. Introducción
Este documento evalúa la calidad de los requisitos iniciales para una solución estandarizada que integre un LLM en el proceso de Pull Request en GitHub con foco en 4 capas: proceso, prompts, checklist y automatización.

## 2. Evaluación global de calidad
Evaluación general: Media.

- Fortalezas
 - Objetivo claro: estandarizar revisión automática en PR.
 - Cobertura amplia: estilo, documentación, patrones, seguridad.
 - Enfoque por capas aporta estructura.

- Debilidades principales
 - Requisitos no medibles: faltan umbrales y criterios de aceptación.
 - Ambigüedad de alcance: no se define si es solo Java y React o multi stack.
 - No se concreta el modo de integración LLM: proveedor, costes, latencia, privacidad.
 - Falta de definición del reporte: formato, severidades, bloqueo o recomendación.

## 3. Análisis individual de requisitos
- FR-001 Detectar eventos de PR abierta o actualizada
 - Claridad: Alta
 - Testeabilidad: Media
 - Observación: definir repos soportados y forks

- FR-002 Extraer diff de código modificado
 - Claridad: Media
 - Testeabilidad: Alta
 - Observación: definir límites de tamaño y archivos binarios

- FR-003 Identificar contexto de proyecto lenguaje y tipo de proyecto
 - Claridad: Media
 - Testeabilidad: Media
 - Observación: definir reglas de clasificación SMALL MEDIUM COMPLEX

- FR-004 Enviar contenido al motor de análisis IA
 - Claridad: Media
 - Testeabilidad: Media
 - Observación: definir redacción y minimización de datos

- FR-005 Generar informe estructurado
 - Claridad: Media
 - Testeabilidad: Media
 - Observación: definir esquema de salida y severidades

- FR-006 Publicar resultado en comentario de PR
 - Claridad: Alta
 - Testeabilidad: Alta
 - Observación: definir actualizaciones idempotentes

- FR-007 Checklist humano de quality gate
 - Claridad: Media
 - Testeabilidad: Media
 - Observación: definir plantilla y obligatoriedad

- FR-008 Pipeline de GitHub Actions para automatizar el flujo
 - Claridad: Alta
 - Testeabilidad: Alta
 - Observación: definir secretos y permisos

- FR-009 API externa POST analyze con autenticación por API key y soporte multi repo
 - Claridad: Media
 - Testeabilidad: Media
 - Observación: definir rate limit y auditoria

- NFR-001 Uniformidad de estilo de codificación
 - Claridad: Media
 - Medible: Baja
 - Observación: definir herramientas y reglas y umbrales

- NFR-002 Documentación de métodos y clases siguiendo buenas practicas
 - Claridad: Media
 - Medible: Baja
 - Observación: definir cuando es obligatorio JavaDoc y excepciones

- NFR-003 Validar buenos patrones SOLID y Clean Code
 - Claridad: Media
 - Medible: Baja
 - Observación: definir lista de code smells y severidades

- NFR-004 Avisar de errores de seguridad desde el principio
 - Claridad: Media
 - Medible: Media
 - Observación: definir fuentes SAST SCA y detecciones LLM

## 4. Ambigüedades y contradicciones
- La detección de patrones SOLID puede ser subjetiva si no se define una taxonomía de hallazgos.
- No se define si el sistema bloquea merges o solo comenta recomendaciones.
- Se menciona multi repo pero no se define un modelo de configuracion por repositorio.
- No se define como evitar filtrado de datos sensibles al LLM.

## 5. Preguntas de refinamiento
- Que significa estilo uniforme en Java y en frontend y cual es el standard objetivo
- El analisis debe ser obligatorio para merge o informativo
- Cuales son los limites de diff por PR y estrategia para PR grandes
- Se requiere soporte on premise o puede usar proveedor externo de LLM
- Se requiere trazabilidad y auditoria de resultados por PR
- Idioma del informe y plantilla unica o por equipo

## 6. Recomendaciones de mejora
- Definir un catalogo de hallazgos con severidad y criterio de bloqueo.
- Definir un esquema de reporte estable y parseable.
- Integrar herramientas deterministas como Checkstyle Spotless Sonar y Dependency Check y usar el LLM como capa de razonamiento.
- Definir politicas de privacidad redaccion de secretos y configuracion por repositorio.
- Definir SLA de tiempo maximo por ejecucion y comportamiento ante fallos.

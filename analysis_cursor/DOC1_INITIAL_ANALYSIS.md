# Initial Requirements Analysis

## 1. Resumen ejecutivo
Se propone una solucion estandar para revisar automaticamente Pull Requests en GitHub integrando un LLM y herramientas de analisis estatico. El objetivo es dar feedback consistente sobre estilo documentacion patrones de diseno y seguridad desde fases tempranas.

## 2. Objetivos del proyecto
- Estandarizar la revision inicial de PR y reducir esfuerzo repetitivo.
- Asegurar uniformidad de estilo y convenciones.
- Mejorar calidad de documentacion y mantenibilidad.
- Detectar riesgos de seguridad lo antes posible.
- Proveer un informe estructurado publicable en la PR.

## 3. Alcance
### In scope
- Integracion con GitHub Pull Request opened y Pull Request synchronize.
- Extraccion de diff y listado de archivos modificados.
- Motor de prompts por capas base tecnologia tipo de proyecto y contexto.
- Analisis hibrido herramientas deterministas mas LLM.
- Generacion de informe estructurado con hallazgos y recomendaciones.
- Publicacion automatica de comentario en PR.
- Checklist humano estandar como quality gate.
- API externa POST analyze con API key.

### Out of scope
- Reemplazar la revision humana completa.
- Refactorizaciones automaticas de codigo en el repositorio.
- Orquestacion compleja tipo Kubernetes o event streaming.
- Gobernanza corporativa completa de modelos y datos mas alla de necesidades del proyecto.

## 4. Actores y stakeholders
- Developer: crea y actualiza PR.
- Reviewer: valida checklist y decide aprobacion.
- Tech Lead: define reglas de calidad y thresholds.
- Security Champion: define politicas OWASP y reglas de seguridad.
- DevOps: mantiene pipelines secretos y permisos.
- Product Owner: prioriza backlog y adopcion por equipos.

## 5. Supuestos y restricciones
- Equipo de 5 desarrolladores full stack.
- Backend principal Java Spring Boot.
- Frontend previsto React.
- Repos en GitHub y uso de GitHub Actions.
- Uso de herramientas existentes permitidas en CI.
- Restriccion de simplicidad para tamanos SMALL o MEDIUM.

## 6. Requisitos funcionales reorganizados
- FR-001 Detectar evento de PR abierta o actualizada.
- FR-002 Obtener diff y archivos modificados.
- FR-003 Determinar contexto tecnologia y tipo de proyecto.
- FR-004 Construir prompt por capas con reglas y contexto.
- FR-005 Ejecutar analisis con herramientas deterministas y LLM.
- FR-006 Generar reporte estructurado con severidad y recomendaciones.
- FR-007 Publicar el reporte en la PR como comentario.
- FR-008 Registrar metricas opcionales de ejecucion y calidad.
- FR-009 Exponer API POST analyze con autenticacion por API key.
- FR-010 Gestionar configuracion por repositorio reglas y thresholds.

## 7. Requisitos no funcionales reorganizados
- NFR-001 Seguridad y privacidad de datos del codigo y secretos.
- NFR-002 Latencia aceptable por PR y comportamiento ante timeouts.
- NFR-003 Confiabilidad e idempotencia al comentar en PR.
- NFR-004 Mantenibilidad de prompts y reglas por tecnologia.
- NFR-005 Auditabilidad de resultados y trazabilidad.

## 8. Flujos de negocio preliminares
- FLOW-001 Revision automatica de Pull Request.
- FLOW-002 Construccion de prompts y analisis.
- FLOW-003 Publicacion de reporte y checklist humano.

## 9. Preguntas abiertas
- Umbrales de bloqueo por severidad y que herramientas son obligatorias.
- Modelo de clasificacion de proyecto SMALL MEDIUM COMPLEX.
- Proveedor de LLM y estrategia de coste.
- Estrategia de redaccion de secretos y datos sensibles.

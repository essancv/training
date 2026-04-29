# Initial Requirements Analysis

## 1. Resumen ejecutivo
Plataforma de revisión automática de código basada en inteligencia artificial integrada con Pull Requests y CI/CD. El producto permitirá analizar cambios de código, detectar defectos funcionales, vulnerabilidades y malas prácticas, publicando recomendaciones accionables en el flujo habitual de desarrollo. El alcance inicial se centra en Java Spring Boot, con arquitectura preparada para soportar nuevas tecnologías.

## 2. Objetivos del proyecto
- Automatizar revisión de código en Pull Requests.
- Mejorar calidad técnica y reducir defectos en producción.
- Detectar vulnerabilidades y riesgos OWASP Top 10.
- Integrarse con Git y pipelines CI/CD.
- Proveer explicaciones accionables y trazables.
- Controlar costes derivados del consumo de IA.
- Permitir crecimiento multi-tecnología y multi-repositorio.

## 3. Alcance
### In scope
- Análisis automático de Pull Requests.
- Integración inicial con GitHub.
- Integración con GitHub Actions y CI/CD.
- API REST de análisis.
- Publicación de comentarios en Pull Requests.
- Motor modular de prompts.
- Soporte inicial Java Spring Boot.
- Detección de vulnerabilidades comunes.
- Configuración por repositorio.
- Observabilidad y trazabilidad.
- Gestión de límites y costes.
- Captura de feedback.
### Out of scope
- Plugins de IDE.
- Modificación automática del código.
- Gestión completa de repositorios Git.
- Persistencia integral del código fuente.
- Sistema completo de incidencias o ticketing.

## 4. Actores y stakeholders
- Nombre: Developer
- Rol: Usuario consumidor de resultados
- Interés: Mejorar calidad y velocidad de entrega
- Responsabilidades: Revisar y corregir hallazgos

- Nombre: CI CD System
- Rol: Sistema integrador
- Interés: Automatizar validaciones
- Responsabilidades: Lanzar análisis y procesar estados

- Nombre: Git Provider GitHub
- Rol: Plataforma externa
- Interés: Integración estable
- Responsabilidades: Eventos, diffs y comentarios

- Nombre: AI Service Provider
- Rol: Proveedor externo IA
- Interés: Consumo de API
- Responsabilidades: Ejecutar inferencia y responder

- Nombre: Repository Administrator
- Rol: Administrador técnico
- Interés: Gobierno de repositorios
- Responsabilidades: Configuración, políticas y permisos

- Nombre: Organization Admin
- Rol: Administrador organizacional
- Interés: Seguridad, coste y adopción
- Responsabilidades: Presupuesto, compliance y acceso

## 5. Supuestos y restricciones
### Supuestos
- Disponibilidad de APIs externas de IA.
- GitHub como proveedor inicial prioritario.
- Acceso a diffs mediante credenciales válidas.
- Configuración por repositorio disponible.
- Equipos dispuestos a incorporar revisión automática.

### Restricciones
- Uso obligatorio de HTTPS.
- Autenticación mediante API keys o tokens seguros.
- No persistencia de código por defecto.
- Dependencia de terceros para IA y Git.
- Control de costes obligatorio.
- Tiempo de respuesta objetivo configurable.

## 6. Requisitos funcionales reorganizados
- FR-001: Análisis automático de Pull Requests  
  Ejecutar revisión cuando se cree o actualice un Pull Request.
- FR-002: Generación de informe de análisis  
  Entregar hallazgos priorizados y recomendaciones.
- FR-003: Integración con sistemas Git  
  Consumir eventos, diffs y publicar comentarios.
- FR-004: Ejecución en CI CD  
  Permitir uso en pipelines automatizados.
- FR-005: Motor de prompts modular  
  Gestionar plantillas reutilizables por tecnología.
- FR-006: Soporte Java Spring Boot  
  Aplicar reglas específicas para stack inicial.
- FR-007: API REST de análisis  
  Exponer endpoints para consumo externo.
- FR-008: Publicación de resultados en Pull Requests  
  Comentar directamente sobre cambios analizados.
- FR-009: Configuración por repositorio  
  Parametrizar reglas, límites y comportamiento.
- FR-010: Gestión de errores  
  Reintentos, notificaciones y mensajes operativos.
- FR-011: Gestión de costes y trial  
  Cuotas, límites y modelos de consumo.
- FR-012: Explicabilidad de resultados  
  Justificar hallazgos con contexto técnico.
- FR-013: Feedback de calidad  
  Recoger valoración de utilidad de resultados.
- FR-014: Extensibilidad tecnológica  
  Incorporar nuevos lenguajes y frameworks.
- FR-015: Seguridad en análisis  
  Revisar patrones inseguros y vulnerabilidades.

## 7. Requisitos no funcionales reorganizados
- NFR-001: Escalabilidad  
  Soportar crecimiento de repositorios y ejecuciones concurrentes.
- NFR-002: Rendimiento  
  Tiempo objetivo menor a 60 segundos configurable.
- NFR-003: Alta disponibilidad  
  Servicios resilientes con recuperación ante fallos.
- NFR-004: Seguridad  
  HTTPS, protección de secretos y control de acceso.
- NFR-005: Privacidad de datos  
  Minimización y retención controlada de información.
- NFR-006: Mantenibilidad modular  
  Componentes desacoplados y testeables.
- NFR-007: Extensibilidad  
  Añadir nuevos analizadores con bajo impacto.
- NFR-008: Observabilidad logs  
  Logs estructurados, métricas y alertas.
- NFR-009: Control de costes  
  Monitorización y límites de consumo.
- NFR-010: Trazabilidad de análisis  
  Auditoría de solicitudes y resultados.
- NFR-011: Compatibilidad GitHub y otros Git  
  Modelo adaptable a nuevos proveedores.

## 8. Flujos de negocio preliminares
### FLOW-001: Análisis automático de Pull Request
Descripción  
Un evento del proveedor Git activa el proceso automático de revisión.
Pasos:
1. Se crea o actualiza Pull Request.
2. Git provider envía evento.
3. Se obtiene diff.
4. Se construye prompt.
5. Se invoca IA.
6. Se genera informe.
7. Se publica comentario.

### FLOW-002: Análisis vía API REST
Descripción  
Un cliente externo solicita revisión mediante API.
Pasos:
1. Cliente envía request.
2. Validación de API key.
3. Construcción de prompt.
4. Invocación de IA.
5. Retorno de informe.

## 9. Preguntas abiertas
- Q-001: ¿Qué proveedores de IA se soportarán inicialmente?
- Q-002: ¿Persistir resultados y durante cuánto tiempo?
- Q-003: ¿Cuál será el formato estándar del informe?
- Q-004: ¿Qué mecanismo de autenticación usará CI/CD?
- Q-005: ¿Se soportará multi tenant desde MVP?
- Q-006: ¿Cómo se versionarán prompts y reglas?
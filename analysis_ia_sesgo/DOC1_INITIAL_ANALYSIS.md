# Initial Requirements Analysis
## 1. Resumen ejecutivo
La solución es una plataforma de revisión automática de código basada en IA que se integra con Pull Requests y pipelines de CI/CD. Inicialmente se orienta a proyectos Java Spring Boot, pero debe ser extensible a otras tecnologías. El sistema analiza cambios de código, genera informes, publica comentarios en PR y permite controlar costes y uso de IA, manteniendo seguridad, privacidad y observabilidad.

## 2. Objetivos del proyecto
- Automatizar la revisión de código en Pull Requests.
- Mejorar la calidad del software y reducir defectos.
- Detectar vulnerabilidades y malas prácticas (incluyendo OWASP Top 10).
- Integrarse con CI/CD y proveedores Git (GitHub inicialmente).
- Proveer explicaciones accionables y comprensibles.
- Controlar costes de uso de IA y ofrecer modelos de trial.
- Soportar extensibilidad tecnológica a otros lenguajes y frameworks.

## 3. Alcance
### In scope
- Análisis automático de Pull Requests (FR-001, FR-003, FR-004, FR-008).
- Integración con Git/GitHub y CI/CD (FR-003, FR-004, NFR-011).
- API REST de análisis (FR-007).
- Publicación de comentarios en Pull Requests (FR-008).
- Motor de prompts modular (FR-005, NFR-006, NFR-007).
- Soporte inicial Java Spring Boot (FR-006).
- Detección de seguridad OWASP Top 10 (FR-015, NFR-004, NFR-005).
- Feedback de calidad y explicabilidad (FR-012, FR-013).
- Configuración por repositorio (FR-009).
- Control de costes y trial (FR-011, NFR-009).
- Observabilidad y trazabilidad (NFR-008, NFR-010).
- Extensibilidad tecnológica (FR-014, NFR-007).

### Out of scope
- Plugins de IDE.
- Edición automática de código.
- Gestión completa de repositorios Git.
- Almacenamiento persistente completo de código fuente.
- Sistema completo de gestión de incidencias.

## 4. Actores y stakeholders
- Developer: usuario principal que crea y revisa Pull Requests.
- CI CD System: ejecuta pipelines y dispara análisis.
- Git Provider GitHub: origen de eventos de PR y repositorios.
- AI Service Provider: proveedor externo de modelos de IA.
- Repository Administrator: configura límites, políticas y repositorios.
- Organization Admin: gestiona políticas globales, costes y seguridad.

## 5. Supuestos y restricciones
### Supuestos
- Se usará una API externa de IA para el análisis de código.
- Integración inicial con GitHub Actions como CI/CD principal.
- Enfoque inicial en Java Spring Boot como lenguaje objetivo.
- Configuración por repositorio disponible y gestionable.

### Restricciones
- Uso obligatorio de HTTPS para todas las comunicaciones.
- Autenticación mediante API keys.
- No persistencia de código por defecto, salvo configuración explícita.
- Dependencia de proveedor de IA externo.
- Control de costes obligatorio con límites configurables.

## 6. Requisitos funcionales reorganizados
- FR-001 Análisis automático de Pull Requests.
- FR-002 Generación de informe de análisis.
- FR-003 Integración con sistemas Git.
- FR-004 Ejecución en CI CD.
- FR-005 Motor de prompts modular.
- FR-006 Soporte Java Spring Boot.
- FR-007 API REST de análisis.
- FR-008 Publicación de resultados en Pull Requests.
- FR-009 Configuración por repositorio.
- FR-010 Gestión de errores.
- FR-011 Gestión de costes y trial.
- FR-012 Explicabilidad de resultados.
- FR-013 Feedback de calidad.
- FR-014 Extensibilidad tecnológica.
- FR-015 Seguridad en análisis.

## 7. Requisitos no funcionales reorganizados
- NFR-001 Escalabilidad.
- NFR-002 Rendimiento menor a 60 segundos configurable.
- NFR-003 Alta disponibilidad.
- NFR-004 Seguridad HTTPS API keys protección de datos.
- NFR-005 Privacidad de datos.
- NFR-006 Mantenibilidad modular.
- NFR-007 Extensibilidad.
- NFR-008 Observabilidad logs.
- NFR-009 Control de costes.
- NFR-010 Trazabilidad de análisis.
- NFR-011 Compatibilidad GitHub y otros Git.

## 8. Flujos de negocio preliminares
- FLOW-001 Análisis automático de Pull Request.
- FLOW-002 Análisis vía API REST.
- FLOW-003 Feedback de calidad.
- FLOW-004 Control de costes y activación.

## 9. Preguntas abiertas
- Q-001 Proveedores de IA iniciales.
- Q-002 Persistencia de resultados o no.
- Q-003 Formato estándar de informe.
- Q-004 Autenticación CI CD.
- Q-005 Soporte multi tenant.
- Q-006 Versionado de prompts.

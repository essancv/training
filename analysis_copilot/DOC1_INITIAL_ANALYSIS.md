# Initial Requirements Analysis
## 1. Resumen ejecutivo
La solucion propuesta es una plataforma de revision automatica de codigo basada en inteligencia artificial integrada con Pull Requests y pipelines de CI CD con foco inicial en proyectos Java Spring Boot y capacidad de extenderse a otras tecnologias y lenguajes.
El sistema analizara cambios de codigo en Pull Requests y a traves de una API REST generando informes de calidad seguridad y buenas practicas publicando comentarios accionables y controlando el coste de uso de servicios de IA.

## 2. Objetivos del proyecto
- Automatizar revision de codigo en Pull Requests.
- Mejorar calidad del software y reducir defectos en produccion.
- Detectar vulnerabilidades y malas practicas incluyendo OWASP Top 10.
- Integrarse con CI CD y proveedores Git empezando por GitHub.
- Proveer explicaciones accionables y comprensibles para desarrolladores.
- Controlar costes y uso de IA con limites y configuracion.
- Soportar extensibilidad tecnologica a nuevos lenguajes y frameworks.

## 3. Alcance
### In scope
- Analisis automatico de Pull Requests.
- Integracion con Git y GitHub.
- Integracion con CI CD incluyendo GitHub Actions.
- API REST de analisis.
- Publicacion de comentarios en Pull Requests.
- Motor de prompts modular.
- Soporte inicial Java Spring Boot.
- Deteccion de seguridad OWASP Top 10.
- Feedback de calidad y explicabilidad.
- Configuracion por repositorio.
- Control de costes y uso de IA.
- Observabilidad y trazabilidad de analisis.
- Extensibilidad tecnologica.

### Out of scope
- Plugins de IDE.
- Edicion automatica de codigo.
- Gestion completa de repositorios Git.
- Almacenamiento persistente completo de codigo fuente.
- Sistema completo de gestion de incidencias.

## 4. Actores y stakeholders
- Developer
- CI CD System
- Git Provider GitHub
- AI Service Provider
- Repository Administrator
- Organization Admin

## 5. Supuestos y restricciones
### Supuestos
- Uso de API externa de IA.
- Integracion inicial con GitHub Actions.
- Enfoque inicial Java Spring Boot.
- Configuracion por repositorio disponible.

### Restricciones
- Uso obligatorio de HTTPS.
- Autenticacion mediante API keys.
- No persistencia de codigo por defecto.
- Dependencia de proveedor de IA.
- Control de costes obligatorio.

## 6. Requisitos funcionales reorganizados
- FR 001 Analisis automatico de Pull Requests.
- FR 002 Generacion de informe de analisis.
- FR 003 Integracion con sistemas Git.
- FR 004 Ejecucion en CI CD.
- FR 005 Motor de prompts modular.
- FR 006 Soporte Java Spring Boot.
- FR 007 API REST de analisis.
- FR 008 Publicacion de resultados en Pull Requests.
- FR 009 Configuracion por repositorio.
- FR 010 Gestion de errores.
- FR 011 Gestion de costes y trial.
- FR 012 Explicabilidad de resultados.
- FR 013 Feedback de calidad.
- FR 014 Extensibilidad tecnologica.
- FR 015 Seguridad en analisis.

## 7. Requisitos no funcionales reorganizados
- NFR 001 Escalabilidad.
- NFR 002 Rendimiento menor a 60 segundos configurable.
- NFR 003 Alta disponibilidad.
- NFR 004 Seguridad HTTPS API keys proteccion de datos.
- NFR 005 Privacidad de datos.
- NFR 006 Mantenibilidad modular.
- NFR 007 Extensibilidad.
- NFR 008 Observabilidad logs.
- NFR 009 Control de costes.
- NFR 010 Trazabilidad de analisis.
- NFR 011 Compatibilidad GitHub y otros Git.

## 8. Flujos de negocio preliminares
- FLOW 001 Analisis automatico de Pull Request.
- FLOW 002 Analisis via API REST.
- FLOW 003 Feedback de calidad.
- FLOW 004 Control de costes y activacion.

## 9. Preguntas abiertas
- Q 001 Proveedores de IA iniciales.
- Q 002 Persistencia de resultados o no.
- Q 003 Formato estandar de informe.
- Q 004 Autenticacion CI CD.
- Q 005 Soporte multi tenant.
- Q 006 Versionado de prompts.

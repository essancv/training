# Initial Requirements Analysis

## 1. Resumen ejecutivo
Plataforma de revisión automática de código basada en inteligencia artificial integrada con Pull Requests y CI CD, orientada inicialmente a Java Spring Boot y extensible a múltiples tecnologías.

## 2. Objetivos del proyecto
- Automatizar revisión de código en Pull Requests
- Mejorar calidad del software
- Detectar vulnerabilidades y malas prácticas
- Integración con CI CD y Git
- Proveer explicaciones accionables
- Control de costes y uso de IA
- Soportar extensibilidad tecnológica

## 3. Alcance (in scope / out scope)
### In scope
- Análisis automático de Pull Requests
- Integración con Git GitHub
- Integración CI CD
- API REST de análisis
- Publicación de comentarios en Pull Requests
- Motor de prompts modular
- Soporte inicial Java Spring Boot
- Detección de seguridad OWASP Top 10
- Feedback de calidad
- Configuración por repositorio
- Control de costes
- Observabilidad y trazabilidad
- Extensibilidad tecnológica

### Out of scope
- Plugins de IDE
- Edición automática de código
- Gestión completa de repositorios Git
- Almacenamiento persistente completo de código fuente
- Sistema completo de gestión de incidencias

## 4. Actores y stakeholders
- Developer
- CI CD System
- Git Provider GitHub
- AI Service Provider
- Repository Administrator
- Organization Admin

## 5. Supuestos y restricciones
### Supuestos
- Uso de API externa de IA
- Integración inicial con GitHub Actions
- Enfoque inicial Java Spring Boot
- Configuración por repositorio disponible

### Restricciones
- Uso obligatorio de HTTPS
- Autenticación mediante API keys
- No persistencia de código por defecto
- Dependencia de proveedor de IA
- Control de costes obligatorio

## 6. Requisitos funcionales reorganizados
- FR-001: Análisis automático de Pull Requests
- FR-002: Generación de informe de análisis
- FR-003: Integración con sistemas Git
- FR-004: Ejecución en CI CD
- FR-005: Motor de prompts modular
- FR-006: Soporte Java Spring Boot
- FR-007: API REST de análisis
- FR-008: Publicación de resultados en Pull Requests
- FR-009: Configuración por repositorio
- FR-010: Gestión de errores
- FR-011: Gestión de costes y trial
- FR-012: Explicabilidad de resultados
- FR-013: Feedback de calidad
- FR-014: Extensibilidad tecnológica
- FR-015: Seguridad en análisis

## 7. Requisitos no funcionales reorganizados
- NFR-001: Escalabilidad
- NFR-002: Rendimiento menor a 60 segundos configurable
- NFR-003: Alta disponibilidad
- NFR-004: Seguridad HTTPS API keys protección de datos
- NFR-005: Privacidad de datos
- NFR-006: Mantenibilidad modular
- NFR-007: Extensibilidad
- NFR-008: Observabilidad logs
- NFR-009: Control de costes
- NFR-010: Trazabilidad de análisis
- NFR-011: Compatibilidad GitHub y otros Git

## 8. Flujos de negocio preliminares

### FLOW-001: Análisis automático de Pull Request
Pasos:
1. Se crea o actualiza Pull Request
2. Git provider envía evento
3. Se obtiene diff
4. Se construye prompt
5. Se invoca IA
6. Se genera informe
7. Se publica comentario en Pull Request

### FLOW-002: Análisis vía API REST
Pasos:
1. Cliente envía request
2. Validación de API key
3. Construcción de prompt
4. Invocación de IA
5. Retorno de informe

### FLOW-003: Feedback de calidad
Pasos:
1. Usuario revisa resultados
2. Marca issues o feedback
3. Se almacena feedback
4. Mejora de prompts futura

### FLOW-004: Control de costes y activación
Pasos:
1. Admin configura límites
2. Sistema valida permisos
3. Ejecuta o bloquea análisis

## 9. Preguntas abiertas
- Q-001: Proveedores de IA iniciales
- Q-002: Persistencia de resultados o no
- Q-003: Formato estándar de informe
- Q-004: Autenticación CI CD
- Q-005: Soporte multi tenant
- Q-006: Versionado de prompts
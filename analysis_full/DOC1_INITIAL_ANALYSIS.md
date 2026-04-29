# Initial Requirements Analysis

## 1. Resumen ejecutivo
Plataforma SaaS/API para revisión automática de código con IA integrada en Pull Requests y pipelines CI/CD. El enfoque inicial es Java Spring Boot, con diseño extensible para nuevos lenguajes y frameworks.

## 2. Objetivos del proyecto
- Automatizar revisión de código en Pull Requests
- Reducir defectos y deuda técnica
- Detectar vulnerabilidades y malas prácticas
- Integrarse con Git y CI/CD
- Proveer recomendaciones accionables
- Controlar costes de consumo IA
- Habilitar escalado multi-tecnología

## 3. Alcance
### In scope
- Integración GitHub inicial
- Webhooks de Pull Request
- API REST de análisis
- Publicación de comentarios
- Motor de prompts configurable
- Soporte Java Spring Boot
- Seguridad OWASP Top 10
- Métricas, logs y trazabilidad
- Configuración por repositorio
### Out of scope
- Plugins IDE
- Autofix de código
- Hosting completo Git
- Persistencia total del código fuente
- Sistema ITSM completo

## 4. Actores y stakeholders
- Nombre: Developer
- Rol: Usuario final
- Interés: Feedback rápido y útil
- Responsabilidades: Corregir incidencias
- Nombre: Repository Administrator
- Rol: Administrador repositorio
- Interés: Configuración y políticas
- Responsabilidades: Activación y límites
- Nombre: Organization Admin
- Rol: Gobierno organizacional
- Interés: Coste y seguridad
- Responsabilidades: Licencias y compliance
- Nombre: CI CD System
- Rol: Sistema integrador
- Interés: Automatización
- Responsabilidades: Lanzar análisis
- Nombre: AI Service Provider
- Rol: Proveedor externo
- Interés: Disponibilidad API
- Responsabilidades: Respuesta IA

## 5. Supuestos y restricciones
### Supuestos
- Existencia de APIs GitHub y proveedor IA estables
- Repositorios con permisos configurables
- Conectividad saliente HTTPS
### Restricciones
- HTTPS obligatorio
- API Keys obligatorias
- No persistencia de código por defecto
- Presupuesto máximo configurable
- Dependencia de terceros

## 6. Requisitos funcionales reorganizados
- FR-001: Análisis automático de PR  
  Ejecutar análisis al crear/actualizar Pull Request.
- FR-002: Informe de análisis  
  Generar hallazgos priorizados con explicación.
- FR-003: Integración Git  
  Consumir eventos y diffs de proveedor Git.
- FR-004: Ejecución CI/CD  
  Permitir ejecución en pipelines.
- FR-005: Motor de prompts  
  Plantillas versionadas por tecnología.
- FR-006: Soporte Java Spring Boot  
  Reglas iniciales especializadas.
- FR-007: API REST  
  Endpoint síncrono/asíncrono de análisis.
- FR-008: Comentarios PR  
  Publicar resultados en Pull Request.
- FR-009: Configuración repositorio  
  Umbrales, reglas y límites.
- FR-010: Gestión de errores  
  Reintentos, mensajes y fallback.
- FR-011: Gestión de costes  
  Cuotas, trial y consumo.
- FR-012: Explicabilidad  
  Justificación de recomendaciones.
- FR-013: Feedback calidad  
  Captura valoración usuario.
- FR-014: Extensibilidad  
  Añadir nuevas tecnologías.
- FR-015: Seguridad análisis  
  Escaneo de vulnerabilidades comunes.

## 7. Requisitos no funcionales reorganizados
- NFR-001: Escalabilidad  
  Escalar horizontalmente.
- NFR-002: Rendimiento  
  Respuesta objetivo menor a 60 s configurable.
- NFR-003: Alta disponibilidad  
  Servicios redundantes.
- NFR-004: Seguridad  
  HTTPS, secretos y acceso mínimo.
- NFR-005: Privacidad  
  Minimización de datos.
- NFR-006: Mantenibilidad  
  Arquitectura modular.
- NFR-007: Extensibilidad  
  Plugins de analizadores.
- NFR-008: Observabilidad  
  Logs, métricas y alertas.
- NFR-009: Control de costes  
  Presupuestos y throttling.
- NFR-010: Trazabilidad  
  Auditoría de ejecuciones.
- NFR-011: Compatibilidad Git  
  Preparado para múltiples proveedores.

## 8. Flujos de negocio preliminares
### FLOW-001: Análisis Pull Request
Descripción  
Análisis automático por evento Git.
Pasos:
1. Recibir webhook.
2. Obtener diff.
3. Ejecutar IA.
4. Publicar comentario.
### FLOW-002: Análisis API
Descripción  
Consumo directo por cliente.
Pasos:
1. Validar API key.
2. Procesar payload.
3. Retornar informe.

## 9. Preguntas abiertas
- Q-001: ¿Proveedor IA único o multivendor?
- Q-002: ¿Persistir resultados cuánto tiempo?
- Q-003: ¿Modelo multi-tenant desde fase 1?
- Q-004: ¿Cobro por uso o licencia fija?
- Q-005: ¿Versionado y aprobación de prompts?
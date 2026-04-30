# Resumen ejecutivo
Sistema de apoyo al análisis funcional que transforma requisitos en documentación estructurada automatizable.

# Objetivos
- Reducir tiempo de análisis inicial.
- Estandarizar entregables.
- Integrar backlog con Jira.
- Publicar artefactos en GitHub.

# Alcance In
- Ingesta de requisitos.
- Gestión de prompt maestro.
- Invocación IA.
- Validación JSON.
- Exportación GitHub/Jira.
- Logs y auditoría.

# Alcance Out
- Gestión completa del ciclo de desarrollo.
- CI/CD avanzada.
- Ejecución automática de código generado.
- Portfolio management.

# Stakeholders
- Product Owner
- Analista funcional
- Arquitecto software
- PMO
- Equipo desarrollo
- DevOps
- Auditoría

# RF reorganizados por módulos
- Entrada: FR-001 FR-002
- Motor IA: FR-003 FR-004 FR-005
- Integración: FR-006 FR-007 FR-008
- Gobierno: FR-009 FR-010

# RNF reorganizados
- Calidad: NFR-001 NFR-002
- Seguridad: NFR-003
- Operación: NFR-004 NFR-005

# Flujos preliminares
1. Usuario carga requisitos.
2. Selecciona prompt versión.
3. Sistema llama IA.
4. Sistema valida JSON.
5. Usuario revisa.
6. Publicación GitHub/Jira.

# Supuestos
- APIs accesibles.
- Tokens disponibles.
- Usuario conoce estructura mínima de requisitos.

# Preguntas abiertas
- ¿Necesita RBAC?
- ¿Necesita multitenancy?
- ¿Necesita anonimización de datos?
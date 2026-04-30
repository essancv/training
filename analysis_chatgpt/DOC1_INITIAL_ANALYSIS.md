# Initial Requirements Analysis

## 1. Resumen ejecutivo
Plataforma SaaS de análisis automático de Pull Requests mediante IA, integrada con GitHub y CI/CD, enfocada en calidad, seguridad y eficiencia.

## 2. Objetivos del proyecto
- Automatizar revisión de código
- Detectar vulnerabilidades
- Integración CI/CD
- Feedback accionable
- Control de costes IA

## 3. Alcance
### In scope
- Análisis PR
- Integración GitHub
- API REST
- Motor prompts
- Seguridad OWASP

### Out of scope
- IDE plugins
- Gestión completa repositorios

## 4. Actores y stakeholders
- Developer
- Repo Admin
- CI/CD System
- GitHub
- AI Provider

## 5. Supuestos y restricciones
- API IA externa
- GitHub inicial
- HTTPS obligatorio
- API keys auth

## 6. Requisitos funcionales reorganizados
FR-001 a FR-015 estructurados en análisis, integración, seguridad, costes

## 7. Requisitos no funcionales reorganizados
NFR-001 a NFR-011: escalabilidad, seguridad, observabilidad, rendimiento

## 8. Flujos de negocio preliminares
FLOW-001 a FLOW-004 definidos en base a eventos GitHub y API

## 9. Preguntas abiertas
- Multi-tenant
- Persistencia
- Formato estándar informe
- IA provider inicial
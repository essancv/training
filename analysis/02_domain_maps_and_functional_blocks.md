# Functional Domain Map

## 1. Introducción
Este documento identifica y organiza los bloques funcionales del sistema de revisión automática de código basado en inteligencia artificial, derivado del análisis inicial (DOC1). El objetivo es estructurar el sistema en dominios funcionales coherentes para facilitar diseño, desarrollo y evolución.

## 2. Lista de bloques funcionales

### DOMAIN-001: Motor de Análisis de Pull Requests
- Descripción: Responsable de analizar automáticamente Pull Requests mediante el procesamiento de diffs y la generación de insights de calidad de código.
- Requisitos asociados:
  - FR-001, FR-002, FR-012, FR-015
  - NFR-002, NFR-010
- Complejidad: alta
- Dependencias con otros bloques:
  - DOMAIN-002 (Git Integration)
  - DOMAIN-004 (AI Prompt Engine)

### DOMAIN-002: Integración con Git
- Descripción: Gestiona la interacción con proveedores Git (GitHub), incluyendo eventos de Pull Request y publicación de comentarios.
- Requisitos asociados:
  - FR-003, FR-008
  - NFR-011
- Complejidad: alta
- Dependencias con otros bloques:
  - DOMAIN-001
  - DOMAIN-006

### DOMAIN-003: Integración CI/CD
- Descripción: Permite la ejecución del análisis dentro de pipelines de CI/CD.
- Requisitos asociados:
  - FR-004
  - NFR-003
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-001
  - DOMAIN-005

### DOMAIN-004: Motor de Prompts e IA
- Descripción: Construcción, versionado y ejecución de prompts hacia proveedores de IA externos.
- Requisitos asociados:
  - FR-005, FR-012, FR-014
  - NFR-009
- Complejidad: alta
- Dependencias con otros bloques:
  - DOMAIN-001
  - DOMAIN-007

### DOMAIN-005: API de Análisis
- Descripción: Expone endpoints REST para ejecutar análisis fuera del flujo de CI/CD.
- Requisitos asociados:
  - FR-007, FR-010
  - NFR-004
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-001
  - DOMAIN-012

### DOMAIN-006: Publicación de Resultados y Feedback
- Descripción: Gestiona la publicación de resultados en Pull Requests y la captura de feedback del usuario.
- Requisitos asociados:
  - FR-008, FR-013
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-002
  - DOMAIN-001

### DOMAIN-007: Configuración por Repositorio
- Descripción: Permite definir reglas, límites y comportamiento del sistema a nivel de repositorio.
- Requisitos asociados:
  - FR-009, FR-011
  - NFR-009
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-004
  - DOMAIN-001

### DOMAIN-008: Gestión de Costes
- Descripción: Controla el consumo de IA, límites de uso y activación basada en presupuesto.
- Requisitos asociados:
  - FR-011
  - NFR-009
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-004
  - DOMAIN-007

### DOMAIN-009: Análisis de Seguridad
- Descripción: Detección de vulnerabilidades y malas prácticas (incluyendo OWASP Top 10).
- Requisitos asociados:
  - FR-015
  - NFR-005
- Complejidad: alta
- Dependencias con otros bloques:
  - DOMAIN-001
  - DOMAIN-004

### DOMAIN-010: Observabilidad y Trazabilidad
- Descripción: Registro de logs, métricas y trazas de análisis ejecutados.
- Requisitos asociados:
  - NFR-008, NFR-010
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-001
  - DOMAIN-003

### DOMAIN-011: Extensibilidad Tecnológica
- Descripción: Permite extender el sistema a nuevas tecnologías más allá de Java Spring Boot.
- Requisitos asociados:
  - FR-014
  - NFR-007
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-004
  - DOMAIN-001

### DOMAIN-012: Seguridad y Autenticación
- Descripción: Manejo de API keys, autenticación y protección de endpoints.
- Requisitos asociados:
  - NFR-004
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-005

### DOMAIN-013: Gestión de Errores y Resiliencia
- Descripción: Manejo de fallos en integración con IA, Git y CI/CD.
- Requisitos asociados:
  - FR-010
- Complejidad: media
- Dependencias con otros bloques:
  - DOMAIN-001
  - DOMAIN-004

## 3. Mapa general de dominios

| Dominio | Tipo | Dependencias |
|--------|------|--------------|
| DOMAIN-001 | Core | DOMAIN-002, DOMAIN-004 |
| DOMAIN-002 | Integration | DOMAIN-001, DOMAIN-006 |
| DOMAIN-003 | Execution | DOMAIN-001, DOMAIN-005 |
| DOMAIN-004 | Core AI | DOMAIN-001, DOMAIN-007 |
| DOMAIN-005 | API | DOMAIN-012 |
| DOMAIN-006 | Output | DOMAIN-002, DOMAIN-001 |
| DOMAIN-007 | Configuration | DOMAIN-004 |
| DOMAIN-008 | Governance | DOMAIN-004, DOMAIN-007 |
| DOMAIN-009 | Analysis | DOMAIN-001, DOMAIN-004 |
| DOMAIN-010 | Observability | DOMAIN-001, DOMAIN-003 |
| DOMAIN-011 | Architecture | DOMAIN-004, DOMAIN-001 |
| DOMAIN-012 | Security | DOMAIN-005 |
| DOMAIN-013 | Resilience | DOMAIN-001, DOMAIN-004 |

## 4. Observaciones
- El sistema presenta una arquitectura altamente modular con fuerte dependencia del motor de IA.
- Los dominios críticos son el análisis de Pull Requests y el motor de prompts.
- La gestión de costes y seguridad son transversales a múltiples dominios.
- Existen dependencias circulares potenciales entre análisis, prompts y configuración que deben controlarse en diseño técnico.
- La extensibilidad es un objetivo arquitectónico clave para futuras tecnologías.
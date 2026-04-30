# Functional Domain Map

## 1. Introducción
Este mapa organiza el sistema en dominios funcionales alineados con las cuatro capas objetivo y con componentes de soporte.

## 2. Bloques funcionales
### DOMAIN-001
- Descripción: Ingestion de eventos de Pull Request y orquestacion del flujo.
- Requisitos asociados: FR-001 FR-002 FR-007
- Complejidad: Media
- Dependencias: GitHub API GitHub Actions

### DOMAIN-002
- Descripción: Extraccion de diff y contexto de repositorio y clasificacion del proyecto.
- Requisitos asociados: FR-002 FR-003 FR-010
- Complejidad: Media
- Dependencias: GitHub API reglas de configuracion

### DOMAIN-003
- Descripción: Motor de prompts por capas y composicion dinamica.
- Requisitos asociados: FR-004 NFR-004
- Complejidad: Media
- Dependencias: Biblioteca de prompts configuracion

### DOMAIN-004
- Descripción: Analisis de calidad y seguridad combinando herramientas deterministas y LLM.
- Requisitos asociados: FR-005 FR-006 NFR-001
- Complejidad: Media
- Dependencias: LLM provider herramientas SAST SCA

### DOMAIN-005
- Descripción: Generacion de reporte estructurado y publicacion en PR.
- Requisitos asociados: FR-006 FR-007 NFR-003
- Complejidad: Baja
- Dependencias: GitHub API plantilla de reporte

### DOMAIN-006
- Descripción: Checklist humano y politicas de aprobacion.
- Requisitos asociados: FR-007
- Complejidad: Baja
- Dependencias: Plantilla de checklist

### DOMAIN-007
- Descripción: Observabilidad metricas logs auditoria.
- Requisitos asociados: FR-008 NFR-005
- Complejidad: Baja
- Dependencias: Sistema de metricas

## 3. Mapa general de dominios

flowchart TD
PR_EVENT['Pull Request Event'] --> DOMAIN001['Process Layer']
DOMAIN001 --> DOMAIN002['Diff And Context']
DOMAIN002 --> DOMAIN003['Prompt Engine']
DOMAIN003 --> DOMAIN004['Quality And Security']
DOMAIN004 --> DOMAIN005['Structured Report']
DOMAIN005 --> PR_COMMENT['Pull Request Comment']
DOMAIN001 --> DOMAIN006['Checklist Gate']
DOMAIN005 --> DOMAIN007['Metrics And Audit']


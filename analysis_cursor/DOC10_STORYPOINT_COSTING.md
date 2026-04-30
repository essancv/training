# Story Point Costing (DOC10)

## 1. Relación entre sizing (horas) y Story Points
Mapa inicial para el equipo.

- 1 SP: 6 h
- 2 SP: 12 h
- 3 SP: 18 h
- 5 SP: 30 h
- 8 SP: 48 h
- 13 SP: 78 h

## 2. Estimación por historia
### STORY-001
- Horas estimadas: 60
- SP estimados: 13
- Justificación: Workflow de PR triggers idempotencia y permisos
- Complejidad técnica: Media
- Dependencias: FR-001 FR-007

### STORY-002
- Horas estimadas: 70
- SP estimados: 13
- Justificación: Extraccion diff limites y manejo de archivos
- Complejidad técnica: Media
- Dependencias: FR-002 NFR-002

### STORY-003
- Horas estimadas: 80
- SP estimados: 13
- Justificación: Capas de prompts y configuracion por tecnologia
- Complejidad técnica: Media
- Dependencias: FR-004 NFR-004

### STORY-004
- Horas estimadas: 50
- SP estimados: 8
- Justificación: Esquema de reporte severidades y publicacion
- Complejidad técnica: Baja
- Dependencias: FR-006 FR-007

### STORY-005
- Horas estimadas: 120
- SP estimados: 13
- Justificación: Integracion SAST SCA redaccion y hallazgos OWASP
- Complejidad técnica: Media Alta
- Dependencias: NFR-001 NFR-004

### STORY-006
- Horas estimadas: 30
- SP estimados: 5
- Justificación: Definir checklist y criterios
- Complejidad técnica: Baja
- Dependencias: FR-007

### STORY-007
- Horas estimadas: 40
- SP estimados: 8
- Justificación: Logs metricas auditoria basica
- Complejidad técnica: Baja
- Dependencias: FR-008 NFR-005

## 3. Coste total del backlog
- Horas totales: 450
- SP totales: 73

## 4. Observaciones y riesgos
- SP puede variar por decisiones de proveedor LLM y politicas de seguridad.
- PR grandes pueden multiplicar latencia y coste si no se implementan limites.

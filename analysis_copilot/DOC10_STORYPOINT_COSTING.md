# Story Point Costing DOC10
## 1. Relacion entre sizing horas y Story Points
Para un equipo con velocidad media se propone la siguiente relacion orientativa entre esfuerzo en horas y Story Points
- 1 a 4 horas aproximadamente 1 a 2 Story Points.
- 4 a 8 horas aproximadamente 3 a 5 Story Points.
- 8 a 16 horas aproximadamente 5 a 8 Story Points.

## 2. Estimacion por historia
### STORY 001
- Horas estimadas
  - 12 horas por integracion de eventos GitHub CI CD y disparo de analisis.
- SP estimados
  - 5 Story Points.
- Justificacion
  - Requiere integracion con webhooks configuracion y pruebas de extremo a extremo.
- Complejidad tecnica
  - Media.
- Dependencias
  - Integracion con proveedor Git y CI CD.

### STORY 002
- Horas estimadas
  - 10 horas para generar informe estructurado y exponerlo via API y comentarios.
- SP estimados
  - 5 Story Points.
- Justificacion
  - Incluye diseño de formato de informe y mapeo de resultados de IA.
- Complejidad tecnica
  - Media.
- Dependencias
  - Motor de analisis con IA.

### STORY 003
- Horas estimadas
  - 8 horas para configuracion de limites de coste por repositorio.
- SP estimados
  - 3 Story Points.
- Justificacion
  - Incluye modelo de datos validaciones y logica de bloqueo.
- Complejidad tecnica
  - Media.
- Dependencias
  - Modulo de configuracion y base de datos.

### STORY 004
- Horas estimadas
  - 8 horas para trazabilidad basica de analisis y uso.
- SP estimados
  - 3 Story Points.
- Justificacion
  - Incluye almacenamiento de metadatos y endpoints de consulta.
- Complejidad tecnica
  - Media.
- Dependencias
  - Base de datos y modulos de analisis.

## 3. Coste total del backlog
- Suma aproximada de Story Points iniciales
  - STORY 001 5 SP.
  - STORY 002 5 SP.
  - STORY 003 3 SP.
  - STORY 004 3 SP.
  - Total 16 Story Points para el nucleo inicial.

## 4. Observaciones y riesgos
- Las estimaciones son iniciales y deben refinarse en sesiones de planning con el equipo.
- Cambios en requisitos de seguridad o proveedores de IA pueden aumentar el esfuerzo.

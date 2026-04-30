# Initial Product Backlog

## 1. Épicas
- EPIC-001 PR workflow and commenting
- EPIC-002 Prompt engine and configuration
- EPIC-003 Quality and security analysis
- EPIC-004 Checklist and governance
- EPIC-005 Observability and audit

## 2. Features
- FEAT-001 GitHub PR trigger and diff retrieval
- FEAT-002 Structured report and PR comment publishing
- FEAT-003 Layered prompt builder
- FEAT-004 Static analysis integration for style and smells
- FEAT-005 Security scanning integration and secure redaction
- FEAT-006 Human checklist template and enforcement guidance
- FEAT-007 Metrics logging and audit trail

## 3. Historias de usuario
### STORY-001
Como Developer quiero que al abrir o actualizar una PR se ejecute automaticamente el analisis para recibir feedback temprano.

### STORY-002
Como System quiero extraer el diff y archivos modificados de forma consistente para analizar solo lo relevante.

### STORY-003
Como Tech Lead quiero que el prompt se construya por capas para asegurar consistencia y adaptacion por tecnologia.

### STORY-004
Como Reviewer quiero recibir un reporte estructurado con severidades y recomendaciones para acelerar la revision.

### STORY-005
Como Security Champion quiero que se detecten riesgos OWASP y dependencias vulnerables para reducir vulnerabilidades.

### STORY-006
Como Reviewer quiero un checklist estandar para no depender solo del LLM.

### STORY-007
Como DevOps quiero logs y metricas basicas para auditar ejecuciones y tiempos.

## 4. Dependencias
- STORY-001 depende de STORY-002
- STORY-004 depende de STORY-003 y STORY-002
- STORY-005 depende de STORY-002
- STORY-007 depende de STORY-001

## 5. Priorización MoSCoW
- Must: STORY-001 STORY-002 STORY-003 STORY-004 STORY-005
- Should: STORY-006 STORY-007
- Could: Panel UI y analisis avanzado multi stack
- Wont: Microservicios y orquestacion compleja en MVP

## 6. Registro de dudas, acciones y refinamientos para JIRA
### JIRA-ITEM-001
- Origen (DOC00–DOC08): DOC00
- Tipo: refinamiento
- Descripción: Definir severidades y criterios de bloqueo para hallazgos
- Impacto: Afecta aprobacion y experiencia de equipo
- Prioridad sugerida: Alta
- Propuesta de resolución: Catalogo de hallazgos con niveles y ejemplos

### JIRA-ITEM-002
- Origen (DOC00–DOC08): DOC1
- Tipo: duda
- Descripción: Definir proveedor LLM permitido y politica de privacidad
- Impacto: Riesgo de compliance
- Prioridad sugerida: Alta
- Propuesta de resolución: Evaluacion legal y tecnica y contrato de no retencion

### JIRA-ITEM-003
- Origen (DOC00–DOC08): DOC7
- Tipo: acción
- Descripción: Implementar redaccion de secretos y datos sensibles antes de enviar al LLM
- Impacto: Reduce riesgo de filtracion
- Prioridad sugerida: Alta
- Propuesta de resolución: Libreria de patrones de secretos y allowlist de archivos

# Initial Product Backlog

## 1. Épicas
### EPIC-001
- Descripción: Captura de requisitos y experiencia de usuario.

### EPIC-002
- Descripción: Motor IA y generación JSON.

### EPIC-003
- Descripción: Integraciones GitHub y Jira.

### EPIC-004
- Descripción: Gobierno, seguridad y observabilidad.

## 2. Features
### FEAT-001
- Relacionada con EPIC-001

### FEAT-002
- Relacionada con EPIC-002

### FEAT-003
- Relacionada con EPIC-003

### FEAT-004
- Relacionada con EPIC-004

## 3. Historias de usuario
### STORY-001
- Como usuario solicitante quiero introducir requisitos para obtener un análisis automático
- Criterios de aceptación:
  - Given requisitos informados  
  - When lanzo procesamiento  
  - Then recibo JSON válido  

### STORY-002
- Como Product Owner quiero disponer de backlog inicial para acelerar discovery
- Criterios de aceptación:
  - Given análisis completado  
  - When reviso backlog  
  - Then existen épicas e historias trazadas  

### STORY-003
- Como administrador quiero publicar resultados en GitHub automáticamente para centralizar documentación
- Criterios de aceptación:
  - Given credenciales válidas  
  - When ejecuto script Python  
  - Then se crean artefactos en GitHub  

### STORY-004
- Como administrador quiero crear backlog en Jira automáticamente para iniciar delivery
- Criterios de aceptación:
  - Given proyecto Jira configurado  
  - When ejecuto script Python  
  - Then se crean épicas e historias  

## 4. Dependencias
- STORY-001 depende de FEAT-001 y FEAT-002
- STORY-002 depende de FEAT-002
- STORY-003 depende de FEAT-003
- STORY-004 depende de FEAT-003

## 5. Priorización MoSCoW
- Must: Captura requisitos, generación JSON, validación schema, exportación GitHub/Jira.
- Should: UI web, versionado prompts, métricas de uso.
- Could: Multi idioma, multi proveedor IA, plantillas sectoriales.
- Won't: Generación automática de código productivo en MVP.
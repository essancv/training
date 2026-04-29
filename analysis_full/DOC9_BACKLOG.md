# Initial Product Backlog

## 1. Épicas
### EPIC-001
- Descripción: Integración GitHub y Pull Requests.

### EPIC-002
- Descripción: Motor IA y generación de informes.

### EPIC-003
- Descripción: Gobierno, costes y observabilidad.

### EPIC-004
- Descripción: Extensibilidad tecnológica.

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
- Como Developer quiero recibir comentarios automáticos en mi Pull Request para corregir antes del merge
- Criterios de aceptación:
  - Given existe un PR abierto  
  - When finaliza el análisis  
  - Then se publica comentario con hallazgos  

### STORY-002
- Como CI CD System quiero invocar la API de análisis para validar código en pipeline
- Criterios de aceptación:
  - Given una API key válida  
  - When llamo al endpoint  
  - Then recibo informe estructurado  

### STORY-003
- Como Organization Admin quiero establecer límites de consumo para controlar presupuesto
- Criterios de aceptación:
  - Given una organización activa  
  - When defino cuota mensual  
  - Then el sistema bloquea excesos  

### STORY-004
- Como Repository Administrator quiero configurar reglas por repositorio para adaptar análisis
- Criterios de aceptación:
  - Given acceso administrador  
  - When guardo configuración  
  - Then se aplica al siguiente análisis  

## 4. Dependencias
- STORY-001 depende de FEAT-001 y FEAT-002
- STORY-002 depende de FEAT-002
- STORY-003 depende de FEAT-003
- STORY-004 depende de FEAT-003

## 5. Priorización MoSCoW
- Must: Integración GitHub, análisis IA, comentarios PR, API REST básica, cuotas básicas.
- Should: Feedback usuario, métricas avanzadas, soporte multi-modelo.
- Could: GitLab/Bitbucket, dashboards premium, benchmarking.
- Won't: Plugin IDE y autofix en MVP.
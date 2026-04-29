# Initial Product Backlog

## 1. Épicas
### EPIC-001
- Descripción: Integración GitHub PR
### EPIC-002
- Descripción: Motor IA de análisis
### EPIC-003
- Descripción: Gobierno, costes y métricas

## 2. Features
### FEAT-001
- Relacionada con EPIC-001
### FEAT-002
- Relacionada con EPIC-002
### FEAT-003
- Relacionada con EPIC-003

## 3. Historias de usuario
### STORY-001
- Como Developer quiero recibir comentarios automáticos en mi PR para corregir antes del merge
- Criterios de aceptación:
  - Given PR nuevo
  - When se complete análisis
  - Then se publica comentario
### STORY-002
- Como Admin quiero definir límites de consumo para controlar presupuesto
- Criterios de aceptación:
  - Given organización activa
  - When configuro cuota
  - Then el sistema bloquea excesos

## 4. Dependencias
- STORY-001 depende de FEAT-001 y FEAT-002
- STORY-002 depende de FEAT-003

## 5. Priorización MoSCoW
- Must: GitHub PR, análisis IA, comentarios, cuotas básicas
- Should: métricas, feedback usuario, multi modelo
- Could: multi Git provider, dashboards avanzados
- Won't: plugins IDE en MVP
# Initial Product Backlog

## 1. Épicas

### EPIC-001: Revisión automática de Pull Requests
- Descripción: Permitir análisis automático de código al crear o actualizar Pull Requests.

### EPIC-002: Plataforma de análisis vía API
- Descripción: Exponer capacidades de análisis mediante API REST segura.

### EPIC-003: Seguridad y calidad de código
- Descripción: Detectar vulnerabilidades, malas prácticas y problemas de calidad.

### EPIC-004: Gestión de configuración y costes
- Descripción: Permitir configuración por repositorio y control del consumo de IA.

### EPIC-005: Observabilidad y mejora continua
- Descripción: Trazabilidad, métricas y feedback para evolución del producto.

## 2. Features

### FEAT-001: Webhook de Pull Request
- Relacionada con EPIC-001

### FEAT-002: Obtención de diff y contexto del PR
- Relacionada con EPIC-001

### FEAT-003: Publicación de comentarios automáticos en PR
- Relacionada con EPIC-001

### FEAT-004: Endpoint REST de análisis manual
- Relacionada con EPIC-002

### FEAT-005: Autenticación por API key
- Relacionada con EPIC-002

### FEAT-006: Reglas de calidad y seguridad OWASP Top 10
- Relacionada con EPIC-003

### FEAT-007: Explicaciones accionables del análisis
- Relacionada con EPIC-003

### FEAT-008: Configuración por repositorio
- Relacionada con EPIC-004

### FEAT-009: Límites de consumo y cuotas
- Relacionada con EPIC-004

### FEAT-010: Logging, métricas y trazabilidad
- Relacionada con EPIC-005

### FEAT-011: Captura de feedback de usuarios
- Relacionada con EPIC-005

## 3. Historias de usuario

### STORY-001
- Como developer quiero que se analice automáticamente mi Pull Request para detectar errores antes del merge.
- Criterios de aceptación:
  - Given un Pull Request creado o actualizado
  - When el sistema recibe el evento
  - Then se ejecuta un análisis automático y se publica el resultado

### STORY-002
- Como developer quiero recibir comentarios claros en el Pull Request para corregir incidencias rápidamente.
- Criterios de aceptación:
  - Given un análisis completado
  - When existen hallazgos
  - Then se publican comentarios comprensibles y accionables

### STORY-003
- Como administrador de repositorio quiero configurar reglas por repositorio para adaptar el análisis al contexto del proyecto.
- Criterios de aceptación:
  - Given un repositorio registrado
  - When modifico la configuración
  - Then el siguiente análisis utiliza la nueva configuración

### STORY-004
- Como usuario técnico quiero lanzar análisis vía API para integrarlo con herramientas externas.
- Criterios de aceptación:
  - Given una API key válida
  - When envío una solicitud de análisis
  - Then recibo un informe estructurado en la respuesta

### STORY-005
- Como organization admin quiero establecer límites de uso para controlar costes del proveedor IA.
- Criterios de aceptación:
  - Given una cuota configurada
  - When se supera el límite
  - Then el sistema bloquea nuevas ejecuciones o alerta según configuración

### STORY-006
- Como developer quiero reportar feedback sobre falsos positivos para mejorar la calidad del sistema.
- Criterios de aceptación:
  - Given un resultado publicado
  - When marco un hallazgo como incorrecto
  - Then el feedback queda registrado para revisión futura

### STORY-007
- Como equipo de operaciones quiero disponer de logs y métricas para monitorizar la plataforma.
- Criterios de aceptación:
  - Given una ejecución de análisis
  - When finaliza o falla
  - Then quedan registradas métricas, logs y estado

## 4. Dependencias

- FEAT-001 depende de integración con GitHub/Git Provider.
- FEAT-002 depende de permisos de acceso al repositorio.
- FEAT-003 depende de FEAT-001 y FEAT-006.
- FEAT-004 depende de FEAT-005.
- FEAT-006 depende del motor de prompts y proveedor IA.
- FEAT-008 depende de persistencia de configuración.
- FEAT-009 depende de métricas de consumo IA.
- FEAT-010 depende de infraestructura de observabilidad.
- FEAT-011 depende de FEAT-003.

## 5. Priorización MoSCoW

| ID | Elemento | Prioridad |
|----|----------|-----------|
| FEAT-001 | Webhook de Pull Request | Must |
| FEAT-002 | Obtención de diff | Must |
| FEAT-003 | Comentarios automáticos en PR | Must |
| FEAT-004 | Endpoint REST análisis | Should |
| FEAT-005 | API key | Must |
| FEAT-006 | Reglas calidad y seguridad | Must |
| FEAT-007 | Explicaciones accionables | Should |
| FEAT-008 | Configuración por repositorio | Should |
| FEAT-009 | Límites de consumo | Must |
| FEAT-010 | Observabilidad | Must |
| FEAT-011 | Feedback usuarios | Could |
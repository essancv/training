# Initial Product Backlog
## 1. Épicas
- EPIC-001 Plataforma de análisis automático de Pull Requests.
- EPIC-002 Integración Git y CI/CD.
- EPIC-003 Control de costes y configuración por repositorio.
- EPIC-004 Explicabilidad, feedback y observabilidad.

## 2. Features
- FEAT-001 Análisis de PR y publicación de comentarios (EPIC-001).
- FEAT-002 API REST de análisis bajo demanda (EPIC-001).
- FEAT-003 Integración con GitHub y GitHub Actions (EPIC-002).
- FEAT-004 Configuración por repositorio y límites de coste (EPIC-003).
- FEAT-005 Feedback de calidad y mejora de prompts (EPIC-004).
- FEAT-006 Observabilidad y trazabilidad de análisis (EPIC-004).

## 3. Historias de usuario
### STORY-001 Análisis automático de PR en GitHub
- Feature: FEAT-001.
- Como Developer quiero que al crear o actualizar un Pull Request en GitHub se ejecute automáticamente un análisis de código para recibir comentarios sobre problemas y mejoras.
- Aceptación:
  - Cuando se crea o actualiza un PR, el sistema recibe el evento y ejecuta el análisis.
  - Se publica un comentario en el PR con un resumen y enlaces a detalles.
  - El tiempo de respuesta cumple NFR-002 para PR de tamaño estándar.

### STORY-002 Informe detallado de análisis
- Feature: FEAT-001.
- Como Developer quiero ver un informe estructurado de los hallazgos para entender qué debo corregir.
- Aceptación:
  - El informe incluye severidad, tipo de problema y ubicación.
  - El informe se asocia al PR y es accesible desde el comentario.

### STORY-003 Análisis vía API REST
- Feature: FEAT-002.
- Como Cliente externo quiero invocar una API REST para analizar código sin depender de PR.

### STORY-004 Configuración de límites de coste por repositorio
- Feature: FEAT-004.
- Como Repository Administrator quiero configurar límites de uso de IA por repositorio para controlar costes.

### STORY-005 Registro de feedback sobre resultados
- Feature: FEAT-005.
- Como Developer quiero marcar resultados como útiles o no útiles para mejorar futuros análisis.

### STORY-006 Observabilidad de análisis
- Feature: FEAT-006.
- Como Organization Admin quiero ver métricas y logs de análisis para entender uso, errores y costes.

## 4. Dependencias
- STORY-001 depende de FEAT-003 (integración GitHub/GitHub Actions).
- STORY-004 depende de la implementación de FR-011 y NFR-009.
- STORY-006 depende de la infraestructura de observabilidad.

## 5. Priorización MoSCoW
- Must: STORY-001, STORY-002, STORY-003, STORY-004.
- Should: STORY-005, STORY-006.
- Could: extensiones a otros proveedores Git y lenguajes.
- Won't (por ahora): plugins de IDE, edición automática de código.

## 6. Registro de dudas, acciones y refinamientos para JIRA
### JIRA-ITEM-001
- Origen (DOC00–DOC08): DOC00, DOC1, DOC3, DOC4.
- Tipo: aclaración.
- Descripción: Definir formato estándar de informe de análisis (estructura, campos, severidades, enlaces).
- Impacto: Alto en UX y trazabilidad.
- Prioridad sugerida: Alta.
- Propuesta de resolución: Taller de diseño con stakeholders para acordar plantilla de informe y reflejarla en FR-002 y NFR-010.

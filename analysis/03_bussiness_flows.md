# Business Flows

## 1. Introducción
Este documento describe los flujos de negocio del sistema de revisión automática de código basado en inteligencia artificial, derivados de DOC1 y los dominios funcionales de DOC2.

## 2. Flujos de negocio

### FLOW-001: Análisis automático de Pull Request
**Actor principal:** Git Provider (GitHub) / CI CD System
**Objetivo:** Ejecutar análisis automático de código en Pull Requests
**Descripción:** Flujo principal activado por eventos de Pull Request para análisis de código
**Flujo principal:**
1. Se crea o actualiza Pull Request
2. Git Provider envía evento al sistema
3. Se obtiene diff del Pull Request
4. Se construye prompt de análisis
5. Se invoca servicio de IA
6. Se genera informe de análisis
7. Se publica comentario en Pull Request
**Flujos alternativos:**
- Fallo en obtención de diff -> registro de error
- Fallo en IA -> reintento o error controlado
**Eventos clave:**
- PR Created or Updated
- Analysis Started
- Analysis Completed
- Analysis Failed

`mermaid
flowchart TD
A[Pull Request] --> B[Git Event]
B --> C[Get Diff]
C --> D[Build Prompt]
D --> E[AI Service]
E --> F[Generate Report]
F --> G[Post Comment]
`

### FLOW-002: Análisis vía API REST
**Actor principal:** Developer / External Client
**Objetivo:** Permitir ejecución de análisis bajo demanda
**Descripción:** Exposición de análisis mediante API REST
**Flujo principal:**
1. Cliente envía request a API
2. Validación de API key
3. Construcción de prompt
4. Invocación de IA
5. Generación de informe
6. Respuesta al cliente
**Flujos alternativos:**
- API key inválida -> rechazo
- Request inválido -> error de validación
**Eventos clave:**
- API Request Received
- Authentication Result
- Response Generated

`mermaid
flowchart TD
A[Client Request] --> B[API Gateway]
B --> C[Validate Key]
C --> D[Build Prompt]
D --> E[AI Service]
E --> F[Return Response]
`

### FLOW-003: Publicación de resultados y feedback
**Actor principal:** Developer / Repository Admin
**Objetivo:** Publicar resultados y capturar feedback
**Descripción:** Gestión de resultados de análisis y retroalimentación
**Flujo principal:**
1. Se genera resultado de análisis
2. Se publica comentario en Pull Request
3. Usuario revisa resultado
4. Usuario envía feedback
5. Se almacena feedback
**Flujos alternativos:**
- Error en publicación -> reintento
- Sin feedback -> cierre del flujo
**Eventos clave:**
- Result Published
- Feedback Submitted
- Feedback Stored

`mermaid
flowchart TD
A[Analysis Result] --> B[Post Comment]
B --> C[User Review]
C --> D[Submit Feedback]
D --> E[Store Feedback]
`

### FLOW-004: Control de costes y activación de análisis
**Actor principal:** Organization Admin / Repository Admin
**Objetivo:** Controlar uso de IA y costes asociados
**Descripción:** Validación de límites antes de ejecutar análisis
**Flujo principal:**
1. Admin configura límites de uso
2. Se recibe solicitud de análisis
3. Se valida cuota disponible
4. Se autoriza o bloquea ejecución
**Flujos alternativos:**
- Cuota agotada -> bloqueo de ejecución
- Configuración inválida -> uso de valores por defecto
**Eventos clave:**
- Cost Limit Configured
- Cost Check Executed
- Execution Allowed or Blocked

`mermaid
flowchart TD
A[Analysis Request] --> B[Check Cost]
B --> C{Quota Available}
C -->|Yes| D[Execute Analysis]
C -->|No| E[Block Execution]
`

## 3. Resumen de dependencias
| Flujo | Dependencias |
|------|--------------|
| FLOW-001 | Git Integration, AI Engine, Cost Management |
| FLOW-002 | API Layer, Security, AI Engine |
| FLOW-003 | Git Integration, Observability |
| FLOW-004 | Cost Management, Configuration |
| Todos | Observability, Error Handling |
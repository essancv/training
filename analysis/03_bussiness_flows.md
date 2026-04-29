# Business Flows

## 1. Introducción
Este documento describe los principales flujos de negocio del sistema de revisión automática de código basado en inteligencia artificial, derivados de DOC1 y los dominios funcionales de DOC2.

## 2. Flujos de negocio

### FLOW-001: Análisis automático de Pull Request
**Actor principal:** Git Provider (GitHub) / CI CD System
**Objetivo:** Ejecutar análisis automático de código en Pull Requests
**Descripción:** Flujo principal de análisis automático activado por eventos de Pull Request
**Flujo principal:**
1. Se crea o actualiza Pull Request
2. Git Provider envía evento al sistema
3. Se obtiene diff del Pull Request
4. Se construye prompt de análisis
5. Se invoca servicio de IA
6. Se genera informe de análisis
7. Se publica comentario en Pull Request
**Flujos alternativos:**
- Fallo en obtención de diff -> registro de error y finalización
- Fallo en IA -> reintento o error controlado
- Coste excedido -> bloqueo de ejecución
**Eventos clave:**
- PR Created/Updated
- Analysis Started
- Analysis Completed
- Analysis Failed

mermaid
graph TD
A[Pull Request Created/Updated] --> B[Git Provider Event]
B --> C[Fetch Diff]
C --> D[Build Prompt]
D --> E[Call AI Service]
E --> F[Generate Analysis Report]
F --> G[Post Comment in PR]


### FLOW-002: Análisis vía API REST
**Actor principal:** Developer / External Client
**Objetivo:** Permitir análisis bajo demanda mediante API
**Descripción:** Flujo de ejecución de análisis mediante llamada REST
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
- Timeout IA -> error controlado
**Eventos clave:**
- API Request Received
- Authentication Success/Failure
- Response Generated

mermaid
flowchart TD
A[Client Request] --> B[API Gateway]
B --> C[Validate API Key]
C --> D[Build Prompt]
D --> E[Call AI Service]
E --> F[Return Response]


### FLOW-003: Publicación de resultados y feedback
**Actor principal:** Developer / Repository Admin
**Objetivo:** Publicar resultados y capturar feedback
**Descripción:** Gestión de resultados de análisis y retroalimentación
**Flujo principal:**
1. Se genera resultado de análisis
2. Se publica comentario en PR
3. Usuario revisa resultado
4. Usuario envía feedback
5. Se almacena feedback
**Flujos alternativos:**
- Error publicación -> reintento
- Sin feedback -> cierre de flujo
**Eventos clave:**
- Result Published
- Feedback Submitted
- Feedback Stored

mermaid
flowchart TD
A[Analysis Result Generated] --> B[Post Comment in PR]
B --> C[User Reviews]
C --> D[Submit Feedback]
D --> E[Store Feedback]


### FLOW-004: Control de costes y activación
**Actor principal:** Organization Admin
**Objetivo:** Controlar uso y costes del sistema
**Descripción:** Validación de límites antes de ejecutar análisis
**Flujo principal:**
1. Admin configura límites
2. Se recibe solicitud de análisis
3. Se valida cuota disponible
4. Se autoriza o bloquea ejecución
**Flujos alternativos:**
- Sin cuota -> bloqueo
- Configuración inválida -> valores por defecto
**Eventos clave:**
- Cost Configured
- Cost Checked
- Execution Allowed/Blocked

`mermaid
flowchart TD
A[Analysis Request] --> B[Check Cost Limits]
B --> C{Quota Available?}
C -->|Yes| D[Execute Analysis]
C -->|No| E[Block Execution]
`

## 3. Resumen de dependencias
- FLOW-001 depende de Git Integration, AI Engine, Cost Management
- FLOW-002 depende de API Layer, Security, AI Engine
- FLOW-003 depende de Git Integration y Observability
- FLOW-004 depende de Cost Management y Configuration
- Todos los flujos dependen de Observability y Error Handling

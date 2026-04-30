# Initial Product Backlog
## 1. Epicas
- EPIC 001 Plataforma de analisis automatico de Pull Requests.
- EPIC 002 API REST de analisis y configuracion.
- EPIC 003 Control de costes y gobernanza.

## 2. Features
- FEAT 001 Integracion con GitHub y CI CD para Pull Requests.
- FEAT 002 Motor de analisis con IA para Java Spring Boot.
- FEAT 003 Publicacion de comentarios y feedback de calidad.
- FEAT 004 Configuracion por repositorio y limites de coste.

## 3. Historias de usuario
- STORY 001 Como developer quiero que los Pull Requests se analicen automaticamente para recibir comentarios de calidad y seguridad.
- STORY 002 Como developer quiero poder consultar un informe de analisis detallado para entender los problemas detectados.
- STORY 003 Como repository administrator quiero configurar limites de uso y costes por repositorio para controlar el gasto en IA.
- STORY 004 Como organization admin quiero ver trazabilidad de analisis y uso para auditar el servicio.

## 4. Dependencias
- Integracion con GitHub y CI CD antes de habilitar analisis automatico.
- Disponibilidad de proveedor de IA antes de activar el motor de analisis.

## 5. Priorizacion MoSCoW
- Must have
  - Analisis automatico de Pull Requests.
  - Integracion con GitHub y CI CD.
  - Motor de prompts modular para Java Spring Boot.
  - Control de costes basico y limites por repositorio.
- Should have
  - Feedback de calidad y explicabilidad avanzada.
  - Observabilidad y trazabilidad detallada.
- Could have
  - Soporte a otros lenguajes y proveedores Git.
- Wont have por ahora
  - Plugins de IDE.
  - Edicion automatica de codigo.

## 6. Registro de dudas acciones y refinamientos para JIRA
### JIRA ITEM 001
- Origen DOC00 DOC08
- Tipo
  - aclaracion.
- Descripcion
  - Definir formato estandar de informe de analisis y comentarios en Pull Requests.
- Impacto
  - Alto en experiencia de usuario y trazabilidad.
- Prioridad sugerida
  - Alta.
- Propuesta de resolucion
  - Diseñar plantilla de informe con secciones de severidad ubicacion recomendacion y referencia a reglas.

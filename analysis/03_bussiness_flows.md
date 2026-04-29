# Business Flows

## 1. Introducción
Este documento describe los principales flujos de negocio del sistema de revisión automática de código basado en inteligencia artificial, derivado de los requisitos funcionales (DOC1) y los dominios funcionales (DOC2). Los flujos representan las interacciones principales entre actores, sistemas externos y el motor de análisis.

## 2. Flujos de negocio

### FLOW-001: Análisis automático de Pull Request
**Actor principal:** Git Provider (GitHub) / CI/CD System  
**Objetivo:** Ejecutar análisis automático de código cuando se crea o actualiza un Pull Request  
**Descripción:** Flujo principal de análisis de código basado en eventos de Pull Request integrados con Git.

**Flujo principal:**  
1. Se crea o actualiza un Pull Request en el repositorio  
2. Git Provider envía evento al sistema  
3. El sistema recupera el diff del Pull Request  
4. Se construye el prompt mediante el motor de prompts  
5. Se invoca el proveedor de IA para análisis  
6. Se generan resultados de análisis (calidad, seguridad, feedback)  
7. Se publica comentario en el Pull Request con los resultados  

**Flujos alternativos:**  
- Fallo en obtención de diff → se registra error en observabilidad y se aborta análisis  
- Fallo en proveedor de IA → reintento o fallback según configuración  
- Coste excedido → bloqueo del análisis por DOMAIN-008  

**Eventos clave:**  
- Pull Request Created/Updated  
- Analysis Started  
- Analysis Completed  
- Analysis Failed  

---

### FLOW-002: Análisis vía API REST
**Actor principal:** Developer / External Client  
**Objetivo:** Permitir ejecución de análisis fuera del flujo CI/CD  
**Descripción:** Exposición de análisis mediante API REST para uso manual o integración externa.

**Flujo principal:**  
1. Cliente envía request de análisis a la API  
2. Sistema valida API key y autenticación  
3. Se construye prompt con el contenido proporcionado  
4. Se invoca el motor de IA  
5. Se genera informe de análisis  
6. Se devuelve respuesta al cliente  

**Flujos alternativos:**  
- API key inválida → rechazo de request  
- Payload inválido → error de validación  
- Timeout IA → respuesta de error controlado  

**Eventos clave:**  
- API Request Received  
- Authentication Success/Failure  
- Analysis Response Generated  

---

### FLOW-003: Publicación de resultados y feedback de calidad
**Actor principal:** Developer / Repository Administrator  
**Objetivo:** Publicar resultados del análisis y capturar feedback para mejora del sistema  
**Descripción:** Flujo que gestiona la entrega de resultados al usuario y la retroalimentación posterior.

**Flujo principal:**  
1. Se genera resultado de análisis  
2. Se publica comentario en Pull Request  
3. Usuario revisa resultados  
4. Usuario proporciona feedback (issues o validación)  
5. Sistema almacena feedback para mejora de prompts  

**Flujos alternativos:**  
- Fallo en publicación en Git → reintento o registro de error  
- Feedback no proporcionado → flujo finaliza sin almacenamiento  

**Eventos clave:**  
- Result Published  
- Feedback Submitted  
- Feedback Stored  

---

### FLOW-004: Control de costes y activación de análisis
**Actor principal:** Organization Admin / Repository Administrator  
**Objetivo:** Controlar uso de IA y evitar sobrecostes  
**Descripción:** Flujo de gobernanza que valida si un análisis puede ejecutarse según límites configurados.

**Flujo principal:**  
1. Admin configura límites de uso y coste por repositorio  
2. Se inicia solicitud de análisis (PR o API)  
3. Sistema valida disponibilidad de cuota  
4. Si hay cuota disponible, se permite ejecución  
5. Si no hay cuota, se bloquea análisis  

**Flujos alternativos:**  
- Cuota agotada → análisis bloqueado  
- Configuración inválida → uso de valores por defecto  

**Eventos clave:**  
- Cost Limit Configured  
- Cost Validation Executed  
- Analysis Blocked/Allowed  

---

## 3. Resumen de dependencias

- FLOW-001 depende de DOMAIN-001, DOMAIN-002, DOMAIN-004, DOMAIN-008
- FLOW-002 depende de DOMAIN-005, DOMAIN-012, DOMAIN-004
- FLOW-003 depende de DOMAIN-006, DOMAIN-010
- FLOW-004 depende de DOMAIN-008, DOMAIN-007
- Todos los flujos dependen transversalmente de DOMAIN-010 (Observabilidad)
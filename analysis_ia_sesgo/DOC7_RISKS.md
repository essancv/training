# Risk & Dependency Register

## 1. Riesgos

### RISK-001 – Dependencia fuerte del modelo de IA
- Descripción: La calidad del análisis (DOC0–DOC9) depende fuertemente del modelo de IA y de la calidad del prompt.
- Probabilidad: Alta.
- Impacto: Alto.
- Mitigación: Definir plantillas de prompt robustas, versionadas y probadas; incluir validaciones post-proceso y posibilidad de regenerar documentos.
- Responsable: Arquitecto de solución / Product Owner.

### RISK-002 – Esquema JSON inestable
- Descripción: Cambios frecuentes en el esquema del JSON pueden romper integraciones con GitHub y Jira.
- Probabilidad: Media.
- Impacto: Alto.
- Mitigación: Definir un contrato de esquema versionado, con compatibilidad hacia atrás cuando sea posible; pruebas automatizadas de validación.
- Responsable: Arquitecto de software.

### RISK-003 – Problemas de autenticación y seguridad con GitHub/Jira
- Descripción: Gestión incorrecta de credenciales o permisos puede impedir la publicación o exponer información sensible.
- Probabilidad: Media.
- Impacto: Alto.
- Mitigación: Uso de mecanismos seguros de almacenamiento de credenciales (vault, variables de entorno), pruebas en entornos de staging, revisión de permisos mínimos necesarios.
- Responsable: DevOps / Administrador de herramientas.

### RISK-004 – Sobredimensionamiento arquitectónico
- Descripción: Tentación de introducir microservicios u otras arquitecturas complejas sin necesidad real.
- Probabilidad: Media.
- Impacto: Medio-alto (coste y complejidad operativa).
- Mitigación: Establecer guardrails arquitectónicos explícitos (como los definidos en este documento), revisiones de arquitectura y aprobación por parte de un comité técnico.
- Responsable: Arquitecto de software.

### RISK-005 – Trazabilidad incompleta o inconsistente
- Descripción: Falta de enlaces claros entre requisitos, dominios, flujos, decisiones de arquitectura y backlog.
- Probabilidad: Media.
- Impacto: Alto (dificulta mantenimiento y auditoría).
- Mitigación: Definir un modelo de identificadores único (FR-XXX, DOMAIN-XXX, FLOW-XXX, etc.) y validaciones automáticas de trazabilidad en el ensamblado del JSON.
- Responsable: Analista funcional / Arquitecto.

### RISK-006 – Expectativas poco realistas sobre eliminación de sesgos
- Descripción: Se espera que el sistema "evite cualquier tipo de sesgo", lo cual no es totalmente controlable.
- Probabilidad: Alta.
- Impacto: Medio.
- Mitigación: Aclarar en la documentación el alcance real de las mitigaciones de sesgo; revisar periódicamente los prompts y resultados para detectar patrones indeseados.
- Responsable: Product Owner.

## 2. Dependencias

### DEP-001 – Proveedor de IA
- Descripción: Disponibilidad y condiciones de uso del modelo de IA (API, costes, límites de uso).
- Criticidad: Alta.

### DEP-002 – API de GitHub
- Descripción: Disponibilidad, cambios de versión y límites de rate de la API de GitHub.
- Criticidad: Media-alta.

### DEP-003 – API de Jira
- Descripción: Disponibilidad, cambios de versión y configuración específica de la instancia de Jira (Cloud/Server/Data Center).
- Criticidad: Media-alta.

### DEP-004 – Infraestructura de ejecución
- Descripción: Servidores o servicios donde se ejecutará el sistema (on-premise o cloud), incluyendo conectividad a las APIs externas.
- Criticidad: Media.

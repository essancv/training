# Risk & Dependency Register

## 1. Riesgos

### RISK-001 – Dependencia del prompt manual
- Descripción: La calidad del análisis generado por la IA depende fuertemente de cómo se redacte el prompt manual.
- Probabilidad: Alta.
- Impacto: Alto.
- Mitigación: Definir plantillas de prompt, guías de redacción y ejemplos; revisar y versionar prompts; realizar pruebas de regresión.
- Responsable: Arquitecto de software / Product Owner.

### RISK-002 – Esquema JSON inestable
- Descripción: Cambios en el esquema JSON pueden romper integraciones con GitHub y Jira.
- Probabilidad: Media.
- Impacto: Alto.
- Mitigación: Definir un esquema versionado, con validación automática y pruebas de compatibilidad; documentar cambios.
- Responsable: Arquitecto de software.

### RISK-003 – Fallos en integraciones con IA, GitHub o Jira
- Descripción: Errores de red, autenticación o cambios en APIs externas pueden impedir la generación o publicación del análisis.
- Probabilidad: Media.
- Impacto: Alto.
- Mitigación: Implementar manejo robusto de errores, reintentos, logs detallados y monitorización; probar en entornos de staging.
- Responsable: DevOps / Equipo de desarrollo.

### RISK-004 – Sobredimensionamiento arquitectónico o tecnológico
- Descripción: Uso de arquitecturas o stacks demasiado complejos para el tamaño del proyecto.
- Probabilidad: Media.
- Impacto: Medio-alto (coste, tiempo, complejidad operativa).
- Mitigación: Aplicar guardrails de arquitectura y tecnología; revisar decisiones en un comité técnico; documentar justificaciones.
- Responsable: Arquitecto de software.

### RISK-005 – Trazabilidad incompleta
- Descripción: Falta de enlaces claros entre requisitos, dominios, flujos, decisiones de arquitectura, riesgos y backlog.
- Probabilidad: Media.
- Impacto: Alto.
- Mitigación: Definir un modelo de identificadores (FR-XXX, DOMAIN-XXX, FLOW-XXX, ARCH-OPT-XXX, RISK-XXX, EPIC-XXX, STORY-XXX) y validaciones automáticas de trazabilidad en el ensamblado del JSON.
- Responsable: Analista funcional.

### RISK-006 – Expectativas irreales sobre eliminación de sesgos
- Descripción: Se espera que el sistema "evite cualquier tipo de sesgo", lo cual no es totalmente controlable.
- Probabilidad: Alta.
- Impacto: Medio.
- Mitigación: Aclarar en la documentación el alcance real de las mitigaciones; revisar periódicamente resultados; ajustar plantillas de prompt.
- Responsable: Product Owner.

## 2. Dependencias

### DEP-001 – Proveedor de IA
- Descripción: Disponibilidad, calidad, costes y límites de uso del modelo de IA.
- Criticidad: Alta.

### DEP-002 – API de GitHub
- Descripción: Disponibilidad, cambios de versión, límites de rate y permisos.
- Criticidad: Media-alta.

### DEP-003 – API de Jira
- Descripción: Disponibilidad, configuración específica de la instancia (Cloud/Server/Data Center), cambios de versión.
- Criticidad: Media-alta.

### DEP-004 – Infraestructura de ejecución
- Descripción: Servidores o servicios cloud donde se ejecuta el backend y el script Python, incluyendo conectividad a las APIs externas.
- Criticidad: Media.

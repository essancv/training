# Risk & Dependency Register

## 1. Riesgos

### RISK-001
- **Descripción:** La IA no genera JSON válido o incumple el esquema definido.
- **Probabilidad:** Media.
- **Impacto:** Alto (bloquea la integración con GitHub y Jira).
- **Mitigación:** Implementar validación estricta de esquema, pruebas con ejemplos variados y manejo de errores con reintentos o ajustes de prompt.
- **Responsable:** Equipo de arquitectura / desarrollo.

### RISK-002
- **Descripción:** Sobredimensionamiento arquitectónico o tecnológico pese a las reglas del prompt.
- **Probabilidad:** Baja-media.
- **Impacto:** Medio (aumento de costes y complejidad de mantenimiento).
- **Mitigación:** Revisiones de arquitectura por parte de un arquitecto humano, reglas claras en el prompt y documentación de decisiones.
- **Responsable:** Arquitecto de software.

### RISK-003
- **Descripción:** Problemas de seguridad en la gestión de credenciales de GitHub y Jira.
- **Probabilidad:** Media.
- **Impacto:** Alto (exposición de repositorios o proyectos).
- **Mitigación:** Uso de vault o variables de entorno seguras, rotación periódica de credenciales, revisión de permisos mínimos necesarios.
- **Responsable:** DevOps / Administrador de herramientas.

### RISK-004
- **Descripción:** Falta de aceptación por parte de los usuarios si el análisis generado no se percibe como fiable.
- **Probabilidad:** Media.
- **Impacto:** Medio-alto (baja adopción de la herramienta).
- **Mitigación:** Fase piloto, feedback de usuarios, ajustes iterativos del prompt y de las reglas de análisis.
- **Responsable:** Product Owner.

### RISK-005
- **Descripción:** Cambios futuros en las APIs de GitHub o Jira que rompan el script de integración.
- **Probabilidad:** Media.
- **Impacto:** Medio.
- **Mitigación:** Monitorizar cambios de API, usar SDKs oficiales cuando sea posible, diseñar el script de forma modular para facilitar actualizaciones.
- **Responsable:** Equipo de desarrollo / DevOps.

## 2. Dependencias

### DEP-001
- **Descripción:** Disponibilidad y estabilidad del proveedor de IA utilizado para generar el análisis.
- **Criticidad:** Alta.

### DEP-002
- **Descripción:** Acceso a APIs de GitHub con permisos adecuados para subir archivos al repositorio.
- **Criticidad:** Alta.

### DEP-003
- **Descripción:** Acceso a APIs de Jira con permisos para crear y actualizar issues.
- **Criticidad:** Alta.

### DEP-004
- **Descripción:** Definición y aprobación del esquema JSON por parte de los stakeholders.
- **Criticidad:** Media-alta.

### DEP-005
- **Descripción:** Disponibilidad de entornos de prueba para GitHub y Jira (sandbox o proyectos de prueba).
- **Criticidad:** Media.

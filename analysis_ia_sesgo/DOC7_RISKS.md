# Risk & Dependency Register
## 1. Riesgos
### RISK-001 Dependencia de proveedor de IA
- Descripción: Fuerte dependencia de un proveedor externo de IA para el análisis de código.
- Probabilidad: Alta.
- Impacto: Alto.
- Mitigación: Diseñar el AIServiceAdapter para soportar múltiples proveedores; definir timeouts y estrategias de fallback.
- Responsable: Product Owner y Arquitecto.

### RISK-002 Rendimiento en PR grandes
- Descripción: Análisis de Pull Requests muy grandes puede superar el objetivo de NFR-002.
- Probabilidad: Media.
- Impacto: Alto.
- Mitigación: Definir límites de tamaño de PR, análisis parcial y mensajes claros al usuario.
- Responsable: Equipo de desarrollo.

### RISK-003 Costes de IA descontrolados
- Descripción: Uso intensivo de IA puede generar costes superiores a lo previsto.
- Probabilidad: Media-Alta.
- Impacto: Alto.
- Mitigación: Implementar FR-011 y NFR-009 con límites, alertas y modos de análisis más ligeros.
- Responsable: Product Owner y Organization Admin.

## 2. Dependencias
### DEP-001 GitHub y otros proveedores Git
- Descripción: Dependencia de APIs de GitHub y potencialmente otros proveedores Git.
- Criticidad: Alta.

### DEP-002 GitHub Actions u otros CI/CD
- Descripción: Dependencia de pipelines de CI/CD para disparar análisis automáticos.
- Criticidad: Media-Alta.

### DEP-003 Proveedor de infraestructura
- Descripción: Dependencia de la plataforma donde se despliega el monolito (VM o contenedores).
- Criticidad: Media.

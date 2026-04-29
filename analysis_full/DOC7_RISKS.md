# Risk & Dependency Register

## 1. Riesgos
### RISK-001
- Descripción: Coste IA superior al esperado
- Probabilidad: Alta
- Impacto: Alto
- Mitigación: cuotas, caché, modelos tiering
- Responsable: Product Owner
### RISK-002
- Descripción: Falsos positivos del análisis
- Probabilidad: Media
- Impacto: Alto
- Mitigación: feedback loop y tuning prompts
- Responsable: Functional Lead
### RISK-003
- Descripción: Cambios API GitHub
- Probabilidad: Media
- Impacto: Medio
- Mitigación: adapter desacoplado
- Responsable: Architect

## 2. Dependencias
### DEP-001
- Descripción: GitHub APIs/Webhooks
- Criticidad: Alta
### DEP-002
- Descripción: Proveedor IA externo
- Criticidad: Alta
### DEP-003
- Descripción: Infraestructura cloud
- Criticidad: Media
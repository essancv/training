# Risk & Dependency Register

## 1. Riesgos
### RISK-001
- Descripción: JSON inválido generado por IA.
- Probabilidad: Media
- Impacto: Alto
- Mitigación: Validación schema y reintentos.
- Responsable: Tech Lead

### RISK-002
- Descripción: Cambios en APIs GitHub/Jira.
- Probabilidad: Media
- Impacto: Medio
- Mitigación: Adaptadores desacoplados.
- Responsable: Architect

### RISK-003
- Descripción: Baja calidad de requisitos de entrada.
- Probabilidad: Alta
- Impacto: Alto
- Mitigación: Plantillas y validaciones previas.
- Responsable: Product Owner

### RISK-004
- Descripción: Coste elevado de uso IA.
- Probabilidad: Alta
- Impacto: Alto
- Mitigación: Límites y modelos alternativos.
- Responsable: Product Owner

## 2. Dependencias
### DEP-001
- Descripción: API Proveedor IA
- Criticidad: Alta

### DEP-002
- Descripción: GitHub API
- Criticidad: Alta

### DEP-003
- Descripción: Jira API
- Criticidad: Alta

### DEP-004
- Descripción: Runtime Python corporativo
- Criticidad: Media
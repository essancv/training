# Risk & Dependency Register

## 1. Riesgos
### RISK-001
- Descripción: Coste variable del proveedor IA superior al previsto.
- Probabilidad: Alta
- Impacto: Alto
- Mitigación: Cuotas, caché, modelos alternativos.
- Responsable: Product Owner

### RISK-002
- Descripción: Falsos positivos que reduzcan confianza del usuario.
- Probabilidad: Media
- Impacto: Alto
- Mitigación: Feedback loop y ajuste continuo.
- Responsable: Functional Lead

### RISK-003
- Descripción: Caída o degradación de APIs externas.
- Probabilidad: Media
- Impacto: Alto
- Mitigación: Retries, circuit breaker y fallback.
- Responsable: Architect

### RISK-004
- Descripción: Exposición de código sensible en tránsito.
- Probabilidad: Baja
- Impacto: Muy Alto
- Mitigación: HTTPS, masking y cifrado.
- Responsable: Security Lead

## 2. Dependencias
### DEP-001
- Descripción: GitHub APIs y Webhooks.
- Criticidad: Alta

### DEP-002
- Descripción: Proveedor IA externo.
- Criticidad: Alta

### DEP-003
- Descripción: Plataforma cloud e infraestructura.
- Criticidad: Media

### DEP-004
- Descripción: Sistema CI/CD del cliente.
- Criticidad: Media
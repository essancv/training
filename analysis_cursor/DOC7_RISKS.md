# Risk & Dependency Register

## 1. Riesgos
### RISK-001
- Descripción: Exposicion de secretos o datos sensibles al proveedor LLM
- Probabilidad: Media
- Impacto: Alto
- Mitigación: Redaccion automatica de secretos limitar contexto politica de no retencion proveedor aprobado
- Responsable: Security Champion

### RISK-002
- Descripción: Falsos positivos o recomendaciones inconsistentes del LLM
- Probabilidad: Media
- Impacto: Medio
- Mitigación: Catalogo de hallazgos y severidades uso de herramientas deterministas como base y LLM como complemento
- Responsable: Tech Lead

### RISK-003
- Descripción: Latencia alta en PR y mala experiencia de desarrollo
- Probabilidad: Media
- Impacto: Medio
- Mitigación: Limites de diff cache resumen por archivo timeouts y modo degradado
- Responsable: DevOps

### RISK-004
- Descripción: Coste variable por uso del LLM
- Probabilidad: Media
- Impacto: Medio
- Mitigación: Cuotas por repo muestreo de analisis analisis incremental y control de tokens
- Responsable: Product Owner

### RISK-005
- Descripción: Permisos insuficientes en GitHub Actions para comentar o leer diff
- Probabilidad: Baja
- Impacto: Medio
- Mitigación: Politicas de permisos revisadas y pruebas en repos de ejemplo
- Responsable: DevOps

## 2. Dependencias
### DEP-001
- Descripción: GitHub Actions y GitHub API
- Criticidad: Alta

### DEP-002
- Descripción: Proveedor LLM y condiciones de privacidad
- Criticidad: Alta

### DEP-003
- Descripción: Herramientas de analisis estatico y seguridad
- Criticidad: Media

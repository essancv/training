# Architecture Options

## 1. Introducción
Se proponen tres alternativas de implantación según velocidad, coste y escalabilidad.

## 2. Arquitecturas propuestas
### ARCH-OPT-001: Monolito API + Workers
- Descripción: Backend único con módulos internos e integraciones.
- Diagrama textual: Web/API + Prompt Engine + Exporters + DB
- Ventajas: Menor time-to-market.
- Inconvenientes: Escalado menos granular.
- Riesgos: Acoplamiento futuro.
- Adecuación RF: Alta
- Adecuación RNF: Media
- Coste relativo: Bajo

### ARCH-OPT-002: Microservicios desacoplados
- Descripción: Servicios independientes por dominio funcional.
- Diagrama textual: Gateway + Requirements + AI + JSON + Connectors
- Ventajas: Escalabilidad y autonomía.
- Inconvenientes: Mayor complejidad DevOps.
- Riesgos: Observabilidad distribuida.
- Adecuación RF: Alta
- Adecuación RNF: Alta
- Coste relativo: Alto

### ARCH-OPT-003: Serverless gestionado
- Descripción: Funciones por evento con servicios cloud administrados.
- Diagrama textual: API Gateway + Functions + Secrets + SaaS APIs
- Ventajas: Pago por uso.
- Inconvenientes: Dependencia cloud.
- Riesgos: Lock-in tecnológico.
- Adecuación RF: Media Alta
- Adecuación RNF: Media Alta
- Coste relativo: Medio
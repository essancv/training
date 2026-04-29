# Architecture Options

## 1. Introducción
Se presentan alternativas viables para lanzamiento y evolución.

## 2. Arquitecturas propuestas
### ARCH-OPT-001: Monolito Modular
- Descripción: Aplicación única con módulos desacoplados y workers internos.
- Diagrama textual: API + Scheduler + Workers + DB
- Ventajas: Menor coste inicial, rapidez de entrega.
- Inconvenientes: Escalado menos granular.
- Riesgos: Acoplamiento progresivo.
- Adecuación RF: Alta para MVP.
- Adecuación RNF: Media.
- Coste relativo: Bajo.

### ARCH-OPT-002: Microservicios Event Driven
- Descripción: Servicios independientes conectados mediante cola de eventos.
- Diagrama textual: Gateway + Queue + Git Service + AI Service + Billing Service
- Ventajas: Escalabilidad independiente, resiliencia.
- Inconvenientes: Mayor complejidad operativa.
- Riesgos: Trazabilidad distribuida.
- Adecuación RF: Alta.
- Adecuación RNF: Alta.
- Coste relativo: Alto.

### ARCH-OPT-003: Serverless Managed
- Descripción: Funciones bajo demanda sobre servicios cloud gestionados.
- Diagrama textual: Webhook -> Functions -> Managed DB -> AI API
- Ventajas: Pago por uso y elasticidad.
- Inconvenientes: Dependencia cloud y cold starts.
- Riesgos: Vendor lock-in.
- Adecuación RF: Media Alta.
- Adecuación RNF: Media Alta.
- Coste relativo: Medio.
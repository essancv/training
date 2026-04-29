# Architecture Options

## 1. Introducción
Alternativas de arquitectura objetivo.

## 2. Arquitecturas propuestas
### ARCH-OPT-001: Monolito Modular
- Descripción: API única con módulos internos.
- Diagrama textual: API + Workers + DB
- Ventajas: Rapidez inicial
- Inconvenientes: Escala limitada
- Riesgos: Acoplamiento futuro
- Adecuación RF: Alta MVP
- Adecuación RNF: Media
- Coste relativo: Bajo
### ARCH-OPT-002: Microservicios Event-Driven
- Descripción: Servicios separados con cola.
- Diagrama textual: Gateway + Queue + Services
- Ventajas: Escala independiente
- Inconvenientes: Operación compleja
- Riesgos: Observabilidad distribuida
- Adecuación RF: Alta
- Adecuación RNF: Alta
- Coste relativo: Alto
### ARCH-OPT-003: Serverless
- Descripción: Funciones por evento.
- Diagrama textual: Webhook -> Functions -> Managed DB
- Ventajas: Pago por uso
- Inconvenientes: Cold starts
- Riesgos: Lock-in cloud
- Adecuación RF: Media Alta
- Adecuación RNF: Media Alta
- Coste relativo: Medio
# Architecture Options

## 1. Introducción
Este documento presenta varias alternativas arquitectónicas para el sistema de revisión automática de código basado en inteligencia artificial, considerando los requisitos, dominios funcionales, flujos de negocio y nivel de complejidad definidos en DOC1–DOC4.

## 2. Arquitecturas propuestas

### ARCH-OPT-001: Monolito Modular por Dominios
- Descripción: Aplicación única desplegable organizada internamente en módulos funcionales desacoplados por dominio.
- Diagrama textual:
```mermaid
flowchart TD
A[Monolith Application] --> B[Git Integration Module]
A --> C[Analysis Engine Module]
A --> D[AI Prompt Module]
A --> E[API Module]
A --> F[Cost Control Module]
A --> G[Observability Module]
```
- Ventajas:
  - Menor complejidad inicial
  - Despliegue simple
  - Menor coste operativo inicial
- Inconvenientes:
  - Escalabilidad limitada
  - Acoplamiento creciente con el tiempo
  - Evolución más lenta
- Riesgos:
  - Saturación del proceso principal
  - Dificultad de separación futura
- Adecuación a RF:
  - Alta para MVP y primeras fases
- Adecuación a RNF:
  - Media
- Coste relativo: Bajo

### ARCH-OPT-002: Microservicios Event-Driven
- Descripción: Servicios independientes por dominio comunicados mediante eventos y mensajería asíncrona.
- Diagrama textual:
```mermaid
flowchart TD
A[GitHub Webhook] --> B[Event Bus]
B --> C[PR Analysis Service]
B --> D[AI Service]
B --> E[Cost Service]
B --> F[API Service]
B --> G[Notification Service]
B --> H[Observability Service]
```
- Ventajas:
  - Alta escalabilidad
  - Desacoplamiento fuerte
  - Resiliencia elevada
- Inconvenientes:
  - Mayor complejidad operativa
  - Coste inicial elevado
  - Debugging distribuido complejo
- Riesgos:
  - Inconsistencia eventual
  - Sobrecoste de infraestructura
- Adecuación a RF:
  - Muy alta
- Adecuación a RNF:
  - Muy alta
- Coste relativo: Alto

### ARCH-OPT-003: Hexagonal (Ports & Adapters)
- Descripción: Núcleo de negocio aislado con adaptadores externos para Git, IA, API y CI/CD.
- Diagrama textual:
```mermaid
flowchart TD
A[Core Domain: Analysis Engine]
A --> B[Git Adapter]
A --> C[AI Adapter]
A --> D[REST Adapter]
A --> E[CI/CD Adapter]
A --> F[Cost Adapter]
A --> G[Logging Adapter]
```
- Ventajas:
  - Alta mantenibilidad
  - Excelente testabilidad
  - Buen aislamiento del dominio
- Inconvenientes:
  - Mayor diseño inicial
  - Curva de aprendizaje técnica
- Riesgos:
  - Sobreingeniería en MVP pequeño
- Adecuación a RF:
  - Alta
- Adecuación a RNF:
  - Alta
- Coste relativo: Medio

### ARCH-OPT-004: Serverless Event-Driven
- Descripción: Flujo compuesto por funciones serverless independientes activadas por eventos.
- Diagrama textual:
```mermaid
flowchart TD
A[Webhook Event] --> B[Function Fetch Diff]
B --> C[Function Build Prompt]
C --> D[Function Invoke AI]
D --> E[Function Build Report]
E --> F[Function Publish PR Comment]
```
- Ventajas:
  - Escalado automático
  - Pago por uso
  - Alta disponibilidad nativa
- Inconvenientes:
  - Dependencia del cloud provider
  - Latencia por cold starts
  - Debugging complejo
- Riesgos:
  - Costes variables impredecibles
  - Límites de ejecución runtime
- Adecuación a RF:
  - Media-Alta
- Adecuación a RNF:
  - Alta
- Coste relativo: Variable

### ARCH-OPT-005: Híbrida Hexagonal + Event-Driven
- Descripción: Núcleo hexagonal de negocio combinado con capa de integración basada en eventos.
- Diagrama textual:
```mermaid
flowchart TD
A[GitHub / API / CI Events] --> B[Event Bus]
B --> C[Orchestrator]
C --> D[Hexagonal Core]
D --> E[AI Adapter]
D --> F[Cost Adapter]
D --> G[Security Adapter]
D --> H[Git Adapter]
```
- Ventajas:
  - Balance entre escalabilidad y mantenibilidad
  - Núcleo estable y extensible
  - Buen soporte multi-integración
- Inconvenientes:
  - Complejidad media-alta
  - Mayor esfuerzo de gobierno técnico
- Riesgos:
  - Integración entre patrones arquitectónicos
- Adecuación a RF:
  - Muy alta
- Adecuación a RNF:
  - Muy alta
- Coste relativo: Medio-Alto

## 3. Resumen comparativo
| Arquitectura | Complejidad | Escalabilidad | Coste | Mantenibilidad | Time to Market |
|--------------|-------------|---------------|-------|----------------|----------------|
| Monolito Modular | Baja | Media | Bajo | Media | Alto |
| Microservicios Event-Driven | Alta | Muy Alta | Alto | Alta | Medio |
| Hexagonal | Media | Alta | Medio | Muy Alta | Medio |
| Serverless Event-Driven | Media | Alta | Variable | Media | Alto |
| Híbrida Hexagonal + Event-Driven | Alta | Muy Alta | Medio-Alto | Alta | Medio |
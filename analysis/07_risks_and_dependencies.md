# Risk & Dependency Register

## 1. Riesgos

### RISK-001: Dependencia crítica de proveedor de IA externo
- Descripción: El sistema depende del servicio externo de IA para generar análisis de código, siendo un componente central en FLOW-001 y FLOW-002.
- Probabilidad: Alta
- Impacto: Muy alto
- Mitigación: Implementar fallback de modelos o proveedores alternativos; cache de resultados; degradación controlada del servicio.
- Responsable: AI Engine Team / Platform Team

---

### RISK-002: Latencia y degradación en análisis de Pull Requests
- Descripción: El análisis de PR puede superar los límites de rendimiento definidos (NFR-002).
- Probabilidad: Media-Alta
- Impacto: Alto
- Mitigación: Optimización de prompts, procesamiento asíncrono, colas de trabajo, caching parcial.
- Responsable: Core Analysis Team

---

### RISK-003: Sobrecoste por uso intensivo de IA
- Descripción: El consumo de API de IA puede exceder los límites presupuestarios definidos.
- Probabilidad: Alta
- Impacto: Alto
- Mitigación: Implementación de control de costes (DOMAIN-008), throttling y quotas por repositorio.
- Responsable: FinOps / Platform Team

---

### RISK-004: Complejidad operativa en arquitectura distribuida
- Descripción: En opciones basadas en microservicios o event-driven, la operación y debugging se vuelve compleja.
- Probabilidad: Media
- Impacto: Alto
- Mitigación: Observabilidad avanzada (logs, tracing), estándares de eventos, tooling de debugging distribuido.
- Responsable: Platform Engineering

---

### RISK-005: Inconsistencia en resultados de análisis (IA no determinista)
- Descripción: Los resultados del análisis pueden variar entre ejecuciones debido a la naturaleza probabilística de la IA.
- Probabilidad: Alta
- Impacto: Medio-Alto
- Mitigación: Versionado de prompts, temperatura controlada, evaluación de consistencia.
- Responsable: AI Engineering

---

### RISK-006: Dependencia fuerte de GitHub API y eventos
- Descripción: Fallos o cambios en GitHub afectan directamente FLOW-001.
- Probabilidad: Media
- Impacto: Alto
- Mitigación: Abstracción mediante adaptadores (Hexagonal), soporte multi-Git provider.
- Responsable: Integration Team

---

### RISK-007: Complejidad de seguridad y gestión de secretos
- Descripción: Manejo de API keys y acceso a repositorios introduce riesgos de seguridad.
- Probabilidad: Media
- Impacto: Muy alto
- Mitigación: Vaults seguros, rotación de claves, mínimo privilegio.
- Responsable: Security Team

## 2. Dependencias

### DEP-001: Proveedor de IA externo
- Descripción: Servicio externo necesario para análisis de código en todos los flujos principales.
- Criticidad: Crítica

---

### DEP-002: GitHub API / Git Provider
- Descripción: Fuente de eventos de Pull Request y canal de publicación de resultados.
- Criticidad: Crítica

---

### DEP-003: Infraestructura CI/CD
- Descripción: Entorno de ejecución para análisis automatizado en pipelines.
- Criticidad: Alta

---

### DEP-004: Sistema de mensajería/event bus (en arquitecturas event-driven)
- Descripción: Backbone de comunicación entre servicios en arquitecturas distribuidas.
- Criticidad: Alta

---

### DEP-005: Sistema de observabilidad (logs, métricas, trazas)
- Descripción: Necesario para trazabilidad de análisis y debugging de flujos.
- Criticidad: Alta

---

### DEP-006: Sistema de gestión de costes/quotas
- Descripción: Control de uso de IA y validación de ejecución de análisis.
- Criticidad: Crítica
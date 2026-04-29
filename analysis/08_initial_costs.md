# Initial Cost Estimate

## 1. Coste por bloque funcional

| Bloque funcional | Complejidad estimada | Coste relativo | Comentario |
|------------------|----------------------|----------------|------------|
| Motor de análisis de Pull Requests | Alta | Alto | Núcleo funcional del sistema |
| Integración Git / GitHub | Alta | Medio-Alto | Webhooks, APIs, publicación PR |
| Integración CI/CD | Media | Medio | Pipelines y runners |
| Motor de prompts e IA | Alta | Alto | Diseño, versionado, optimización |
| API REST | Media | Medio | Seguridad y exposición externa |
| Publicación de resultados y feedback | Media | Medio | UX operativa y feedback loop |
| Configuración por repositorio | Media | Medio | Multi-configuración |
| Gestión de costes | Media | Medio-Alto | Quotas, límites, reporting |
| Seguridad (API keys, secretos) | Media | Medio-Alto | Requisito transversal |
| Observabilidad y trazabilidad | Media | Medio | Logs, métricas, tracing |
| Extensibilidad tecnológica | Media | Medio-Alto | Diseño orientado a crecimiento |

## 2. Coste por arquitectura

| Arquitectura | Coste implementación inicial | Coste operación anual | Coste evolución | Observación |
|--------------|------------------------------|-----------------------|-----------------|-------------|
| Monolito Modular | Bajo | Bajo | Medio-Alto | Rápida salida al mercado |
| Microservices Event-Driven | Alto | Alto | Medio | Alto coste inicial y operativo |
| Hexagonal | Medio | Medio | Bajo-Medio | Buen equilibrio estructural |
| Serverless Event-Driven | Medio | Variable | Medio | Dependiente de volumen de uso |
| Híbrida Hexagonal + Event-Driven | Medio-Alto | Medio-Alto | Bajo | Alta capacidad futura |

## 3. Coste de infraestructura

| Concepto | Coste relativo | Comentario |
|----------|----------------|------------|
| Hosting aplicación backend | Medio | Compute base |
| Base de datos configuración/resultados | Medio | Persistencia operativa |
| Sistema de colas/event bus | Medio-Alto | Necesario en arquitecturas distribuidas |
| Observabilidad (logs, métricas, tracing) | Medio | Coste recurrente creciente |
| Gestión de secretos | Bajo-Medio | Vault / KMS |
| CDN / API Gateway | Medio | Especialmente para API pública |
| Escalado automático | Medio | Relevante en alta carga |
| Backup / DR | Medio | Continuidad de negocio |

## 4. Coste de integraciones

| Integración | Coste relativo | Comentario |
|-------------|----------------|------------|
| GitHub / Git Provider | Medio | APIs, webhooks, permisos |
| CI/CD (GitHub Actions u otros) | Medio | Setup y mantenimiento |
| Proveedor IA externo | Alto | Principal driver variable de coste |
| SSO / IAM corporativo | Medio | Si se incorpora futuro enterprise |
| Alerting / Monitoring tools | Medio | Integración operativa |

## 5. Coste de mantenimiento anual

| Área | Coste relativo anual | Comentario |
|------|----------------------|------------|
| Soporte correctivo | Medio | Bugs e incidencias |
| Evolutivos funcionales | Medio-Alto | Nuevos lenguajes y reglas |
| Operación plataforma | Medio-Alto | Monitoring, seguridad, upgrades |
| Coste proveedor IA | Alto | Dependiente del volumen |
| Seguridad y compliance | Medio | Rotación claves, auditoría |
| Optimización de prompts/modelos | Medio-Alto | Necesario para calidad/coste |

**Resumen ejecutivo:**
- Arquitecturas con menor coste inicial: Monolito Modular.
- Mejor equilibrio coste/valor a medio plazo: Hexagonal.
- Mayor coste total potencial: Microservices Event-Driven.
- Coste variable más crítico del proyecto: consumo de IA externa.
- Para un proyecto clasificado como COMPLEX (DOC4), se recomienda estimación financiera por fases (MVP, Escalado, Enterprise).
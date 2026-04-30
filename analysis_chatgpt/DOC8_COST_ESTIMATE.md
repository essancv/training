# Initial Cost Estimate
## 1. Coste por bloque funcional
- DOMAIN-001 CorePullRequestAnalysis: Alto, por integración con IA, generación de informes y publicación en PR.
- DOMAIN-002 GitAndCICDIntegration: Medio, por integración con webhooks y pipelines.
- DOMAIN-003 ConfigurationCostAndGovernance: Medio, por lógica de límites, políticas y modelos de trial.
- DOMAIN-004 ExplainabilityFeedbackAndObservability: Medio, por feedback, métricas y trazabilidad.

## 2. Coste por arquitectura
- ARCH-OPT-001 Monolito modular 3-tier: Coste de desarrollo y mantenimiento medio, coste de infraestructura contenido (una o pocas instancias escalables horizontalmente).

## 3. Coste de infraestructura
- VM o cluster de contenedores simple para el monolito.
- Base de datos PostgreSQL gestionada o autogestionada.
- Solución de observabilidad (logs y métricas) de bajo coste.

## 4. Coste de integraciones
- Integración con GitHub y GitHub Actions: coste inicial de desarrollo medio, coste operativo bajo.
- Integración con proveedor de IA: coste de desarrollo medio, coste variable de uso (principal driver de OPEX).

## 5. Coste de mantenimiento anual
- Mantenimiento de monolito y actualizaciones de dependencias.
- Ajuste de prompts y modelos de IA.
- Evolución de integraciones y soporte a nuevas tecnologías.
- Se estima un coste de mantenimiento anual medio, dominado por la evolución funcional y la optimización de costes de IA.

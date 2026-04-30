# Initial Cost Estimate

## 1. Coste por bloque funcional

Estimación de esfuerzo (personas-mes) para una primera versión funcional (MVP), asumiendo un equipo de 3–5 desarrolladores y un enfoque ARCH-OPT-001/002.

- DOMAIN-001 – Captura y normalización de requisitos: ~0.5 personas-mes.
- DOMAIN-002 – Orquestación de prompt y reglas: ~0.75 personas-mes.
- DOMAIN-003 – Motor de análisis IA (integración con API): ~0.75 personas-mes.
- DOMAIN-004 – Ensamblado y trazabilidad del JSON: ~0.75 personas-mes.
- DOMAIN-005 – Integración con GitHub y Jira (script Python + conectores): ~1.0 personas-mes.
- DOMAIN-006 – Gobernanza, configuración y seguridad: ~0.5 personas-mes.

**Total aproximado MVP**: 4.25 personas-mes (sin incluir QA y gestión de proyecto).

## 2. Coste por arquitectura

- **ARCH-OPT-001 – Monolito modular en capas**:
  - Esfuerzo de implementación: Base (4.25 personas-mes) + 0.5 personas-mes de diseño y pruebas → ~4.75 personas-mes.
  - Coste relativo: Bajo.

- **ARCH-OPT-002 – Monolito Clean/Hexagonal**:
  - Esfuerzo adicional de diseño y refactorización: +0.5–1.0 personas-mes sobre ARCH-OPT-001.
  - Coste relativo: Medio.

- **ARCH-OPT-003 – Microservicios** (no recomendada):
  - Esfuerzo adicional significativo (diseño de servicios, infraestructura, observabilidad): +2–3 personas-mes.
  - Coste relativo: Alto.

## 3. Coste de infraestructura

- Entorno de ejecución (servidor o servicio cloud para el monolito):
  - Coste mensual estimado: Bajo-medio, dependiendo del proveedor (por ejemplo, una instancia pequeña/mediana en cloud).
- Coste de almacenamiento:
  - Bajo, dado el volumen limitado de JSONs y logs.
- Coste de monitorización y logging:
  - Bajo-medio, si se utilizan servicios gestionados o herramientas existentes.

## 4. Coste de integraciones

- Integración con IA:
  - Coste variable por uso (tokens/llamadas), dependiente del proveedor.
  - Para un uso moderado (análisis por proyecto), el coste se mantiene en rango bajo-medio.

- Integración con GitHub y Jira:
  - Coste principalmente en esfuerzo de desarrollo (incluido en DOMAIN-005).
  - Posibles costes de licencias de Jira/GitHub Enterprise si aplica (fuera del alcance de esta estimación técnica).

## 5. Coste de mantenimiento anual

- Mantenimiento correctivo y evolutivo (nuevos tipos de documentos, ajustes de prompt, cambios en APIs):
  - Estimación: 10–20% del esfuerzo inicial por año → ~0.5–1.0 personas-mes/año.
- Monitorización de cambios en APIs de IA, GitHub y Jira y adaptación:
  - Estimación: 0.25–0.5 personas-mes/año.

En conjunto, el coste de mantenimiento anual se considera **bajo-medio**, coherente con un producto especializado de tamaño MEDIUM.

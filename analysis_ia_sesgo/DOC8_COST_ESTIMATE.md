# Initial Cost Estimate

## 1. Coste por bloque funcional

Estimación de esfuerzo (personas-mes) para un MVP basado en ARCH-OPT-001 (monolito modular Python/FastAPI), sin incluir gestión de proyecto ni QA dedicados.

- DOMAIN-001 – Captura y validación de requisitos: ~0.4 personas-mes.
- DOMAIN-002 – Gestión de prompt manual: ~0.5 personas-mes.
- DOMAIN-003 – Orquestación de llamada a la IA: ~0.6 personas-mes.
- DOMAIN-004 – Ensamblado del JSON y trazabilidad: ~0.7 personas-mes.
- DOMAIN-005 – Integración con GitHub y Jira (script Python + conectores): ~0.8 personas-mes.
- DOMAIN-006 – Configuración, seguridad y observabilidad: ~0.4 personas-mes.

Total aproximado MVP: **3.4 personas-mes**.

## 2. Coste por arquitectura

- ARCH-OPT-001 – Monolito modular Python/FastAPI:
  - Esfuerzo base: ~3.4 personas-mes.
  - Overhead de diseño y pruebas: ~0.4 personas-mes.
  - Total estimado: ~3.8 personas-mes.

- ARCH-OPT-002 – Monolito Clean Architecture:
  - Esfuerzo adicional de diseño y refactorización: +0.5–0.8 personas-mes.
  - Total estimado: ~4.3–4.6 personas-mes.

- ARCH-OPT-003 – SPA React + API REST:
  - Esfuerzo adicional de frontend: +0.6–0.8 personas-mes.
  - Total estimado: ~4.4–4.6 personas-mes.

## 3. Coste de infraestructura

- Backend monolítico (Python/FastAPI) en una VM o contenedor:
  - Coste mensual: Bajo, dependiendo del proveedor cloud (instancia pequeña/mediana).
- Base de datos (PostgreSQL o similar):
  - Coste bajo para el volumen de datos previsto.
- Almacenamiento de logs y JSONs:
  - Coste bajo.

## 4. Coste de integraciones

- IA:
  - Coste variable por uso (tokens/llamadas). Para un uso moderado (análisis por proyecto), el coste se considera bajo-medio.
- GitHub y Jira:
  - Coste de desarrollo incluido en DOMAIN-005.
  - Posibles costes de licencias (fuera del alcance de esta estimación técnica).

## 5. Coste de mantenimiento anual

- Mantenimiento correctivo y evolutivo (ajustes de esquema, cambios en prompts, nuevas integraciones):
  - Estimación: 15–20% del esfuerzo inicial por año → ~0.6–0.8 personas-mes/año.
- Adaptación a cambios en APIs de IA, GitHub y Jira:
  - Estimación: 0.3–0.5 personas-mes/año.

Coste de mantenimiento anual global: **~0.9–1.3 personas-mes/año**, considerado bajo-medio para un producto de este tamaño.

# Initial Cost Estimate

## 1. Coste por bloque funcional
- **DOMAIN-001 – Ingesta y gestión de requisitos:**
  - Estimación: 80 horas.
  - Incluye modelado de requisitos, identificadores y trazabilidad básica.
- **DOMAIN-002 – Orquestación de IA y generación de análisis:**
  - Estimación: 140 horas.
  - Incluye integración con IA, construcción de prompt y manejo de errores.
- **DOMAIN-003 – Motor de reglas de arquitectura y anti-sobredimensionamiento:**
  - Estimación: 80 horas.
- **DOMAIN-004 – Modelo de datos y generación de JSON unificado:**
  - Estimación: 120 horas.
- **DOMAIN-005 – Sizing y estimación de esfuerzo:**
  - Estimación: 60 horas.
- **DOMAIN-006 – Estimación de costes y riesgos:**
  - Estimación: 60 horas.
- **DOMAIN-007 – Backlog y exportación a herramientas externas:**
  - Estimación: 120 horas (incluye integración con GitHub y Jira).

**Total estimado por dominios:** 660 horas.

## 2. Coste por arquitectura
- **ARCH-OPT-001 – Monolito modular en capas:**
  - Esfuerzo adicional de arquitectura y despliegue: 80 horas.
  - Coste relativo: Bajo.
- **ARCH-OPT-002 – SPA ligera + API REST monolítica:**
  - Esfuerzo adicional (frontend + coordinación): 160 horas.
  - Coste relativo: Medio.

Se asume la elección de **ARCH-OPT-001**, por lo que se considera el coste de 80 horas.

## 3. Coste de infraestructura
- **Entorno de desarrollo y pruebas:** 40 horas (configuración de contenedores, CI básica, entornos de prueba).
- **Entorno de producción ligero:** 40 horas (configuración de VM o servicio de contenedores, monitorización básica).

Total infraestructura: 80 horas.

## 4. Coste de integraciones
- **Integración con IA:** Incluida en DOMAIN-002 (140 horas).
- **Integración con GitHub:** 40 horas (script, pruebas, documentación).
- **Integración con Jira:** 60 horas (script, mapeo de backlog, pruebas).

Total integraciones específicas: 100 horas (además de las ya consideradas en DOMAIN-007, se asume solapamiento parcial).

## 5. Coste de mantenimiento anual
- **Mantenimiento correctivo y evolutivo:**
  - Estimación: 15% del esfuerzo inicial de desarrollo.
  - Esfuerzo inicial aproximado: 660 horas (dominios) + 80 horas (arquitectura) + 80 horas (infraestructura) ≈ 820 horas.
  - 15% de 820 horas ≈ 123 horas anuales.

En términos económicos, el coste final dependerá de la tarifa por hora, pero el esfuerzo estimado sitúa el proyecto en un rango **MEDIUM** coherente con el sizing y la arquitectura recomendada.

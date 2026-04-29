# 📦 AI Project Artifacts Definition Spec (ADS v2.1 - Reasoning First + Multi Architecture)

Este documento define el sistema de artefactos utilizado para transformar requisitos funcionales y no funcionales en:

- Especificaciones de arquitectura
- Backlog estructurado en Jira
- Documentación en GitHub
- Validación automática de completitud
- Modelo de razonamiento estructurado del sistema
- Evaluación comparativa de arquitecturas viables
- Selección justificada de arquitectura inicial
- Base para evolución futura del producto

---

# 🧠 0. PRINCIPIO FUNDAMENTAL

Antes de generar cualquier artefacto, el sistema DEBE construir una capa de razonamiento previa.

## 🔴 CAPA DE RAZONAMIENTO GLOBAL

1. Entendimiento del dominio  
2. Identificación de actores y usuarios  
3. Objetivos de negocio  
4. Problemas reales a resolver  
5. Flujos de valor end-to-end  
6. Restricciones técnicas y organizativas  
7. Riesgos iniciales  
8. Alternativas arquitectónicas posibles  
9. Suposiciones explícitas  
10. Incertidumbres abiertas  

👉 Ningún artefacto puede generarse sin esta base.

---

# 1. 🧭 TIPOS DE PROYECTO

## 🟢 SMALL

- 1 dominio funcional principal  
- Baja complejidad  
- Sin integraciones críticas  
- 1 equipo  
- Time-to-market prioritario  

Arquitecturas mínimas a evaluar: **1**

## 🟡 MEDIUM

- Múltiples módulos  
- Integraciones externas  
- Complejidad moderada  
- 1–3 equipos  
- Necesidad de escalado selectivo  

Arquitecturas mínimas a evaluar: **2**

## 🔴 COMPLEX

- Multi-dominio  
- Arquitectura distribuida  
- Alta incertidumbre  
- Multi-equipo  
- Seguridad / compliance relevante  
- Alta carga o crecimiento esperado  

Arquitecturas mínimas a evaluar: **3**

---

# 2. 📊 MATRIZ DE ARTEFACTOS

| Artefacto | SMALL | MEDIUM | COMPLEX | Destino |
|----------|------|--------|----------|----------|
| OVR | OBL | OBL | OBL | GitHub |
| REQMAP | OPT | OBL | OBL | GitHub |
| EPIC | OBL | OBL | OBL | Jira |
| STORY | OBL | OBL | OBL | Jira |
| TASK | OPT | OBL | OBL | Jira |
| SPIKE | OPT | OBL | OBL | Jira |
| RISK | OPT | OPT | OBL | GitHub / Jira |
| FLOW | OBL | OBL | OBL | GitHub |
| ARCH-OPTIONS | OPT | OBL | OBL | GitHub |
| ARCH-DECISION | OPT | OBL | OBL | GitHub |
| ARCH-L1 | OPT | OBL | OBL | GitHub |
| ARCH-L2 | — | OPT | OBL | GitHub |
| API | OPT | OBL | OBL | GitHub |
| DATA | OPT | OBL | OBL | GitHub |
| SEC | — | OPT | OBL | GitHub |
| DEP | — | OPT | OBL | GitHub / Jira |
| ADR | — | OPT | OBL | GitHub |
| OPS | — | OPT | OBL | GitHub |
| NFR | OPT | OBL | OBL | GitHub |
| MILE | — | OPT | OBL | Jira |
| ENAB | — | OPT | OBL | Jira |
| TEST | OPT | OBL | OBL | GitHub / Jira |
| OPENQ | OBL | OBL | OBL | GitHub / Jira |

Leyenda:

- **OBL** = Obligatorio  
- **OPT** = Opcional  
- **—** = No requerido

---

# 🧠 3. CAPAS DE RAZONAMIENTO OBLIGATORIAS

## 🧭 3.1 DOMAIN UNDERSTANDING LAYER

Debe identificar:

- usuarios  
- actores internos y externos  
- objetivos  
- dolores actuales  
- restricciones reales  
- oportunidades  

Genera:

- OVR

## 🧠 3.2 DOMAIN MODEL LAYER

Debe identificar:

- entidades  
- relaciones  
- eventos  
- reglas  
- invariantes  
- ownership de datos  

Genera:

- DATA  
- API  
- ARCH

## 🔄 3.3 FLOW THINKING LAYER

Debe modelar:

- procesos end-to-end  
- estados  
- validaciones  
- decisiones  
- errores  
- excepciones  
- handoffs  

Genera:

- FLOW  
- STORY  
- TEST

## 🧩 3.4 CAPABILITY DECOMPOSITION LAYER

Debe responder:

- ¿Qué debe poder hacer el sistema?  
- ¿Qué valor entrega?  

Genera:

- EPIC

## 🧠 3.5 PROBLEM DECOMPOSITION LAYER

Debe producir:

- problemas principales  
- subproblemas  
- acciones de usuario  
- vertical slices  

Genera:

- STORY  
- TASK

## 🏗️ 3.6 ARCHITECTURE REASONING LAYER

Debe analizar:

- escalabilidad  
- mantenibilidad  
- coste  
- operación  
- skill del equipo  
- velocidad de entrega  
- integraciones  
- riesgos  

Genera:

- ARCH-OPTIONS  
- ARCH-DECISION  
- ARCH-L1  
- ARCH-L2

## 📊 3.7 NFR REASONING LAYER

Debe identificar:

- usuarios concurrentes  
- picos de carga  
- latencia aceptable  
- uptime esperado  
- recuperación  
- auditoría  
- seguridad  

Genera:

- NFR

## 📐 3.8 DECISION LAYER

Debe incluir:

- problema  
- opciones  
- tradeoffs  
- decisión  
- consecuencias  

Genera:

- ADR

---

# 🏗️ 4. MULTI ARCHITECTURE DISCOVERY

## REGLA FUNDAMENTAL

Nunca asumir una única arquitectura desde el inicio.  
Siempre evaluar varias alternativas compatibles con requisitos.

## 4.1 Número mínimo de opciones

| Tipo | Opciones mínimas |
|------|------------------|
| SMALL | 1 |
| MEDIUM | 2 |
| COMPLEX | 3 |

## 4.2 Tipologías sugeridas a evaluar

- Monolito clásico  
- Monolito modular  
- Microservicios por dominio  
- Event-driven  
- Serverless  
- Hexagonal + servicios internos  
- SaaS + integración  
- Data-centric platform

## 4.3 ARCH-OPTIONS

Cada opción debe incluir:

- id  
- nombre  
- estilo  
- descripción  
- fortalezas  
- debilidades  
- riesgos  
- complejidad operativa  
- coste relativo  
- velocidad de entrega  
- escalabilidad  
- encaje con requisitos  
- cuándo NO usarla  

### Ejemplo

    ARCH_OPTIONS:
      - id: A1
        name: Monolito Modular
        style: modular_monolith

      - id: A2
        name: Microservicios por dominio
        style: microservices

      - id: A3
        name: Event Driven Serverless
        style: event_driven_serverless

## 4.4 ARCH-DECISION

Debe seleccionar una opción actual.

Campos obligatorios:

- selected_option  
- rationale  
- rejected_options_reason  
- risks_assumed  
- revisit_triggers  
- migration_paths  

### Ejemplo

    ARCH_DECISION:
      selected_option: A1
      rationale:
        - equipo pequeño
        - menor coste inicial
        - entrega rápida
      revisit_triggers:
        - >3 equipos
        - >10x tráfico
        - despliegues bloqueados por acoplamiento

---

# 📚 5. DEFINICIÓN DE ARTEFACTOS

## 🧭 OVR

Resumen ejecutivo.

Incluye:

- problema  
- oportunidad  
- usuarios  
- objetivos  
- alcance  
- exclusiones  
- métricas

## 🔗 REQMAP

Trazabilidad:

requisito → artefactos

## 📌 EPIC

Capacidad de negocio o capacidad del sistema.  
No tareas técnicas.

## 🧩 STORY

Formato:

> Como \<rol> quiero \<objetivo> para \<beneficio>

Debe ser:

- observable  
- pequeña  
- testeable  
- valiosa

## ⚙️ TASK

Trabajo técnico ejecutable.

## 🔬 SPIKE

Investigación de incertidumbre.

## ⚠️ RISK

Riesgo técnico, operativo o de negocio.

## 🔄 FLOW

Flujos en Mermaid + narrativa.

## 🏗️ ARCH-L1

Vista de alto nivel de la opción seleccionada.

## 🧱 ARCH-L2

Detalle técnico:

- componentes  
- límites  
- integración  
- datos  
- seguridad

## 🔌 API

Contratos:

- REST  
- GraphQL  
- eventos  
- batch

## 🗄️ DATA

Modelo de datos.

## 🔐 SEC

Modelo de seguridad:

- auth  
- roles  
- permisos  
- auditoría

## 🔗 DEP

Dependencias externas e internas.

## 📐 ADR

Registro de decisiones relevantes.

## 🚀 OPS

Operación:

- CI/CD  
- despliegue  
- observabilidad  
- runbooks  
- alertado

## 📊 NFR

Medibles.

Ejemplos:

- p95 < 300ms  
- uptime 99.9%  
- RPO 15m  
- RTO 1h

## 🗺️ MILE

Roadmap por fases.

## 🧰 ENAB

Trabajo técnico habilitador.

## 🧪 TEST

Estrategia:

- unitarias  
- integración  
- e2e  
- performance  
- seguridad

## ❓ OPENQ

Preguntas abiertas reales.

---

# 🔗 6. REGLA DE TRAZABILIDAD

Cada artefacto debe responder:

1. ¿Qué requisito cubre?  
2. ¿Qué problema resuelve?  
3. ¿Qué capa de razonamiento lo originó?  
4. ¿Qué dependencias tiene?  
5. ¿Qué impacto tiene si cambia?  

---

# 📐 7. VALIDACIÓN DE CALIDAD

Debe validarse:

- coherencia funcional  
- backlog consistente  
- historias no triviales  
- arquitectura justificada  
- varias opciones evaluadas  
- NFR alineados  
- riesgos identificados  
- decisiones explícitas

---

# 🧪 8. PRUEBA OBLIGATORIA DE MULTI-ARQUITECTURA

Para verificar que el sistema cumple ADS v2.1, usar este prompt de prueba.

## Caso de prueba

    Necesitamos una plataforma SaaS B2B para reservas de salas corporativas en varios países.

    Requisitos:
    - multi-tenant
    - 5.000 empresas cliente
    - integración con Google Workspace y Microsoft 365
    - app web + móvil
    - pagos por suscripción
    - crecimiento internacional
    - SLA 99.9%
    - equipo inicial de 6 personas
    - lanzamiento en 4 meses

## Resultado esperado mínimo

Para proyecto MEDIUM/COMPLEX la IA debe proponer al menos:

1. Monolito modular multi-tenant  
2. Microservicios por dominios clave  
3. Serverless / event-driven para integraciones  

Y seleccionar una opción justificada.

👉 Si solo devuelve una arquitectura, el sistema **NO cumple ADS v2.1**.

---

# 🎯 9. OBJETIVO DEL SISTEMA

Permitir generación automática de:

- backlog Jira de calidad  
- documentación GitHub útil  
- arquitectura inicial realista  
- varias alternativas arquitectónicas  
- decisiones justificadas  
- roadmap evolutivo

---

# 🚨 10. REGLA FINAL OBLIGATORIA

Nunca proponer una única arquitectura sin evaluar alternativas compatibles con requisitos, contexto, equipo y restricciones.

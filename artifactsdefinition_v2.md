# 📦 AI Project Artifacts Definition Spec (ADS v2 - Reasoning First)

Este documento define el sistema de artefactos utilizado para transformar requisitos funcionales en:

- Especificaciones de arquitectura
- Backlog estructurado en Jira
- Documentación en GitHub
- Validación automática de completitud
- 🧠 Modelo de razonamiento estructurado del sistema

---

# 🧠 0. PRINCIPIO FUNDAMENTAL (OBLIGATORIO)

Antes de generar cualquier artefacto, el sistema DEBE construir una **capa de razonamiento previa**.

## 🔴 CAPA DE RAZONAMIENTO GLOBAL

1. Entendimiento del dominio
2. Identificación de actores y usuarios
3. Problemas reales del negocio
4. Flujos de valor end-to-end
5. Modelo mental del sistema
6. Restricciones técnicas y de negocio

👉 Ningún artefacto puede generarse sin esta base.

---

# 1. 🧭 TIPOS DE PROYECTO

## 🟢 SMALL
- 1 dominio funcional
- Baja complejidad
- Sin integraciones críticas

## 🟡 MEDIUM
- Múltiples módulos
- Integraciones externas
- Complejidad media

## 🔴 COMPLEX
- Multi-dominio
- Arquitectura distribuida
- Alta incertidumbre
- Compliance / seguridad relevante

---

# 🧠 2. CAPAS DE RAZONAMIENTO OBLIGATORIAS

Cada artefacto debe derivarse explícitamente de una o más capas.

---

## 🧭 2.1 DOMAIN UNDERSTANDING LAYER

Base de todo el sistema:

- usuarios
- objetivos de negocio
- problemas
- contexto
- restricciones

👉 Genera OVR

---

## 🧠 2.2 DOMAIN MODEL LAYER

Obligatorio antes de DATA / API / ARCH:

- entities
- aggregates
- commands
- events
- invariants

👉 Evita modelos genéricos o incorrectos

---

## 🔄 2.3 FLOW THINKING LAYER

Obligatorio antes de FLOW / EPIC / STORY:

- flujos end-to-end
- edge cases
- estados del sistema
- errores

---

## 🧩 2.4 CAPABILITY DECOMPOSITION LAYER

Obligatorio antes de EPIC:

- capacidades del sistema
- qué hace el sistema (no cómo)

---

## 🧠 2.5 PROBLEM DECOMPOSITION LAYER

Obligatorio antes de STORY:

- problema → subproblemas → tareas funcionales
- evita historias superficiales

---

## 🏗️ 2.6 ARCHITECTURE REASONING LAYER

Antes de ARCH-L1 / ARCH-L2:

- tradeoffs
- escalabilidad
- restricciones técnicas
- decisiones de diseño

---

## 📊 2.7 NFR REASONING LAYER

Antes de NFR:

- carga esperada
- latencia
- disponibilidad
- resiliencia
- seguridad

---

## 📐 2.8 DECISION LAYER (ADR)

Antes de ADR:

- problema
- alternativas
- tradeoffs
- decisión

---

# 📊 3. MATRIZ DE ARTEFACTOS

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

---

# 📚 4. DEFINICIÓN DE ARTEFACTOS

---

## 🧭 OVR (Overview)

Derivado de DOMAIN UNDERSTANDING LAYER.

Incluye:
- contexto del sistema
- usuarios
- problemas
- objetivos
- restricciones
- métricas de éxito

---

## 🔗 REQMAP

Trazabilidad entre requisitos y artefactos.

---

## 📌 EPIC

Derivado de CAPABILITY LAYER.

Representa:
- capacidad del sistema
- valor de negocio
- no implementación técnica

---

## 🧩 STORY

Derivada de PROBLEM DECOMPOSITION.

Formato:
> Como <rol> quiero <acción> para <beneficio>

Debe ser observable y verificable.

---

## ⚙️ TASK

Descomposición técnica ejecutable.

---

## 🔬 SPIKE

Investigación técnica o incertidumbre.

---

## ⚠️ RISK

Riesgos técnicos o de negocio.

---

## 🔄 FLOW

Derivado de FLOW THINKING LAYER.

Incluye:
- actores
- pasos
- estados
- edge cases
- errores

---

## 🏗️ ARCH-L1

Arquitectura de alto nivel.

Derivada de ARCHITECTURE REASONING LAYER.

---

## 🧱 ARCH-L2

Arquitectura detallada:
- componentes
- integración
- diseño interno

---

## 🔌 API

Contratos de integración.

---

## 🗄️ DATA

Derivado de DOMAIN MODEL LAYER.

Incluye:
- entidades
- relaciones
- atributos
- invariantes

---

## 🔐 SEC

Modelo de seguridad:
- auth
- roles
- permisos

---

## 🔗 DEP

Dependencias externas o internas.

---

## 📐 ADR

Derivado de DECISION LAYER.

Debe incluir:
- problema
- alternativas
- tradeoffs
- decisión

---

## 🚀 OPS

Operación del sistema:
- despliegue
- observabilidad
- runbooks

---

## 📊 NFR

Derivado de NFR REASONING LAYER.

Incluye:
- latencia (p95/p99)
- throughput
- disponibilidad
- resiliencia
- consistencia
- seguridad

---

## 🗺️ MILE

Roadmap por fases.

---

## 🧰 ENAB

Enablers técnicos.

---

## 🧪 TEST

Estrategia de pruebas:
- unitarias
- integración
- e2e
- criterios de aceptación

---

## ❓ OPENQ

Preguntas abiertas del sistema.

---

# 🔗 5. REGLA DE TRAZABILIDAD

Cada artefacto debe poder responder:

- ¿De qué capa de razonamiento proviene?
- ¿Qué problema resuelve?
- ¿Qué decisión lo justifica?

---

# 📐 6. VALIDACIÓN DE COMPLETITUD

El sistema debe validar:

- coherencia entre capas
- consistencia EPIC → STORY → TASK
- trazabilidad OVR → ARCH
- alineación NFR → ADR → ARCH
- ausencia de artefactos “huérfanos”

---

# 🎯 OBJETIVO DEL SISTEMA

Este ADS permite:

- generación de arquitectura coherente
- backlog Jira estructurado y justificable
- documentación GitHub consistente
- reducción de ambigüedad funcional
- trazabilidad completa extremo a extremo

---

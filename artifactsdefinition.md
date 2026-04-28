# 📦 AI Project Artifacts Definition Spec

Este documento define el sistema de artefactos utilizado para transformar requisitos funcionales en:

- Especificaciones de arquitectura
- Backlog estructurado en Jira
- Documentación en GitHub
- Validación automática de completitud

---

# 1. 🧭 Tipos de Proyecto

## 🟢 SMALL
Proyectos simples:
- 1 dominio funcional
- Sin integraciones complejas
- Baja incertidumbre
- 1 equipo

## 🟡 MEDIUM
Proyectos intermedios:
- Varios módulos
- Integraciones externas
- Complejidad moderada
- 1–3 equipos

## 🔴 COMPLEX
Proyectos enterprise:
- Multi-dominio
- Arquitectura distribuida
- Integraciones críticas
- Alta incertidumbre
- Multi-equipo
- Compliance / seguridad relevante

---

# 2. 📊 Matriz de Artefactos (Unificada)


## Leyenda
- **OBL** = Obligatorio
- **OPT** = Opcional
- **—** = No requerido

---

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

---

# 3. 📚 Artifact Definition System (ADS)

## 🧭 OVR (Overview)
Resumen funcional del sistema:
- objetivos de negocio
- alcance
- exclusiones
- métricas de éxito

---

## 🔗 REQMAP (Requirements Mapping)
Trazabilidad entre requisitos y artefactos:
- asegura cobertura funcional
- conecta negocio con arquitectura

---

## 📌 EPIC
Bloques funcionales en Jira.

---

## 🧩 STORY
Historia de usuario:
> Como <rol> quiero <objetivo> para <beneficio>

---

## ⚙️ TASK
Descomposición técnica ejecutable.

---

## 🔬 SPIKE
Investigación o incertidumbre técnica.

---

## ⚠️ RISK
Riesgos del sistema:
- impacto
- probabilidad
- mitigación

---

## 🔄 FLOW
Flujos de negocio en Mermaid.

---

## 🏗️ ARCH-L1
Arquitectura de alto nivel:
- componentes principales
- visión global

---

## 🧱 ARCH-L2
Arquitectura detallada:
- capas
- integraciones
- diseño interno

---

## 🔌 API
Contratos de integración:
- endpoints
- métodos
- payloads

---

## 🗄️ DATA
Modelo de datos:
- entidades
- relaciones
- atributos

---

## 🔐 SEC
Modelo de seguridad:
- auth
- roles
- permisos

---

## 🔗 DEP
Dependencias externas o internas:
- sistemas terceros
- APIs
- legacy

---

## 📐 ADR
Decisiones arquitectónicas relevantes.

---

## 🚀 OPS
Operación del sistema:
- deployment
- observabilidad
- runbooks

---

## 📊 NFR
Requisitos no funcionales:
- performance
- seguridad
- escalabilidad
- disponibilidad
- auditoría

---

## 🗺️ MILE
Roadmap por fases.

---

## 🧰 ENAB
Enablers técnicos para desbloquear desarrollo.

---

## 🧪 TEST
Estrategia de pruebas:
- criterios de aceptación
- validación funcional
- cobertura

---

## ❓ OPENQ
Preguntas abiertas del sistema.

---

# 4. 🎯 Objetivo del Sistema

Este sistema permite:

- Generación automática de documentación en GitHub
- Creación estructurada de backlog en Jira
- Validación automática de completitud
- Trazabilidad extremo a extremo
- Reducción de ambigüedad en análisis funcional

---

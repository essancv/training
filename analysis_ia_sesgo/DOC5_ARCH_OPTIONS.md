# Architecture Options

## 1. Evaluación previa de necesidad arquitectónica

- Tamaño del proyecto: Clasificado como MEDIUM, con complejidad funcional y técnica moderadas.
- Complejidad funcional: Media, con 6 dominios claramente identificados y reglas de trazabilidad y construcción de prompt.
- RNF relevantes:
  - NFR-001 Rendimiento moderado (tiempos de respuesta aceptables, no en tiempo real masivo).
  - NFR-002 Seguridad en gestión de credenciales y acceso a APIs.
  - NFR-003 Trazabilidad y auditoría.
  - NFR-005 Extensibilidad para nuevos tipos de artefactos e integraciones.
  - NFR-006 Priorizar simplicidad arquitectónica.
- Riesgos de sobredimensionamiento:
  - Introducir microservicios, orquestadores complejos o infraestructuras distribuidas podría aumentar costes y complejidad operativa sin beneficios claros, dado el volumen de uso y datos.

## 2. Arquitecturas propuestas

### ARCH-OPT-001 – Monolito modular en capas (recomendada)
- Nivel de complejidad: Bajo-medio.
- Justificación basada en requisitos:
  - Adecuada para tamaño MEDIUM y equipo de 3–6 desarrolladores.
  - Permite separar claramente dominios (captura, orquestación, IA, ensamblado, integraciones) mediante módulos internos.
  - Facilita la trazabilidad y el control del flujo de datos en un único despliegue.
- Diagrama textual:
  - Capa de Presentación/API (REST o CLI) → Capa de Aplicación (orquestación de flujos, casos de uso) → Capa de Dominio (lógica de construcción de prompt, trazabilidad, ensamblado JSON) → Capa de Infraestructura (conectores IA, GitHub, Jira, almacenamiento).
- Ventajas:
  - Simplicidad de despliegue y operación.
  - Menor coste de infraestructura.
  - Facilidad de depuración y trazabilidad end-to-end.
  - Alineado con NFR-006 (simplicidad).
- Inconvenientes:
  - Escalado principalmente vertical; menos flexible si el uso creciera mucho.
  - Despliegues acoplados (todo el sistema se despliega junto).
- Riesgos:
  - Si el producto crece mucho en funcionalidades y usuarios, podría requerir refactorización futura.
- Coste relativo: Bajo.
- Adecuación al tamaño del proyecto: Alta para MEDIUM.

### ARCH-OPT-002 – Monolito modular con Clean Architecture / Hexagonal
- Nivel de complejidad: Medio.
- Justificación basada en requisitos:
  - Refuerza la separación entre lógica de dominio (trazabilidad, construcción de prompt) y detalles de infraestructura (IA, GitHub, Jira).
  - Facilita la extensibilidad (NFR-005) y pruebas unitarias.
- Diagrama textual:
  - Núcleo de Dominio (entidades, servicios de dominio, reglas de trazabilidad) rodeado por Capa de Aplicación (casos de uso) y adaptadores de entrada (API/CLI) y salida (IA, GitHub, Jira, almacenamiento).
- Ventajas:
  - Alta mantenibilidad y testabilidad.
  - Facilidad para cambiar proveedores de IA o herramientas de ALM.
- Inconvenientes:
  - Mayor esfuerzo inicial de diseño y disciplina de implementación.
- Riesgos:
  - Sobrecarga conceptual si el equipo no está familiarizado con estos patrones.
- Coste relativo: Medio.
- Adecuación al tamaño del proyecto: Alta, especialmente si se prevé evolución del producto.

### ARCH-OPT-003 – Arquitectura de microservicios (no recomendada en esta fase)
- Nivel de complejidad: Alto.
- Justificación basada en requisitos:
  - Podría separar dominios (captura, IA, integraciones) en servicios independientes, pero no hay requisitos de escalabilidad extrema ni equipos grandes que lo justifiquen.
- Diagrama textual:
  - Servicio de Captura de Requisitos ↔ Servicio de Orquestación de Prompt ↔ Servicio de Análisis IA ↔ Servicio de Ensamblado JSON ↔ Servicio de Integración GitHub/Jira.
- Ventajas:
  - Escalado independiente por dominio.
  - Despliegues independientes.
- Inconvenientes:
  - Complejidad operativa (orquestación, observabilidad, comunicación entre servicios).
  - Mayor coste de infraestructura y DevOps.
- Riesgos:
  - Sobredimensionamiento claro para un proyecto MEDIUM.
  - Mayor probabilidad de fallos distribuidos.
- Coste relativo: Alto.
- Adecuación al tamaño del proyecto: Baja.

## 3. Recomendación basada en simplicidad

Siguiendo la regla de priorizar arquitecturas simples para proyectos SMALL/MEDIUM y dado que no existen requisitos de escalabilidad extrema ni equipos grandes, se recomienda:

- **Opción preferente**: ARCH-OPT-001 (Monolito modular en capas) o, si el equipo tiene experiencia, ARCH-OPT-002 (Monolito modular con Clean/Hexagonal) como evolución natural.
- **Opción a evitar en esta fase**: ARCH-OPT-003 (microservicios), salvo que futuros requisitos introduzcan cargas impredecibles, integraciones distribuidas complejas o un crecimiento significativo del equipo y del uso.

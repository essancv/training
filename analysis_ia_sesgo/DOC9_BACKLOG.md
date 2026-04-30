# Initial Product Backlog

## 1. Épicas
- **EPIC-001 – Ingesta y gestión de requisitos**
- **EPIC-002 – Orquestación de IA y generación de análisis**
- **EPIC-003 – Modelo de datos y JSON unificado**
- **EPIC-004 – Sizing, costes y riesgos**
- **EPIC-005 – Integración con GitHub y Jira**

## 2. Features
- **FEATURE-001 – Captura estructurada de requisitos** (EPIC-001)
- **FEATURE-002 – Trazabilidad requisitos-artefactos** (EPIC-001, EPIC-003)
- **FEATURE-003 – Construcción de prompt estándar** (EPIC-002)
- **FEATURE-004 – Cliente de IA y validación de salida** (EPIC-002)
- **FEATURE-005 – Definición de esquema JSON DOC00–DOC10** (EPIC-003)
- **FEATURE-006 – Motor de sizing y clasificación SMALL/MEDIUM/LARGE** (EPIC-004)
- **FEATURE-007 – Estimación de costes y registro de riesgos** (EPIC-004)
- **FEATURE-008 – Script Python para GitHub** (EPIC-005)
- **FEATURE-009 – Script Python para Jira** (EPIC-005)

## 3. Historias de usuario

### STORY-001 – Captura de requisitos
- **Como** analista (ACT-001)
- **Quiero** introducir requisitos funcionales con identificadores únicos
- **Para** que la IA pueda analizarlos y mantener trazabilidad.
- **Origen:** FR-001, FR-004.

### STORY-002 – Construcción de prompt estándar
- **Como** arquitecto / analista
- **Quiero** disponer de un prompt estándar que incluya reglas anti-sobredimensionamiento
- **Para** que la IA genere análisis proporcionados al tamaño del proyecto.
- **Origen:** FR-002, NFR-001.

### STORY-003 – Generación de JSON unificado
- **Como** product owner
- **Quiero** que el sistema genere un único JSON con DOC00–DOC10
- **Para** poder integrarlo fácilmente con GitHub y Jira.
- **Origen:** FR-003, FR-007, NFR-002.

### STORY-004 – Validación de esquema JSON
- **Como** desarrollador
- **Quiero** validar el JSON generado contra un esquema versionado
- **Para** asegurar que la integración con herramientas externas no falle.
- **Origen:** FR-007, NFR-002.

### STORY-005 – Sizing y clasificación del proyecto
- **Como** arquitecto
- **Quiero** que el sistema estime el tamaño del proyecto y lo clasifique (SMALL/MEDIUM/LARGE)
- **Para** seleccionar la arquitectura adecuada.
- **Origen:** Requisitos de sizing implícitos, NFR-001.

### STORY-006 – Estimación de costes y riesgos
- **Como** product owner
- **Quiero** obtener una estimación inicial de costes y un registro de riesgos
- **Para** tomar decisiones de planificación y presupuesto.
- **Origen:** Requisitos de costes y riesgos.

### STORY-007 – Script de integración con GitHub
- **Como** DevOps
- **Quiero** un script en Python que suba el JSON a GitHub
- **Para** versionar el análisis de forma automática.
- **Origen:** FR-005.

### STORY-008 – Script de integración con Jira
- **Como** DevOps
- **Quiero** un script en Python que cree o actualice issues en Jira a partir del backlog
- **Para** mantener alineado el trabajo del equipo con el análisis generado.
- **Origen:** FR-006.

## 4. Dependencias
- **STORY-002** depende de **STORY-001** (se necesitan requisitos capturados para construir el prompt).
- **STORY-003** depende de **STORY-002** y **STORY-004** (prompt y validación de esquema).
- **STORY-005** y **STORY-006** dependen de **STORY-003** (JSON con análisis completo).
- **STORY-007** y **STORY-008** dependen de **STORY-003** y **STORY-004** (JSON estable y validado).

## 5. Priorización MoSCoW
- **Must have:** STORY-001, STORY-002, STORY-003, STORY-004, STORY-007, STORY-008.
- **Should have:** STORY-005, STORY-006.
- **Could have:** Cualquier mejora futura de UI o reporting adicional.
- **Won't have (por ahora):** Integraciones con otras herramientas distintas de GitHub y Jira.

## 6. Registro de dudas, acciones y refinamientos para JIRA

### JIRA-ITEM-001
- **Origen:** DOC00, DOC1.
- **Tipo:** duda.
- **Descripción:** Definir el formato exacto de entrada de requisitos (JSON, Markdown estructurado, formulario, etc.).
- **Impacto:** Alto (afecta a la ingesta y trazabilidad).
- **Prioridad sugerida:** Alta.
- **Propuesta de resolución:** Taller de definición con stakeholders para acordar un formato estándar y ejemplos.

### JIRA-ITEM-002
- **Origen:** DOC00, DOC5.
- **Tipo:** aclaración.
- **Descripción:** Acordar si se requiere una interfaz gráfica (SPA) o si basta con API/CLI.
- **Impacto:** Medio (afecta a la arquitectura y al esfuerzo de desarrollo).
- **Prioridad sugerida:** Media.
- **Propuesta de resolución:** Decisión en comité de producto y arquitectura basada en el perfil de usuarios.

### JIRA-ITEM-003
- **Origen:** DOC7.
- **Tipo:** riesgo.
- **Descripción:** Gestión segura de credenciales de GitHub y Jira.
- **Impacto:** Alto.
- **Prioridad sugerida:** Alta.
- **Propuesta de resolución:** Implementar almacenamiento seguro de credenciales (vault o similar) y políticas de rotación.

### JIRA-ITEM-004
- **Origen:** DOC8.
- **Tipo:** refinamiento.
- **Descripción:** Ajustar las estimaciones de horas por dominio tras un primer prototipo.
- **Impacto:** Medio.
- **Prioridad sugerida:** Media.
- **Propuesta de resolución:** Revisar estimaciones después de la primera iteración y actualizar el modelo de costes.

### JIRA-ITEM-005
- **Origen:** DOC2, DOC3.
- **Tipo:** accion.
- **Descripción:** Definir ejemplos concretos de entrada y salida (requisitos de muestra y JSON resultante) para pruebas.
- **Impacto:** Alto (facilita validación y adopción).
- **Prioridad sugerida:** Alta.
- **Propuesta de resolución:** Crear un conjunto de casos de prueba representativos y documentarlos en el repositorio.

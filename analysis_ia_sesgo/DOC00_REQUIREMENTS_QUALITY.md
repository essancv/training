# Requirements Quality Assessment (DOC00)
## 1. Introducción
Este documento evalúa la calidad de los requisitos funcionales (FR) y no funcionales (NFR) para la plataforma de revisión automática de código basada en IA integrada con Pull Requests y CI/CD.

## 2. Evaluación global de calidad
- Cobertura: Los requisitos cubren funcionalidad principal (análisis PR, API, CI/CD, costes, seguridad, extensibilidad).
- Granularidad: FR bien listados pero algunos son demasiado amplios (p.ej. FR-001, FR-015, FR-014).
- Trazabilidad: No hay aún trazabilidad explícita FR↔flujos↔dominios.
- Priorización: No se indica prioridad (Must/Should/Could/Won't).
- Medibilidad: Algunos NFR son vagos (p.ej. NFR-001 Escalabilidad, NFR-003 Alta disponibilidad) sin métricas concretas.

## 3. Análisis individual de requisitos
- FR-001 Análisis automático de Pull Requests: Claro en intención, falta detalle de triggers, alcance de análisis y límites de tamaño.
- FR-002 Generación de informe de análisis: Falta formato estándar, estructura y nivel de detalle.
- FR-003 Integración con sistemas Git: Se menciona Git/GitHub, pero no se aclara si se soportarán otros proveedores desde el inicio.
- FR-004 Ejecución en CI CD: Falta aclarar modos de ejecución (sincronía, asincronía, reintentos, timeouts).
- FR-005 Motor de prompts modular: Correcto pero sin criterios de versionado ni gobernanza.
- FR-006 Soporte Java Spring Boot: Claro como alcance inicial.
- FR-007 API REST de análisis: Falta especificar autenticación, límites de uso y formatos de entrada/salida.
- FR-008 Publicación de resultados en Pull Requests: Falta definir granularidad de comentarios (por archivo, por línea, resumen global).
- FR-009 Configuración por repositorio: No se detalla dónde se almacena ni el modelo de configuración.
- FR-010 Gestión de errores: Requisito amplio, sin taxonomía de errores ni estrategia de comunicación.
- FR-011 Gestión de costes y trial: Falta modelo de cuotas, límites y métricas de consumo.
- FR-012 Explicabilidad de resultados: No se define nivel mínimo de explicabilidad.
- FR-013 Feedback de calidad: Falta aclarar cómo se usa el feedback para mejorar el sistema.
- FR-014 Extensibilidad tecnológica: No se define roadmap ni mecanismo de plugins.
- FR-015 Seguridad en análisis: Requisito amplio, solapado con NFR de seguridad y privacidad.

- NFR-001 Escalabilidad: No se definen umbrales (número de PR/día, concurrencia).
- NFR-002 Rendimiento menor a 60 segundos configurable: Claro pero falta definir percentil (p95/p99) y tamaño máximo de PR.
- NFR-003 Alta disponibilidad: No se define objetivo (p.ej. 99.5%, 99.9%).
- NFR-004 Seguridad HTTPS API keys protección de datos: Correcto pero genérico.
- NFR-005 Privacidad de datos: No se detalla retención ni anonimización.
- NFR-006 Mantenibilidad modular: Correcto, requiere reflejarse en arquitectura.
- NFR-007 Extensibilidad: Relacionado con FR-014, falta modelo concreto.
- NFR-008 Observabilidad logs: No se definen métricas mínimas ni paneles.
- NFR-009 Control de costes: Falta umbrales y políticas de corte.
- NFR-010 Trazabilidad de análisis: No se define nivel de trazas ni correlación con PR.
- NFR-011 Compatibilidad GitHub y otros Git: Falta lista de objetivos y estrategia de abstracción.

## 4. Ambigüedades y contradicciones
- Ambigüedad en persistencia: "No persistencia de código por defecto" vs necesidad de trazabilidad y feedback (FLOW-003) que implica algún tipo de almacenamiento.
- Extensibilidad tecnológica (FR-014, NFR-007) sin aclarar si es prioridad inicial o roadmap.
- Control de costes (FR-011, NFR-009) sin modelo de pricing ni límites concretos.

## 5. Preguntas de refinamiento
- Q-RQ-001: ¿Cuál es el tamaño máximo de PR soportado y cómo se gestiona el fallback si se supera?
- Q-RQ-002: ¿Qué proveedores de IA se usarán inicialmente y se requiere multi-proveedor desde el inicio?
- Q-RQ-003: ¿Se almacenarán resultados de análisis y feedback de forma persistente? ¿Durante cuánto tiempo?
- Q-RQ-004: ¿Cuál es el objetivo de disponibilidad (SLA) y el percentil de rendimiento objetivo?
- Q-RQ-005: ¿Qué otros proveedores Git además de GitHub son prioritarios?

## 6. Recomendaciones de mejora
- Introducir priorización MoSCoW para FR y NFR.
- Definir métricas cuantitativas para NFR clave (rendimiento, disponibilidad, escalabilidad, costes).
- Aclarar modelo de persistencia de resultados y feedback respetando privacidad.
- Documentar un formato estándar de informe de análisis.
- Establecer una matriz de trazabilidad FR↔flujos↔dominios↔backlog.

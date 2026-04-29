# Initial Requirements Analysis

## 1. Resumen ejecutivo
Se requiere una solución capaz de transformar requisitos funcionales aportados por usuarios en un análisis integral automatizado mediante IA. El sistema recibirá entradas funcionales, construirá prompts especializados y solicitará a un modelo de IA la generación de un único JSON estructurado que contenga análisis funcional, arquitectura, sizing, riesgos, costes y backlog inicial. Dicho JSON deberá mantener trazabilidad entre requisitos y artefactos generados, permitiendo su posterior carga automática en GitHub y Jira mediante scripts en Python.

## 2. Objetivos del proyecto
- Automatizar el análisis inicial de proyectos software.
- Reducir tiempo de discovery y definición funcional.
- Generar documentación estructurada y reutilizable.
- Garantizar trazabilidad entre requisitos y entregables.
- Facilitar carga automática en GitHub y Jira.
- Estandarizar outputs de análisis entre equipos.
- Habilitar escalabilidad para múltiples tipos de proyectos.

## 3. Alcance
### In scope
- Captura de requisitos funcionales del usuario.
- Normalización y validación de requisitos.
- Generación de prompts especializados.
- Invocación a IA externa.
- Generación de JSON único estructurado.
- Análisis inicial de requisitos.
- Mapa de dominios y bloques funcionales.
- Flujos de negocio.
- Sizing del proyecto.
- Opciones y comparación de arquitecturas.
- Riesgos y dependencias.
- Estimación inicial de costes.
- Backlog inicial (épicas, historias, tareas, spikes).
- Script Python para subida automática a GitHub.
- Script Python para subida automática a Jira.
- Trazabilidad entre requisitos y outputs.
### Out of scope
- Ejecución del proyecto desarrollado.
- Implementación del software analizado.
- Gestión financiera detallada del proyecto.
- PMO completa.
- Desarrollo automático de código productivo.
- Integraciones con herramientas no definidas.

## 4. Actores y stakeholders
- Nombre: Usuario solicitante
- Rol: Solicitante funcional
- Interés: Obtener análisis rápido y consistente
- Responsabilidades: Introducir requisitos completos

- Nombre: Product Owner
- Rol: Responsable funcional
- Interés: Backlog y priorización inicial
- Responsabilidades: Validar outputs

- Nombre: Arquitecto Software
- Rol: Responsable técnico
- Interés: Opciones arquitectónicas viables
- Responsabilidades: Revisar arquitectura propuesta

- Nombre: Equipo Delivery
- Rol: Ejecución del proyecto
- Interés: Base documental inicial
- Responsabilidades: Refinamiento posterior

- Nombre: GitHub
- Rol: Plataforma destino
- Interés: Recepción automática de artefactos
- Responsabilidades: APIs repositorio/issues

- Nombre: Jira
- Rol: Plataforma destino
- Interés: Gestión backlog
- Responsabilidades: APIs issues/epics/stories

- Nombre: Proveedor IA
- Rol: Motor generativo externo
- Interés: Consumo API
- Responsabilidades: Generar respuesta estructurada

## 5. Supuestos y restricciones
### Supuestos
- Existe API de IA consumible por HTTPS.
- GitHub y Jira disponen de credenciales válidas.
- Los requisitos de entrada son suficientemente claros.
- El JSON tendrá esquema versionado.
- El script Python podrá ejecutarse en entorno corporativo.

### Restricciones
- JSON único obligatorio como salida principal.
- Trazabilidad obligatoria entre requisitos y artefactos.
- Integración inicial limitada a GitHub y Jira.
- Dependencia de proveedor externo IA.
- Seguridad de credenciales obligatoria.
- Formato interoperable y validable.

## 6. Requisitos funcionales reorganizados
- FR-001: Captura de requisitos  
  Permitir recibir requisitos funcionales en texto estructurado o libre.
- FR-002: Normalización de requisitos  
  Transformar entradas a formato consistente.
- FR-003: Generación de prompt  
  Construir prompt óptimo para IA según contexto.
- FR-004: Invocación a IA  
  Enviar prompt y recibir respuesta.
- FR-005: JSON unificado  
  Generar único JSON con todos los documentos.
- FR-006: Análisis inicial automático  
  Incluir objetivos, alcance, stakeholders y preguntas abiertas.
- FR-007: Mapa funcional  
  Generar dominios y bloques funcionales.
- FR-008: Flujos de negocio  
  Definir procesos y diagramas.
- FR-009: Sizing  
  Estimar complejidad y tamaño.
- FR-010: Arquitectura  
  Proponer opciones técnicas.
- FR-011: Comparativa de arquitectura  
  Evaluar alternativas.
- FR-012: Riesgos y dependencias  
  Identificar riesgos iniciales.
- FR-013: Costes iniciales  
  Estimar rangos económicos.
- FR-014: Backlog inicial  
  Generar épicas, historias, tareas y spikes.
- FR-015: Trazabilidad  
  Relacionar requisitos con outputs.
- FR-016: Exportación GitHub  
  Subir artefactos automáticamente.
- FR-017: Exportación Jira  
  Crear backlog automáticamente.
- FR-018: Script Python  
  Automatizar integraciones.

## 7. Requisitos no funcionales reorganizados
- NFR-001: Seguridad  
  Gestión segura de credenciales y tokens.
- NFR-002: Rendimiento  
  Tiempo de respuesta razonable para análisis completo.
- NFR-003: Escalabilidad  
  Soportar múltiples solicitudes concurrentes.
- NFR-004: Disponibilidad  
  Servicio accesible en horario operativo.
- NFR-005: Trazabilidad  
  Auditoría de inputs y outputs.
- NFR-006: Mantenibilidad  
  Código modular y extensible.
- NFR-007: Portabilidad  
  Script Python ejecutable en múltiples entornos.
- NFR-008: Interoperabilidad  
  Compatibilidad GitHub/Jira APIs.
- NFR-009: Calidad de datos  
  JSON válido y consistente.
- NFR-010: Versionado  
  Control de versiones de esquema y prompts.

## 8. Flujos de negocio preliminares
### FLOW-001: Generación de análisis completo
Descripción  
El usuario introduce requisitos y el sistema genera un JSON completo mediante IA.
Pasos:
1. Usuario envía requisitos.
2. Sistema normaliza entrada.
3. Se genera prompt.
4. Se invoca IA.
5. Se valida JSON.
6. Se entrega resultado.

### FLOW-002: Publicación automática en herramientas
Descripción  
El sistema usa el JSON generado para crear artefactos en GitHub y Jira.
Pasos:
1. Leer JSON generado.
2. Autenticar APIs externas.
3. Crear repositorio/issues GitHub.
4. Crear épicas e historias Jira.
5. Confirmar resultado.

## 9. Preguntas abiertas
- Q-001: ¿Se soportarán múltiples proveedores IA?
- Q-002: ¿Qué versión de Jira Cloud/Server?
- Q-003: ¿Debe generarse repositorio GitHub desde cero?
- Q-004: ¿Se requiere UI o solo CLI/API?
- Q-005: ¿Qué esquema JSON corporativo se adoptará?
- Q-006: ¿Cómo se gestionará el versionado de prompts?
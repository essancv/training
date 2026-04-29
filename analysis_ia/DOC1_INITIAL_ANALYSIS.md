# Initial Requirements Analysis

## 1. Resumen ejecutivo
Solución para transformar requisitos funcionales en un paquete documental automatizado mediante IA, exportable a GitHub y Jira.

## 2. Objetivos del proyecto
- Automatizar discovery inicial.
- Estandarizar entregables.
- Mantener trazabilidad extremo a extremo.
- Integrar con herramientas de delivery.

## 3. Alcance
### In scope
- Ingesta de requisitos.
- Prompting IA.
- JSON único estructurado.
- Exportación GitHub/Jira.
### Out of scope
- Desarrollo automático de software final.
- Gestión completa PPM.

## 4. Actores y stakeholders
- Nombre: Usuario
- Rol: Solicitante
- Interés: Análisis rápido
- Responsabilidades: Proveer requisitos
- Nombre: Product Owner
- Rol: Negocio
- Interés: Backlog inicial
- Responsabilidades: Validación
- Nombre: Arquitecto
- Rol: Técnico
- Interés: Arquitectura viable
- Responsabilidades: Revisión técnica

## 5. Supuestos y restricciones
### Supuestos
- APIs externas disponibles.
- Credenciales válidas.
### Restricciones
- JSON único obligatorio.
- Seguridad de secretos obligatoria.

## 6. Requisitos funcionales reorganizados
- FR-001: Captura de requisitos  
  Recibir requisitos funcionales de usuario.
- FR-002: Generación documental  
  Crear análisis completo por IA.
- FR-003: Exportación herramientas  
  Publicar en GitHub y Jira.

## 7. Requisitos no funcionales reorganizados
- NFR-001: Seguridad  
  Protección de credenciales y datos.
- NFR-002: Calidad  
  JSON válido y consistente.

## 8. Flujos de negocio preliminares
### FLOW-001: Generación análisis
Descripción  
Procesar requisitos y devolver JSON.
Pasos:
1. Recibir requisitos.
2. Invocar IA.
3. Validar JSON.

## 9. Preguntas abiertas
- Q-001: ¿Habrá interfaz web?
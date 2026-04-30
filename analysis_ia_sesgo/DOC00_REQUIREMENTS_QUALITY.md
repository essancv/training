# Introducción
Se analiza la solicitud de construir una solución que reciba requisitos funcionales, genere un prompt manualmente ajustado y lo envíe a una IA para producir documentación estructurada en JSON destinada a GitHub y Jira.

# Evaluación global
| Criterio | Resultado | Observación |
|---|---|---|
| Claridad | Media | Objetivo claro pero faltan restricciones operativas |
| Completitud | Media | No define autenticación, volumen, SLA, multiusuario |
| Consistencia | Alta | No se detectan contradicciones |
| Verificabilidad | Media | Faltan métricas de calidad de salida |
| Trazabilidad | Alta | Se exige explícitamente |
| Factibilidad | Alta | Solución viable con stack estándar |

# Requisitos identificados
## Funcionales
- FR-001 Capturar requisitos funcionales proporcionados por usuario.
- FR-002 Permitir construir o cargar prompt maestro manual.
- FR-003 Invocar IA externa con prompt y requisitos.
- FR-004 Generar único JSON con documentos DOC00-DOC10.
- FR-005 Mantener trazabilidad entre requisitos y artefactos.
- FR-006 Exportar JSON para GitHub.
- FR-007 Exportar JSON para Jira.
- FR-008 Ejecutar script Python de automatización.
- FR-009 Registrar errores y auditoría.
- FR-010 Validar JSON contra esquema.

## No funcionales
- NFR-001 Evitar sesgos y sobredimensionamiento.
- NFR-002 Salida inmutable y consistente.
- NFR-003 Seguridad de credenciales GitHub/Jira.
- NFR-004 Mantenibilidad del prompt y scripts.
- NFR-005 Tiempo de respuesta razonable.

# Ambigüedades detectadas
- ¿La IA será un proveedor específico o abstracta?
- ¿Se soportarán múltiples modelos?
- ¿El usuario interactúa vía CLI, web o API?
- ¿Se desea versionado histórico de prompts y salidas?
- ¿Qué campos exactos de Jira deben mapearse?
- ¿GitHub recibirá issues, wiki, repo files o releases?

# Riesgos de calidad de requisitos
- Riesgo de prompt demasiado rígido.
- Riesgo de cambios frecuentes en APIs externas.
- Riesgo de JSON válido sintácticamente pero pobre semánticamente.

# Preguntas abiertas
1. Número estimado de usuarios.
2. Idiomas soportados.
3. Límite de tamaño de entrada.
4. Entorno on-premise o cloud.
5. Necesidad de aprobación humana previa a publicar.

# Recomendaciones
- Definir contrato OpenAPI interno.
- Definir JSON Schema versionado.
- Añadir pruebas de regresión de prompts.
- Añadir revisión humana opcional.
- Medir calidad por cobertura de trazabilidad.
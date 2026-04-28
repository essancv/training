# Requisitos funcionales y no funcionales

## AI Code Review Platform

---

# 1. Introducción

Este documento define los requisitos funcionales y no funcionales de una plataforma de análisis automático de código basada en inteligencia artificial, integrada con procesos de Pull Request (PR), inicialmente orientada a equipos de desarrollo con Java y Spring Boot, pero diseñada con extensibilidad multi-tecnología.

---

# 2. Requisitos funcionales (RF)

## RF1 – Análisis automático de código en PR

El sistema debe analizar automáticamente el código de una Pull Request (diff) cuando esta se cree o actualice.

## RF2 – Generación de informe de análisis

El sistema debe generar un informe estructurado con los problemas detectados, mejoras recomendadas y buenas prácticas.

## RF3 – Integración con sistemas de control de versiones

El sistema debe integrarse con plataformas de repositorios Git para recibir eventos de PR y publicar resultados.

## RF4 – Ejecución automática en CI/CD

El análisis debe ejecutarse automáticamente en pipelines de integración continua ante eventos de PR.

## RF5 – Motor de prompts modular

El sistema debe construir prompts dinámicamente mediante capas:

* Base corporativa
* Específica por tecnología
* Específica por equipo

## RF6 – Soporte inicial para Java Spring Boot

El sistema debe soportar inicialmente análisis de código Java con Spring Boot.

## RF7 – API de análisis

El sistema debe exponer una API REST para ejecutar análisis de código de forma programática.

## RF8 – Publicación de resultados en PR

El sistema debe publicar el resultado del análisis como comentario en la Pull Request.

## RF9 – Configuración básica por repositorio

Debe ser posible activar o desactivar el análisis mediante configuración en el repositorio.

## RF10 – Gestión básica de errores

Si el análisis falla, el sistema debe notificar el error de forma clara en la PR o respuesta API.

## RF11 – Gestión de costes y trial

El sistema debe permitir habilitar o deshabilitar el uso del análisis en función de políticas de coste y un periodo de prueba configurado.

## RF12 – Explicabilidad de resultados

Cada issue detectado debe incluir una explicación clara de por qué se considera un problema y cómo solucionarlo.

## RF13 – Feedback de calidad

El sistema debe permitir registrar feedback sobre los resultados del análisis (ej. falsos positivos).

## RF14 – Extensibilidad tecnológica

El sistema debe permitir añadir nuevas tecnologías soportadas sin rediseño del núcleo.

## RF15 – Seguridad en el análisis

El sistema debe evitar la exposición de datos sensibles y detectar problemas de seguridad comunes (ej. OWASP Top 10).

---

# 3. Requisitos no funcionales (RNF)

## RNF1 – Escalabilidad

El sistema debe ser capaz de soportar múltiples análisis concurrentes de PR sin degradación significativa del servicio.

## RNF2 – Rendimiento

El tiempo de respuesta del análisis debe ser configurable, con un objetivo inicial inferior a 60 segundos para PRs estándar.

## RNF3 – Disponibilidad

El sistema debe diseñarse para alta disponibilidad en entorno cloud, con tolerancia a fallos del proveedor de IA.

## RNF4 – Seguridad

* Comunicación cifrada (HTTPS)
* Autenticación mediante API keys
* Protección de datos sensibles enviados al modelo

## RNF5 – Privacidad de datos

El sistema no debe almacenar código fuente de forma persistente salvo configuración explícita.

## RNF6 – Mantenibilidad

El sistema debe estar diseñado modularmente, separando:

* Motor de prompts
* Integración CI/CD
* Procesamiento de análisis
* Capa de API

## RNF7 – Extensibilidad

El sistema debe permitir añadir nuevas reglas, prompts y tecnologías sin modificar el núcleo del sistema.

## RNF8 – Observabilidad

El sistema debe registrar logs estructurados de:

* Ejecuciones de análisis
* Errores
* Uso de API

## RNF9 – Control de costes

El sistema debe permitir limitar el uso de recursos de IA por organización o repositorio.

## RNF10 – Trazabilidad

Cada análisis debe ser reproducible o trazable a partir de versión de prompt y configuración utilizada.

## RNF11 – Compatibilidad

El sistema debe integrarse inicialmente con GitHub y ser extensible a otras plataformas Git.

---

# 4. Supuestos y restricciones

* El sistema inicialmente se centrará en Java Spring Boot.
* La IA se consumirá a través de API externa o servicio equivalente.
* La integración inicial se realizará mediante CI/CD (GitHub Actions).

---

# 5. Objetivo del sistema

Proporcionar un asistente automático de revisión de código que mejore la calidad del software, reduzca errores comunes, refuerce buenas prácticas de arquitectura y seguridad, y homogeneice el estilo de desarrollo entre equipos.

7. Levantar cliente de kafka :

 curl.exe -v --http1.1 https://glowing-space-engine-7x49gjrp95gcp57g-8080.app.github.dev/stream/kafka

8. Crear un usuario

 $user=@{username='demo';nombre='Andres';apellidos='Carrera';password='demoaaa';email='a@a'} | ConvertTo-Json
  $result=Invoke-RestMethod -Uri "https://glowing-space-engine-7x49gjrp95gcp57g-8080.app.github.dev/api/users" -Method Post -Body $user4  -ContentType "application/json"

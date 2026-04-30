# Functional Domain Map

## 1. Introducción
Identificación de dominios funcionales de la plataforma de análisis de código con IA.

## 2. Bloques funcionales

### DOMAIN-001 INGESTA DE EVENTOS GIT
- Descripción: Captura eventos de Pull Requests desde GitHub
- Requisitos asociados: FR-001, FR-003
- Complejidad: Media
- Dependencias: GitHub API

### DOMAIN-002 MOTOR DE ANALISIS IA
- Descripción: Generación de análisis de código mediante IA
- Requisitos asociados: FR-001, FR-012, FR-015
- Complejidad: Alta
- Dependencias: AI Provider

### DOMAIN-003 GESTION DE INTEGRACIONES CI CD
- Descripción: Ejecución en pipelines
- Requisitos asociados: FR-004
- Complejidad: Media
- Dependencias: CI/CD systems

### DOMAIN-004 API Y ORQUESTACION
- Descripción: API REST para análisis
- Requisitos asociados: FR-007, FR-010
- Complejidad: Media

### DOMAIN-005 SEGURIDAD Y CONTROL
- Descripción: Seguridad, OWASP, autenticación
- Requisitos asociados: FR-015, NFR-004
- Complejidad: Alta

### DOMAIN-006 OBSERVABILIDAD Y COSTES
- Descripción: Logs, métricas, control coste IA
- Requisitos asociados: FR-011, NFR-009
- Complejidad: Media-Alta

## 3. Mapa general de dominios
```mermaid
flowchart TD
GIT_DOMAIN['Ingesta GitHub'] --> AI_DOMAIN['Motor Analisis IA']
AI_DOMAIN --> API_DOMAIN['API Orquestacion']
API_DOMAIN --> CI_DOMAIN['CI CD Integracion']
AI_DOMAIN --> SEC_DOMAIN['Seguridad y Control']
AI_DOMAIN --> OBS_DOMAIN['Observabilidad y Costes'
```
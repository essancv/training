# Initial Cost Estimate

## 1. Coste por bloque funcional
Estimacion inicial en horas para MVP MEDIUM.

- DOMAIN-001 Process Layer: 60 h
- DOMAIN-002 Diff And Context: 70 h
- DOMAIN-003 Prompt Engine: 80 h
- DOMAIN-004 Quality And Security: 120 h
- DOMAIN-005 Structured Report: 50 h
- DOMAIN-006 Checklist Gate: 30 h
- DOMAIN-007 Metrics And Audit: 40 h

Total estimado: 450 h

## 2. Coste por arquitectura
- ARCH-OPT-001: 450 h
- ARCH-OPT-002: 520 h por servicio API central y despliegue

## 3. Coste de infraestructura
- ARCH-OPT-001: Bajo. Principalmente runners de GitHub Actions
- ARCH-OPT-002: Medio. VM o contenedor para servicio mas logs

## 4. Coste de integraciones
- GitHub API permisos comentario y lectura: incluido en dominio
- LLM provider: integracion 40 h incluida en dominio de analisis

## 5. Coste de mantenimiento anual
- Ajuste de prompts reglas y thresholds: 80 h
- Actualizacion de herramientas y dependencias: 60 h
- Soporte operativo y incidencias: 60 h

Total mantenimiento anual estimado: 200 h

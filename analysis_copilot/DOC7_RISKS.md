# Risk and Dependency Register
## 1. Riesgos
### RISK 001 Dependencia de proveedor de IA
- Descripcion
  - Fuerte dependencia de un proveedor de IA externo para el analisis de codigo.
- Probabilidad
  - Media.
- Impacto
  - Alto por posible degradacion de servicio o cambios de precio.
- Mitigacion
  - Diseñar el motor de prompts y la integracion de forma desacoplada para poder cambiar de proveedor.
- Responsable
  - Product Owner Arquitecto.

### RISK 002 Costes de IA superiores a lo previsto
- Descripcion
  - El uso intensivo de analisis puede generar costes elevados.
- Probabilidad
  - Media alta.
- Impacto
  - Alto.
- Mitigacion
  - Implementar limites de uso por repositorio y alertas de consumo.
- Responsable
  - Organization Admin Product Owner.

### RISK 003 Rendimiento insuficiente
- Descripcion
  - El tiempo de respuesta puede superar los 60 segundos en analisis complejos.
- Probabilidad
  - Media.
- Impacto
  - Medio alto.
- Mitigacion
  - Optimizar prompts paralelizar analisis cuando sea posible y ajustar configuraciones de IA.
- Responsable
  - Equipo tecnico.

### RISK 004 Privacidad y cumplimiento
- Descripcion
  - Riesgos relacionados con el envio de codigo a servicios de IA externos.
- Probabilidad
  - Media.
- Impacto
  - Alto.
- Mitigacion
  - No persistir codigo por defecto y revisar terminos de uso del proveedor de IA.
- Responsable
  - Seguridad legal.

## 2. Dependencias
### DEP 001 GitHub y otros proveedores Git
- Descripcion
  - Dependencia de APIs y webhooks de GitHub y otros proveedores.
- Criticidad
  - Alta.

### DEP 002 Proveedor de IA
- Descripcion
  - Disponibilidad y rendimiento del servicio de IA.
- Criticidad
  - Alta.

### DEP 003 Infraestructura de despliegue
- Descripcion
  - Entorno de ejecucion Docker o maquinas virtuales y servicios de base de datos.
- Criticidad
  - Media.

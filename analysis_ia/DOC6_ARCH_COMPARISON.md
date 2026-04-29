# Architecture Comparison Matrix

## 1. Matriz comparativa
| Arquitectura | Escalabilidad | Coste | Complejidad | Seguridad | Integraciones | Time-to-market | Adecuación RNF |
| Monolito API + Workers | Media | Bajo | Baja | Media Alta | Alta | Alta | Media |
| Microservicios desacoplados | Alta | Alto | Alta | Alta | Alta | Media | Alta |
| Serverless gestionado | Alta | Medio | Media | Alta | Media Alta | Alta | Media Alta |

## 2. Recomendación razonada
Se recomienda iniciar con Monolito API + Workers para acelerar entrega y evolucionar a microservicios cuando aumente volumen o variedad de conectores.
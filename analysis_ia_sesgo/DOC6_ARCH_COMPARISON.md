# Architecture Comparison Matrix

## 1. Matriz comparativa

| Arquitectura | Complejidad | Stack | Coste | Riesgo | Adecuación RNF |
| --- | --- | --- | --- | --- | --- |
| ARCH-OPT-001 Monolito modular en capas | Baja-media | Backend Python FastAPI, sin SPA obligatoria, DB ligera | Bajo | Bajo | Alta, cumple anti sobredimensionamiento y simplicidad |
| ARCH-OPT-002 SPA ligera + API REST monolitica | Media | Frontend React o Vue, Backend Node o Python, DB ligera | Medio | Medio | Media, adecuada si se requiere UI rica pero puede ser excesiva |

## 2. Recomendación razonada
La arquitectura **ARCH-OPT-001** ofrece el mejor equilibrio entre simplicidad, coste y adecuación a los requisitos no funcionales. El proyecto no presenta necesidades de escalabilidad extrema ni de interfaces de usuario complejas que justifiquen una SPA dedicada. Por tanto, se recomienda adoptar un **monolito modular en capas** con un backend ligero (Python/FastAPI) y mantener la opción de añadir una UI más rica en el futuro si surgen nuevos requisitos.

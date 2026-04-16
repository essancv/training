# 📦 Event Client SDK — Instalación y Uso

Este documento describe cómo construir, probar y usar el SDK `event-client-sdk`, así como las decisiones técnicas adoptadas durante su configuración.

---

# 🧠 1. Objetivo

Construir un SDK frontend **agnóstico de framework** (React, Angular, JS puro) que:

* Consuma eventos vía **WebSocket o SSE**
* Sea compatible con bundlers modernos (Vite, Webpack…)
* Exporte correctamente módulos **ESM + CommonJS**
* Sea publicable en npm o instalable localmente

---

# 🧹 2. Limpieza inicial

Antes de configurar correctamente el proyecto, se realizó una limpieza completa:

```bash
rm -rf dist
rm -rf node_modules
rm -f package-lock.json
```

### ✅ ¿Por qué?

* Evitar artefactos corruptos (`.tgz`, builds previos)
* Eliminar configuraciones inconsistentes
* Garantizar un build reproducible

---

# ⚙️ 3. Configuración del SDK

## 📄 `package.json`

Configuración clave:

```json
{
  "name": "event-client-sdk",
  "version": "1.0.0",
  "type": "module",

  "main": "dist/index.cjs",
  "module": "dist/index.mjs",
  "types": "dist/index.d.ts",

  "exports": {
    ".": {
      "import": "./dist/index.mjs",
      "require": "./dist/index.cjs"
    }
  },

  "files": ["dist"],

  "scripts": {
    "build": "tsup",
    "dev": "tsup --watch"
  }
}
```

---

## 🧠 ¿Por qué esta configuración?

| Campo            | Motivo                    |
| ---------------- | ------------------------- |
| `type: module`   | Soporte moderno ESM       |
| `main`           | Compatibilidad Node (CJS) |
| `module`         | Usado por bundlers (Vite) |
| `exports.import` | Fuerza uso de ESM         |
| `files`          | Publicar solo `dist`      |

---

# 🏗️ 4. Configuración de build (`tsup`)

## 📄 `tsup.config.ts`

```ts
import { defineConfig } from 'tsup';

export default defineConfig({
  entry: {
    index: 'src/index.ts'
  },
  format: ['esm', 'cjs'],
  dts: true,
  sourcemap: true,
  clean: true,
  splitting: false
});
```

---

## 🧠 ¿Por qué?

* `esm` → necesario para Vite/browser
* `cjs` → compatibilidad Node
* `dts` → soporte TypeScript
* `splitting: false` → evita múltiples chunks

---

# 🧩 5. Entry point del SDK

## 📄 `src/index.ts`

```ts
export { EventClient } from './core/EventClient';
export * from './core/types';
```

---

## 🧠 ¿Por qué?

* Define la API pública del SDK
* Evita imports internos
* Simplifica uso externo:

```ts
import { EventClient } from 'event-client-sdk';
```

---

# 🔨 6. Construcción del SDK

```bash
npm install
npm run build
```

---

## 📁 Resultado esperado

```text
dist/
  index.mjs      ✅ ESM (Vite)
  index.cjs      ✅ CommonJS
  index.d.ts     ✅ Tipos
```

---

## 🧠 Problema resuelto

Inicialmente:

* Vite cargaba `index.js` (incorrecto)
* → error: `does not provide an export`

Solución:

👉 apuntar `exports.import` a `index.mjs`

---

# 🧪 7. Crear ejemplo con Vite

## 📁 Estructura

```text
examples/js-vite/
  index.html
  main.js
  package.json
```

---

## 📄 `package.json` del ejemplo

```json
{
  "name": "js-vite-example",
  "private": true,
  "type": "module",

  "scripts": {
    "dev": "vite --host",
    "build": "vite build"
  },

  "dependencies": {
    "event-client-sdk": "file:../.."
  },

  "devDependencies": {
    "vite": "^8.0.0"
  }
}
```

---

## 🧠 ¿Por qué?

* `file:../..` → usar SDK local
* `vite --host` → necesario en Codespaces
* proyecto separado → evitar conflictos con SDK

---

# ▶️ 8. Ejecutar ejemplo

```bash
cd examples/js-vite
npm install
npm run dev
```

---

# ⚠️ Problemas resueltos

## ❌ 404 en `/main.js`

* causado por Vite sin `--host`

## ❌ Error export

* causado por usar `index.js` en vez de `index.mjs`

## ❌ `Cannot find src/index.ts`

* causado por ejecutar comandos en carpeta incorrecta

---

# 📦 9. Generar paquete instalable (`.tgz`)

## 👉 Crear paquete

Desde la raíz del SDK:

```bash
npm run build
npm pack
```

---

## 📁 Resultado

```text
event-client-sdk-1.0.0.tgz
```

---

## 🧠 ¿Qué es esto?

* paquete real npm
* contiene solo `dist/`
* listo para distribución

---

# 📥 10. Instalar en otro proyecto

## 👉 Opción 1: desde `.tgz`

```bash
npm install ../event-client-sdk-1.0.0.tgz
```

---

## 👉 Opción 2: desde carpeta local

```bash
npm install ../eventclient
```

---

## 👉 Opción 3: (futuro) desde npm

```bash
npm install event-client-sdk
```

---

# 🚀 11. Uso básico

```js
import { EventClient } from 'event-client-sdk';

const client = new EventClient({
  url: 'ws://localhost:8080/events',
  transport: 'websocket'
});

client.connect();

client.subscribe('orders', (event) => {
  console.log(event);
});
```

---

# 🧠 Conclusión

Se ha conseguido:

* SDK portable y limpio ✅
* compatible con Vite/React/Angular ✅
* sin errores de módulos ✅
* listo para publicación en npm ✅

---

# 🔜 Siguientes pasos recomendados

* Publicar en npm (`npm publish`)
* Añadir tests
* Añadir reconexión automática
* Versionado semántico (semver)

---

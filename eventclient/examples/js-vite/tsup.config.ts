import { defineConfig } from 'tsup';

export default defineConfig({
  entry: ['src/index.ts'],   // 👈 punto de entrada
  format: ['esm', 'cjs'],    // 👈 genera .mjs y .cjs
  dts: true,                 // 👈 genera tipos
  sourcemap: true,
  clean: true,
  splitting: false           // 👈 evita líos con múltiples chunks
});
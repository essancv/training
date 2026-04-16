// ------------------------------
// src/internal/EventEmitter.ts
// ------------------------------
export class InternalEmitter {
  private listeners = new Map<string, Set<Function>>();

  on(event: string, handler: Function) {
    if (!this.listeners.has(event)) {
      this.listeners.set(event, new Set());
    }
    this.listeners.get(event)!.add(handler);
  }

  emit(event: string, payload?: any) {
    this.listeners.get(event)?.forEach((h) => h(payload));
  }
}

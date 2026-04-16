// ------------------------------
// src/internal/AckManager.ts
// ------------------------------
export class AckManager {
  private resolvers = new Map<string, Function>();

  register(id: string, resolve: Function) {
    this.resolvers.set(id, resolve);
  }

  resolve(id: string) {
    this.resolvers.get(id)?.();
    this.resolvers.delete(id);
  }
}


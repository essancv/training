// ------------------------------
// src/subscription/SubscriptionManager.ts
// ------------------------------
export class SubscriptionManager {
  private handlers = new Map<string, Set<EventHandler>>();

  subscribe(topic: string, handler: EventHandler) {
    if (!this.handlers.has(topic)) {
      this.handlers.set(topic, new Set());
    }
    this.handlers.get(topic)!.add(handler);

    return {
      unsubscribe: () => this.handlers.get(topic)?.delete(handler)
    };
  }

  hasTopic(topic: string) {
    return (this.handlers.get(topic)?.size ?? 0) > 0;
  }

  dispatch(event: ServerEvent) {
    this.handlers.get(event.topic)?.forEach((h) => h(event));
  }
}

// ------------------------------
// src/stream/EventStream.ts
// ------------------------------

import { ServerEvent } from "../core/types";
export class EventStream<T> implements AsyncIterable<ServerEvent<T>> {
  private queue: ServerEvent<T>[] = [];
  private resolvers: Function[] = [];

  constructor(private subscribeFn: Function, private topic: string, private bufferSize: number) {}

  async *[Symbol.asyncIterator]() {
    const sub = this.subscribeFn(this.topic, (event: ServerEvent<T>) => {
      if (this.queue.length >= this.bufferSize) this.queue.shift();
      this.queue.push(event);
      this.resolvers.shift()?.();
    });

    try {
      while (true) {
        if (!this.queue.length) {
          await new Promise((r) => this.resolvers.push(r));
        }
        yield this.queue.shift()!;
      }
    } finally {
      sub.unsubscribe();
    }
  }
}


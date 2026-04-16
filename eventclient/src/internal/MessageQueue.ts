

// ------------------------------
// src/internal/MessageQueue.ts
// ------------------------------
export class MessageQueue {
  private queue: any[] = [];

  enqueue(msg: any) {
    this.queue.push(msg);
  }

  flush(send: (msg: any) => void) {
    this.queue.forEach(send);
    this.queue = [];
  }
}

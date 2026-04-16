// ------------------------------
// src/core/EventClient.ts
// ------------------------------
import { InternalEmitter } from '../internal/EventEmitter';
import { AckManager } from '../internal/AckManager';
import { MessageQueue } from '../internal/MessageQueue';
import { SSETransport } from '../transport/sse/SSETransport';
import { WebSocketTransport } from '../transport/websocket/WebSocketTransportV0';
import { SubscriptionManager } from '../subscription/SubscriptionManager';
import { EventStream } from '../stream/EventStream';
import { Messages } from '../protocol/messages';
import type { EventClientConfig, EventHandler } from './types';

export class EventClient {
  private emitter = new InternalEmitter();
  private subs = new SubscriptionManager();
  private ack = new AckManager();
  private queue = new MessageQueue();
  private transport: any;
  private topics = new Set<string>();

  constructor(private config: EventClientConfig) {
    this.transport = config.transport === 'websocket'
      ? new WebSocketTransport(config.url, this.handleMessage.bind(this))
      : new SSETransport(config.url, this.handleMessage.bind(this));
  }

  connect() {
    this.transport.connect();
    this.authenticate();
  }

  private async authenticate() {
    if (!this.config.getToken) return;
    const token = await this.config.getToken();
    this.send({ type: Messages.AUTH, payload: { token } });
  }

  subscribe(topic: string, handler: EventHandler) {
    const sub = this.subs.subscribe(topic, handler);

    if (!this.topics.has(topic)) {
      this.topics.add(topic);
      this.send({ type: Messages.SUBSCRIBE, payload: { topic } });
    }

    return {
      unsubscribe: () => {
        sub.unsubscribe();
        if (!this.subs.hasTopic(topic)) {
          this.topics.delete(topic);
          this.send({ type: Messages.UNSUBSCRIBE, payload: { topic } });
        }
      }
    };
  }

  stream(topic: string) {
    return new EventStream(this.subscribe.bind(this), topic, this.config.bufferSize ?? 100);
  }

  private send(msg: any) {
    const id = crypto.randomUUID();
    msg.id = id;

    return new Promise((resolve) => {
      this.ack.register(id, resolve);

      if (this.transport.send) this.transport.send(msg);
      else this.queue.enqueue(msg);
    });
  }

  private handleMessage(data: string) {
    const msg = JSON.parse(data);

    switch (msg.type) {
      case Messages.ACK:
        this.ack.resolve(msg.id);
        break;
      case Messages.EVENT:
        this.subs.dispatch(msg.payload);
        break;
    }
  }
}

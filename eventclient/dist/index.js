"use strict";
var __defProp = Object.defineProperty;
var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
var __getOwnPropNames = Object.getOwnPropertyNames;
var __hasOwnProp = Object.prototype.hasOwnProperty;
var __export = (target, all) => {
  for (var name in all)
    __defProp(target, name, { get: all[name], enumerable: true });
};
var __copyProps = (to, from, except, desc) => {
  if (from && typeof from === "object" || typeof from === "function") {
    for (let key of __getOwnPropNames(from))
      if (!__hasOwnProp.call(to, key) && key !== except)
        __defProp(to, key, { get: () => from[key], enumerable: !(desc = __getOwnPropDesc(from, key)) || desc.enumerable });
  }
  return to;
};
var __toCommonJS = (mod) => __copyProps(__defProp({}, "__esModule", { value: true }), mod);

// src/index.ts
var index_exports = {};
__export(index_exports, {
  EventClient: () => EventClient
});
module.exports = __toCommonJS(index_exports);

// src/internal/EventEmitter.ts
var InternalEmitter = class {
  constructor() {
    this.listeners = /* @__PURE__ */ new Map();
  }
  on(event, handler) {
    if (!this.listeners.has(event)) {
      this.listeners.set(event, /* @__PURE__ */ new Set());
    }
    this.listeners.get(event).add(handler);
  }
  emit(event, payload) {
    this.listeners.get(event)?.forEach((h) => h(payload));
  }
};

// src/internal/AckManager.ts
var AckManager = class {
  constructor() {
    this.resolvers = /* @__PURE__ */ new Map();
  }
  register(id, resolve) {
    this.resolvers.set(id, resolve);
  }
  resolve(id) {
    this.resolvers.get(id)?.();
    this.resolvers.delete(id);
  }
};

// src/internal/MessageQueue.ts
var MessageQueue = class {
  constructor() {
    this.queue = [];
  }
  enqueue(msg) {
    this.queue.push(msg);
  }
  flush(send) {
    this.queue.forEach(send);
    this.queue = [];
  }
};

// src/transport/sse/SSETransport.ts
var SSETransport = class {
  constructor(url, topic, onMessage) {
    this.url = url;
    this.topic = topic;
    this.onMessage = onMessage;
  }
  connect() {
    console.log("\u{1F310} [SSE] Connecting to:", this.url);
    const urlWithTopic = `${this.url}?topic=${this.topic}`;
    this.eventSource = new EventSource(urlWithTopic);
    this.eventSource.onopen = () => {
      console.log("\u{1F7E2} [SSE] Connection OPEN");
      this.openHandler?.();
    };
    this.eventSource.onmessage = (e) => {
      console.log("\u{1F4E5} [SSE] Message RECEIVED:", e.data);
      this.onMessage(e.data);
    };
    this.eventSource.onerror = (err) => {
      console.error("\u274C [SSE] ERROR:", err);
      this.errorHandler?.(err);
    };
  }
  close() {
    console.log("\u{1F50C} [SSE] Closing connection");
    this.eventSource?.close();
  }
  // ------------------------------
  // Event hooks (como WS)
  // ------------------------------
  onOpen(handler) {
    console.log("\u{1F9E9} [SSE] onOpen handler registered");
    this.openHandler = handler;
  }
  onError(handler) {
    console.log("\u{1F9E9} [SSE] onError handler registered");
    this.errorHandler = handler;
  }
};

// src/transport/websocket/WebSocketTransport.ts
var WebSocketTransport = class {
  constructor(url, onMessage) {
    this.url = url;
    this.onMessage = onMessage;
    console.log("\u{1F50C} [WS] Transport initialized with URL:", url);
  }
  connect() {
    console.log("\u{1F6A8}\u{1F6A8}\u{1F6A8} NUEVO CONNECT EJECUTADO \u{1F6A8}\u{1F6A8}\u{1F6A8}", this.url);
    this.socket = new WebSocket(this.url);
    this.socket.onopen = () => {
      console.log("\u{1F7E2} [WS] Connection OPEN");
      this.openHandler?.();
    };
    this.socket.onmessage = (e) => {
      console.log("\u{1F4E5} [WS] Message RECEIVED:", e.data);
      this.onMessage(e.data);
    };
    this.socket.onclose = (event) => {
      console.log("\u{1F534} [WS] Connection CLOSED", {
        code: event.code,
        reason: event.reason,
        wasClean: event.wasClean
      });
      this.closeHandler?.();
    };
    this.socket.onerror = (err) => {
      console.error("\u274C [WS] ERROR:", err);
      this.errorHandler?.(err);
    };
  }
  send(msg) {
    const payload = JSON.stringify(msg);
    console.log("\u{1F4E4} [WS] Sending:", payload);
    if (!this.socket) {
      console.warn("\u26A0\uFE0F [WS] Cannot send, socket not initialized");
      return;
    }
    if (this.socket.readyState !== WebSocket.OPEN) {
      console.warn("\u26A0\uFE0F [WS] Cannot send, socket not OPEN. State:", this.socket.readyState);
      return;
    }
    this.socket.send(payload);
  }
  close() {
    console.log("\u{1F50C} [WS] Closing connection");
    this.socket?.close();
  }
  // ------------------------------
  // Event hooks (usados por EventClient)
  // ------------------------------
  onOpen(handler) {
    console.log("\u{1F9E9} [WS] onOpen handler registered");
    this.openHandler = handler;
  }
  onClose(handler) {
    console.log("\u{1F9E9} [WS] onClose handler registered");
    this.closeHandler = handler;
  }
  onError(handler) {
    console.log("\u{1F9E9} [WS] onError handler registered");
    this.errorHandler = handler;
  }
};

// src/subscription/SubscriptionManager.ts
var SubscriptionManager = class {
  constructor() {
    this.handlers = /* @__PURE__ */ new Map();
  }
  subscribe(topic, handler) {
    if (!this.handlers.has(topic)) {
      this.handlers.set(topic, /* @__PURE__ */ new Set());
    }
    this.handlers.get(topic).add(handler);
    return {
      unsubscribe: () => this.handlers.get(topic)?.delete(handler)
    };
  }
  hasTopic(topic) {
    return (this.handlers.get(topic)?.size ?? 0) > 0;
  }
  dispatch(event) {
    this.handlers.get(event.topic)?.forEach((h) => h(event));
  }
};

// src/stream/EventStream.ts
var EventStream = class {
  constructor(subscribeFn, topic, bufferSize) {
    this.subscribeFn = subscribeFn;
    this.topic = topic;
    this.bufferSize = bufferSize;
    this.queue = [];
    this.resolvers = [];
  }
  async *[Symbol.asyncIterator]() {
    const sub = this.subscribeFn(this.topic, (event) => {
      if (this.queue.length >= this.bufferSize) this.queue.shift();
      this.queue.push(event);
      this.resolvers.shift()?.();
    });
    try {
      while (true) {
        if (!this.queue.length) {
          await new Promise((r) => this.resolvers.push(r));
        }
        yield this.queue.shift();
      }
    } finally {
      sub.unsubscribe();
    }
  }
};

// src/protocol/messages.ts
var Messages = {
  AUTH: "AUTH",
  AUTH_OK: "AUTH_OK",
  SUBSCRIBE: "SUBSCRIBE",
  UNSUBSCRIBE: "UNSUBSCRIBE",
  EVENT: "EVENT",
  ACK: "ACK",
  PING: "PING",
  PONG: "PONG"
};

// src/core/EventClient.ts
var EventClient = class {
  constructor(config) {
    this.config = config;
    this.emitter = new InternalEmitter();
    this.subs = new SubscriptionManager();
    this.ack = new AckManager();
    this.queue = new MessageQueue();
    this.topics = /* @__PURE__ */ new Set();
    this.transport = config.transport === "websocket" ? new WebSocketTransport(config.url, this.handleMessage.bind(this)) : new SSETransport(config.url, config.topic, this.handleMessage.bind(this));
    console.log("\u{1F9EA} Transport seleccionado:", config.transport);
    console.log("\u{1F9EA} Transport instancia:", this.transport.constructor.name);
  }
  // ------------------------------
  // Public API
  // ------------------------------
  connect() {
    console.log("\u{1F680} EventClient.connect() llamado");
    this.transport.onOpen?.(() => {
      console.log("\u{1F50C} Transport connected");
      this.emitter.emit("connected");
      this.queue.flush((msg) => this.transport.send(msg));
    });
    this.transport.onClose?.(() => {
      console.log("\u{1F50C} Transport disconnected");
      this.emitter.emit("disconnected");
    });
    this.transport.onError?.((err) => {
      console.error("Transport error:", err);
      this.emitter.emit("error", err);
    });
    this.transport.connect();
    this.authenticate();
  }
  disconnect() {
    this.transport.close?.();
    this.emitter.emit("disconnected");
  }
  on(event, handler) {
    this.emitter.on(event, handler);
  }
  subscribe(topic, handler) {
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
  stream(topic) {
    return new EventStream(
      this.subscribe.bind(this),
      topic,
      this.config.bufferSize ?? 100
    );
  }
  // ------------------------------
  // Internal
  // ------------------------------
  async authenticate() {
    if (!this.config.getToken) return;
    const token = await this.config.getToken();
    if (!token) return;
    this.send({
      type: Messages.AUTH,
      payload: { token }
    });
  }
  send(msg) {
    const id = crypto.randomUUID();
    msg.id = id;
    return new Promise((resolve) => {
      this.ack.register(id, resolve);
      if (this.transport.send) {
        this.transport.send(msg);
      } else {
        this.queue.enqueue(msg);
      }
    });
  }
  handleMessage(data) {
    const msg = JSON.parse(data);
    switch (msg.type) {
      case Messages.AUTH_OK:
        console.log("\u2705 AUTH_OK recibido");
        this.emitter.emit("connected");
        break;
      case Messages.ACK:
        this.ack.resolve(msg.id);
        break;
      case Messages.EVENT:
        this.subs.dispatch(msg.payload);
        break;
      default:
        console.warn("\u26A0\uFE0F Unknown message:", msg);
    }
  }
};
// Annotate the CommonJS export names for ESM import in node:
0 && (module.exports = {
  EventClient
});
//# sourceMappingURL=index.js.map
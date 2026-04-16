import { MockWebSocketServer } from './MockWebSocketServer.js';

const server = new MockWebSocketServer();

export class MockWebSocket {
  static CONNECTING = 0;
  static OPEN = 1;
  static CLOSING = 2;
  static CLOSED = 3;

  constructor(url) {
    this.url = url;
    this.readyState = MockWebSocket.CONNECTING;

    // Simula conexión asincrónica REAL
    setTimeout(() => {
      this.readyState = MockWebSocket.OPEN;
      console.log("🟢 MOCK WS CONNECTED");
      this.onopen?.();
    }, 50);

    server.connect(this);
  }

  send(data) {
    console.log("➡️ CLIENT -> SERVER:", data);
    server.receive(this, data);
  }

  close() {
    this.readyState = MockWebSocket.CLOSED;
    server.disconnect(this);
    this.onclose?.();
  }

  _send(msg) {
    console.log("📥 CLIENT RECEIVES:", msg);

    this.onmessage?.({
      data: JSON.stringify(msg)
    });
  }
}
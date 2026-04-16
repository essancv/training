export class MockWebSocketServer {
  constructor() {
    this.clients = new Set();
    this.topics = new Map();
  }

  connect(client) {
    this.clients.add(client);
  }

  disconnect(client) {
    this.clients.delete(client);
  }

  receive(client, raw) {
    console.log("⬅️ SERVER RECEIVED:", raw);

    let msg;
    try {
      msg = JSON.parse(raw);
    } catch (e) {
      console.error("❌ Invalid JSON:", raw);
      return;
    }

    switch (msg.type) {
      case 'AUTH':
        client._send({
          type: 'AUTH_OK'
        });
        break;

      case 'SUBSCRIBE':
        this.subscribe(client, msg.topic, msg.id);
        break;

      case 'UNSUBSCRIBE':
        this.unsubscribe(client, msg.topic);
        break;

      case 'PING':
        client._send({
          type: 'PONG',
          timestamp: Date.now()
        });
        break;

      default:
        console.warn("⚠️ Unknown message type:", msg);
    }
  }

  subscribe(client, topic, id) {
    if (!this.topics.has(topic)) {
      this.topics.set(topic, new Set());
    }

    const clients = this.topics.get(topic);
    clients.add(client);

    // ACK
    client._send({
      type: 'ACK',
      id
    });

    this.startEmitting(topic);
  }

  unsubscribe(client, topic) {
    this.topics.get(topic)?.delete(client);
  }

  startEmitting(topic) {
    const clients = this.topics.get(topic);

    if (clients._interval) return;

    const interval = setInterval(() => {
      if (!clients || clients.size === 0) {
        clearInterval(interval);
        return;
      }

      const event = {
        type: 'EVENT',
        payload: {
          topic,
          eventType: 'MOCK_EVENT',
          data: {
            value: Math.random()
          },
          timestamp: Date.now()
        }
      };

      console.log("📤 SERVER -> CLIENT:", event);

      clients.forEach(client => client._send(event));
    }, 2000);

    clients._interval = interval;
  }
}
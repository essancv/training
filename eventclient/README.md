# Event Client SDK

Framework-agnostic **event-driven client SDK** compatible con:

* React
* Angular
* JavaScript (vanilla / async)
* Cualquier entorno web moderno

Soporta múltiples transportes:

* **SSE (Server-Sent Events)**
* **WebSocket (con protocolo completo: AUTH, SUBSCRIBE, ACK, HEARTBEAT)**

---

# 🚀 Características

* 🔌 API simple y consistente
* 🔁 Reconexión automática
* 📡 Suscripción dinámica a topics
* 🔐 Autenticación integrada
* 💓 Heartbeat (PING/PONG)
* ✅ Confirmación de mensajes (ACK)
* 🔄 Transporte intercambiable (SSE / WebSocket)
* 🧠 Async iterators (`for await`)
* 📦 Independiente del framework

---

# 📁 Estructura del proyecto

```text
event-client-sdk/
├── src/
│   ├── core/              # API pública (EventClient, tipos)
│   ├── transport/         # SSE y WebSocket
│   ├── protocol/          # Definición de mensajes WS
│   ├── subscription/      # Gestión de topics
│   ├── stream/            # Async iterators
│   ├── internal/          # Infraestructura interna (ACK, cola, emitter)
│   └── index.ts           # Entry point
│
├── dist/                  # Build generado
├── tests/                 # Tests
├── examples/              # Ejemplos (React, Angular, JS)
│
├── package.json
├── tsconfig.json
└── tsup.config.ts
```

---

# ⚙️ Instalación

```bash
npm install event-client-sdk
```

---

# 🏗️ Build

Desde la raíz del proyecto:

```bash
npm install
npm run build
```

Salida:

```text
dist/
  index.js      (ESM)
  index.cjs     (CommonJS)
  index.d.ts    (Types)
```

---

# 🧠 Uso básico

```ts
import { EventClient } from 'event-client-sdk';

const client = new EventClient({
  url: 'http://localhost:8080/events',
  transport: 'websocket'
});

client.connect();

client.subscribe('orders', (event) => {
  console.log(event);
});
```

---

# ⚛️ Uso en React

```tsx
import { useEffect } from 'react';
import { EventClient } from 'event-client-sdk';

const client = new EventClient({
  url: '/events',
  transport: 'websocket'
});

export function OrdersComponent() {
  useEffect(() => {
    client.connect();

    const sub = client.subscribe('orders', (event) => {
      console.log(event);
    });

    return () => sub.unsubscribe();
  }, []);

  return <div>Listening orders...</div>;
}
```

---

# 🅰️ Uso en Angular

```ts
import { Injectable } from '@angular/core';
import { EventClient } from 'event-client-sdk';

@Injectable({ providedIn: 'root' })
export class EventService {
  private client = new EventClient({
    url: '/events',
    transport: 'websocket'
  });

  constructor() {
    this.client.connect();
  }

  subscribe(topic: string, handler: any) {
    return this.client.subscribe(topic, handler);
  }
}
```

---

# 🌐 Uso en JavaScript (async)

```js
const client = new EventClient({
  url: '/events',
  transport: 'websocket'
});

client.connect();

(async () => {
  for await (const event of client.stream('orders')) {
    console.log(event);
  }
})();
```

---

# 📡 API

## Constructor

```ts
new EventClient(config)
```

### Configuración

```ts
{
  url: string;
  transport?: 'sse' | 'websocket';
  getToken?: () => Promise<string>;
  heartbeat?: {
    interval?: number;
    timeout?: number;
  };
  bufferSize?: number;
}
```

---

## Métodos

### connect()

Inicia la conexión

---

### disconnect()

Cierra la conexión

---

### subscribe(topic, handler)

Suscribe a un topic

```ts
const sub = client.subscribe('orders', handler);
sub.unsubscribe();
```

---

### once(topic)

Recibe un único evento

```ts
const event = await client.once('orders');
```

---

### stream(topic)

Async iterator

```ts
for await (const event of client.stream('orders')) {
  console.log(event);
}
```

---

### on(event, handler)

Eventos internos:

* `connected`
* `disconnected`
* `error`

---

# 🔌 Transporte

## SSE

* Simple
* Unidireccional
* Reconexión automática del navegador

---

## WebSocket

* Bidireccional
* Menor latencia
* Protocolo completo

---

# 🔐 Protocolo WebSocket

## Envelope

```json
{
  "type": "STRING",
  "id": "uuid",
  "timestamp": 1710000000000,
  "payload": {}
}
```

---

## Cliente → Servidor

### AUTH

```json
{
  "type": "AUTH",
  "payload": { "token": "jwt" }
}
```

---

### SUBSCRIBE

```json
{
  "type": "SUBSCRIBE",
  "payload": { "topic": "orders" }
}
```

---

### UNSUBSCRIBE

```json
{
  "type": "UNSUBSCRIBE",
  "payload": { "topic": "orders" }
}
```

---

### PING

```json
{
  "type": "PING"
}
```

---

## Servidor → Cliente

### EVENT

```json
{
  "type": "EVENT",
  "payload": {
    "topic": "orders",
    "eventType": "CREATED",
    "data": {},
    "timestamp": 1710000000000
  }
}
```

---

### ACK

```json
{
  "type": "ACK",
  "id": "message-id"
}
```

---

### PONG

```json
{
  "type": "PONG"
}
```

---

### ERROR

```json
{
  "type": "ERROR",
  "payload": {
    "code": "ERROR_CODE",
    "message": "description"
  }
}
```

---

# 🔄 Flujo de conexión

1. connect()
2. AUTH
3. SUBSCRIBE topics
4. Recepción de EVENT
5. HEARTBEAT (PING/PONG)
6. Reconexión automática

---

# 🧠 Buenas prácticas

* Usar `unsubscribe()` en cleanup (React / Angular)
* No abrir múltiples conexiones innecesarias
* Centralizar cliente en servicios
* Manejar errores (`on('error')`)

---

# ⚠️ Limitaciones

* Requiere backend compatible con protocolo
* WebSocket necesita gestión de estado en servidor
* SSE no soporta suscripción dinámica real

---

# 🚀 Roadmap

* Retry automático con backoff
* Timeout de ACK
* Filtros por topic
* Batch events
* Métricas y tracing

---

# 📄 Licencia

MIT

---

# 🤝 Contribuciones

Pull requests bienvenidos 🚀

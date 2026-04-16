/*
import { MockWebSocket } from './mock/MockWebSocket';

// 👇 sustituye WebSocket global (SÓLO PRUEBASG)
window.WebSocket = MockWebSocket;
import { MockEventSource } from './mock/MockEventSource.js';

// activar mock SSE
window.EventSource = MockEventSource;
*/
import { EventClient } from 'event-client-sdk';
console.log("🧪 EventClient source:", EventClient.toString());

// Crear cliente
const protocol = location.protocol === 'https:' ? 'wss:' : 'ws:';

const client = new EventClient({
  /*
  url: '`${protocol}://localhost:8080/events', // ⚠️ cambia si tu backend está en otro sitio
  */
 /*
  url: `${protocol}//glowing-space-engine-7x49gjrp95gcp57g-8080.app.github.dev/events`, // ⚠️ cambia si tu backend está en otro sitio
  transport: 'websocket',  // sse o websocket
*/
  url: `https://glowing-space-engine-7x49gjrp95gcp57g-8080.app.github.dev/events/sse`, // ⚠️ cambia si tu backend está en otro sitio
  transport: 'sse',  // sse o websocket

  // opcional: autenticación
  getToken: async () => {
    // return "tu-jwt";
    return null;
  },

  heartbeat: {
    interval: 30000,
    timeout: 10000
  }
});

// Elementos DOM
const list = document.getElementById('list');
const status = document.getElementById('status');

console.log("Conectando al servidor de eventos...");

// Conectar
client.connect();

status.textContent = 'Conectando...';


client.onOpen?.(() => {
  console.log("✅ Conectado");
});

// Eventos internos
client.on('connected', () => {
  console.log('🟢 Conectado');

   status.textContent = '🟢 Conectado'; 

  // ✅ subscribe correcto
  const subscription = client.subscribe('demo-topic', (event) => {
    console.log('📩 Evento recibido:', event);
  });

  // ✅ stream correcto
  (async () => {
    for await (const event of client.stream('demo-topic')) {
      console.log('[STREAM]', event);
    }
  })();
});

client.on?.('disconnected', () => {
  console.log('Desconectado');
  status.textContent = '🔴 Desconectado';
});

client.on?.('error', (err) => {
  console.error('Error:', err);
  status.textContent = '⚠️ Error';
});

/*
// Suscripción clásica
const subscription = client.subscribe('orders', (event) => {
  console.log('Evento recibido:', event);

  const li = document.createElement('li');
  li.textContent = `[SUBSCRIBE] ${JSON.stringify(event)}`;
  list.appendChild(li);
});

// Async stream (alternativa moderna)
(async () => {
  try {
    for await (const event of client.stream('orders')) {
      const li = document.createElement('li');
      li.textContent = `[STREAM] ${JSON.stringify(event)}`;
      list.appendChild(li);
    }
  } catch (err) {
    console.error('Stream error:', err);
  }
})();
*/
// Limpieza al cerrar página
window.addEventListener('beforeunload', () => {
  subscription.unsubscribe();
  client.disconnect();
});
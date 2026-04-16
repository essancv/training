import { EventClient } from 'event-client-sdk';

// Crear cliente
const client = new EventClient({
  url: 'ws://localhost:8080/events', // ⚠️ cambia si tu backend está en otro sitio
  transport: 'websocket',

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

// Conectar
client.connect();

status.textContent = 'Conectando...';

// Eventos internos
client.on?.('connected', () => {
  console.log('Conectado');
  status.textContent = '🟢 Conectado';
});

client.on?.('disconnected', () => {
  console.log('Desconectado');
  status.textContent = '🔴 Desconectado';
});

client.on?.('error', (err) => {
  console.error('Error:', err);
  status.textContent = '⚠️ Error';
});

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

// Limpieza al cerrar página
window.addEventListener('beforeunload', () => {
  subscription.unsubscribe();
  client.disconnect();
});
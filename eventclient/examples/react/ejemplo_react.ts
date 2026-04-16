import { useEffect, useState } from 'react';
import { EventClient } from 'event-client-sdk';

const client = new EventClient({
  url: 'ws://localhost:8080/events',
  transport: 'websocket'
});

export default function App() {
  const [events, setEvents] = useState<any[]>([]);

  useEffect(() => {
    client.connect();

    const sub = client.subscribe('orders', (event) => {
      setEvents((prev) => [...prev, event]);
    });

    return () => {
      sub.unsubscribe();
      client.disconnect();
    };
  }, []);

  return (
    <div>
      <h1>Eventos Orders</h1>
      <ul>
        {events.map((e, i) => (
          <li key={i}>{JSON.stringify(e)}</li>
        ))}
      </ul>
    </div>
  );
}
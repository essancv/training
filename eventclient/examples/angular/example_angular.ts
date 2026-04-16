import { Injectable } from '@angular/core';
import { EventClient } from 'event-client-sdk';

@Injectable({ providedIn: 'root' })
export class EventService {
  private client = new EventClient({
    url: 'ws://localhost:8080/events',
    transport: 'websocket'
  });

  constructor() {
    this.client.connect();
  }

  subscribe(topic: string, handler: any) {
    return this.client.subscribe(topic, handler);
  }
}
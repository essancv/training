// ------------------------------
// src/core/types.ts
// ------------------------------
export type ServerEvent<T = any> = {
  topic: string;
  type: string;
  data: T;
  id?: string;
  timestamp: number;
};

export type EventHandler<T = any> = (event: ServerEvent<T>) => void | Promise<void>;

export interface Subscription {
  unsubscribe(): void;
}

export type TransportType = 'sse' | 'websocket';

export interface EventClientConfig {
  url: string;
  topic: string; // Agregado para SSE
  transport?: TransportType;
  getToken?: () => Promise<string>;
  heartbeat?: {
    interval?: number;
    timeout?: number;
  };
  bufferSize?: number;
}



// ------------------------------
// src/transport/Transport.ts
// ------------------------------
export interface Transport {
  connect(): void;
  disconnect(): void;
  send?(msg: any): void;
}

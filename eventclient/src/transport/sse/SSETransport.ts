// ------------------------------
// src/transport/sse/SSETransport.ts
// ------------------------------

export class SSETransport {
  private eventSource?: EventSource;

  private openHandler?: () => void;
  private errorHandler?: (err: any) => void;

  constructor(
    private url: string,
    private topic: string,
    private onMessage: (data: string) => void
  ) {}

  connect() {
    console.log('🌐 [SSE] Connecting to:', this.url);
    const urlWithTopic = `${this.url}?topic=${this.topic}`;
    this.eventSource = new EventSource(urlWithTopic);

    this.eventSource.onopen = () => {
      console.log('🟢 [SSE] Connection OPEN');
      this.openHandler?.();
    };

    this.eventSource.onmessage = (e) => {
      console.log('📥 [SSE] Message RECEIVED:', e.data);
      this.onMessage(e.data);
    };

    this.eventSource.onerror = (err) => {
      console.error('❌ [SSE] ERROR:', err);
      this.errorHandler?.(err);
    };
  }

  close() {
    console.log('🔌 [SSE] Closing connection');
    this.eventSource?.close();
  }

  // ------------------------------
  // Event hooks (como WS)
  // ------------------------------

  onOpen(handler: () => void) {
    console.log('🧩 [SSE] onOpen handler registered');
    this.openHandler = handler;
  }

  onError(handler: (err: any) => void) {
    console.log('🧩 [SSE] onError handler registered');
    this.errorHandler = handler;
  }
}
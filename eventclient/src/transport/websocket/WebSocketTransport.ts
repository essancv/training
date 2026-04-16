// ------------------------------
// src/transport/websocket/WebSocketTransport.ts
// ------------------------------
export class WebSocketTransport {
  private socket?: WebSocket;

  private openHandler?: () => void;
  private closeHandler?: () => void;
  private errorHandler?: (err: any) => void;

  constructor(
    private url: string,
    private onMessage: (data: string) => void
  ) { console.log('🔌 [WS] Transport initialized with URL:', url);}

  connect() {
    console.log('🚨🚨🚨 NUEVO CONNECT EJECUTADO 🚨🚨🚨', this.url); 

    this.socket = new WebSocket(this.url);

    this.socket.onopen = () => {
      console.log('🟢 [WS] Connection OPEN');
      this.openHandler?.();
    };

    this.socket.onmessage = (e) => {
      console.log('📥 [WS] Message RECEIVED:', e.data);
      this.onMessage(e.data);
    };

    this.socket.onclose = (event) => {
      console.log('🔴 [WS] Connection CLOSED', {
        code: event.code,
        reason: event.reason,
        wasClean: event.wasClean
      });

      this.closeHandler?.();
    };

    this.socket.onerror = (err) => {
      console.error('❌ [WS] ERROR:', err);
      this.errorHandler?.(err);
    };
  }

  send(msg: any) {
    const payload = JSON.stringify(msg);

    console.log('📤 [WS] Sending:', payload);

    if (!this.socket) {
      console.warn('⚠️ [WS] Cannot send, socket not initialized');
      return;
    }

    if (this.socket.readyState !== WebSocket.OPEN) {
      console.warn('⚠️ [WS] Cannot send, socket not OPEN. State:', this.socket.readyState);
      return;
    }

    this.socket.send(payload);
  }

  close() {
    console.log('🔌 [WS] Closing connection');

    this.socket?.close();
  }

  // ------------------------------
  // Event hooks (usados por EventClient)
  // ------------------------------

  onOpen(handler: () => void) {
    console.log('🧩 [WS] onOpen handler registered');
    this.openHandler = handler;
  }

  onClose(handler: () => void) {
    console.log('🧩 [WS] onClose handler registered');
    this.closeHandler = handler;
  }

  onError(handler: (err: any) => void) {
    console.log('🧩 [WS] onError handler registered');
    this.errorHandler = handler;
  }
}
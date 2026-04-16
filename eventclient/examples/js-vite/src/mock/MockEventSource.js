export class MockEventSource {
  constructor(url) {
    this.url = url;

    console.log('🌐 [SSE MOCK] Connecting to:', url);

    // Simula apertura
    setTimeout(() => {
      console.log('🟢 [SSE MOCK] OPEN');
      this.onopen?.();
      this.startEmitting();
    }, 50);
  }

  startEmitting() {
    this.interval = setInterval(() => {
      const event = {
        type: 'EVENT',
        payload: {
          topic: 'orders',
          data: { value: Math.random() },
          timestamp: Date.now()
        }
      };

      const data = JSON.stringify(event);

      console.log('📤 [SSE MOCK] Sending:', data);

      this.onmessage?.({
        data
      });
    }, 2000);
  }

  close() {
    console.log('🔴 [SSE MOCK] CLOSED');
    clearInterval(this.interval);
    this.onclose?.();
  }
}
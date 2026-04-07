
import time
import requests
from sseclient import SSEClient

SSE_URL = "https://glowing-space-engine-7x49gjrp95gcp57g-8080.app.github.dev/streamV2/kafka?groupId=cliente2&topics=user-deleted"

def connect_sse():
    """Conecta al servidor SSE y devuelve un cliente SSE."""
    headers = {
       "Accept": "text/event-stream",
        "Cache-Control": "no-cache",
        "Connection": "keep-alive" }    

    response = requests.get(SSE_URL, stream=True, headers=headers, timeout=30)
    response.raise_for_status()  # si hay error HTTP, lanza excepción

    return SSEClient(response)

def main():
    print("Conectando al servidor SSE...")

    while True:
        try:
            client = connect_sse()
            print("Conectado. Escuchando eventos...")

            for event in client.events():
                if event.data:
                    print("Mensaje recibido:", event.data)

        except Exception as e:
            print("Error en la conexión SSE:", e)
            print("Reintentando en 3 segundos...")
            time.sleep(3)

if __name__ == "__main__":
    main()

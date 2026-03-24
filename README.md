# training

# Compilar y arrancar spring-boot

mvn clean spring-boot:run

# Ver los topic en red panda
docker exec -it f60170cc8277 rpk --brokers localhost:9092 topic list

# arrancar docker con redpanda

docker run -d --name redpanda \
  -p 9092:9092 \
  -p 9644:9644 \
  docker.redpanda.com/redpandadata/redpanda:latest \
  redpanda start \
    --overprovisioned \
    --smp 1 \
    --memory 1G \
    --kafka-addr PLAINTEXT://0.0.0.0:9092 \
    --advertise-kafka-addr PLAINTEXT://localhost:9092 \
    --rpc-addr 0.0.0.0:33145 \
    --advertise-rpc-addr localhost:33145

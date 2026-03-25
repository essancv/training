# training

# Compilar y arrancar spring-boot

mvn clean spring-boot:run

# Ver los topic en red panda
docker exec -it redpanda rpk --brokers localhost:9092 topic list
docker exec -it redpanda /bin/bash
rpk topic consume <topic>
rpk topic describe <topic>
rpk group list
rpk group describe sse-proxy

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

docker run -d --name redpanda   -p 9092:9092   docker.redpanda.com/redpandadata/redpanda:latest   redpanda start     --overprovisioned     --smp 1     --memory 1G     --reserve-memory 0M     --node-id 0     --check=false     --kafka-addr PLAINTEXT://0.0.0.0:9092     --advertise-kafka-addr  PLAINTEXT://172.17.0.1:9092

  # comandos docker

  docker pull <image> # descarga imagen
  docker images # ver las imágenes descargadas
  docker run -d --name <name> <image>  # arrancar la imagen
  docker run -d --name <name> <image>  # arrancar la imagen con ports binding
  docker run -d -p 6000:6379 redis:8.4.2
  
  docker stop <name> (or <docker id>)
  docker start <container id> # arrancar una ejecución previa 

  docker logs <container id>  # ver los log
  docker exec -it <docker id> <command>
  docker network ls 
  docker network create <network name> # red interna para los contenedores que me interesa
  docker run --net <network name>  # cuando hay varios contenedores que se referencian



# Verificar conexión con kafka
  nc -vz 172.17.0.1 9092
# Verificar mensajería con PS
  curl.exe -v --http1.1 https://glowing-space-engine-7x49gjrp95gcp57g-8080.app.github.dev/stream/kafka

1. Crear la red de mongo :
   docker network create mongo-net

2. Arrancar docker de mongo
   docker run -d \
  --name mongo \
  --network mongo-net \
  -p 27017:27017 \
  mongo

3. arrancar mongoe-express (admin/pass)

docker run -d \
  --name mongo-express \
  --network mongo-net \
  -p 8081:8081 \
  -e ME_CONFIG_MONGODB_SERVER=mongo \
  -e ME_CONFIG_MONGODB_PORT=27017 \
  -e ME_CONFIG_OPTIONS_EDITORTHEME=default \
  mongo-express

4. arrancar redpanda (sustituto de kafka)

docker run -d --name redpanda   -p 9092:9092   docker.redpanda.com/redpandadata/redpanda:latest   redpanda start     --overprovisioned     --smp 1     --memory 1G     --reserve-memory 0M     --node-id 0     --check=false     --kafka-addr PLAINTEXT://0.0.0.0:9092     --advertise-kafka-addr  PLAINTEXT://172.17.0.1:9092

5. Verificar que hay conexión con kafka (en codespaces)
 nc -vz 172.17.0.1 9092

6. Lanzar spring-boot (cd JPA/tutorial)

mvn clean spring-boot:run

7. Levantar cliente de kafka :

 curl.exe -v --http1.1 https://glowing-space-engine-7x49gjrp95gcp57g-8080.app.github.dev/stream/kafka

8. Crear un usuario

 $user=@{username='demo';nombre='Andres';apellidos='Carrera';password='demoaaa';email='a@a'} | ConvertTo-Json
  $result=Invoke-RestMethod -Uri "https://glowing-space-engine-7x49gjrp95gcp57g-8080.app.github.dev/api/users" -Method Post -Body $user4  -ContentType "application/json"
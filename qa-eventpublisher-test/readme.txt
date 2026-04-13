mvn clean spring-boot:run
Disparar escenarios:

POST http://localhost:8080/qa/events/ok

POST http://localhost:8080/qa/events/null

POST http://localhost:8080/qa/events/exception

POST http://localhost:8080/qa/events/custom/123

Observar:

rpk topic list

mensajes en qa-topic y qa-dead-letter

rpk topic consume qa-topic

logs en consola y logs/event-publisher.log
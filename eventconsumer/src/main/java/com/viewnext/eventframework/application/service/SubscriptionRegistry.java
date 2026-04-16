package com.viewnext.eventframework.application.service;

import com.viewnext.eventframework.application.port.out.ClientConnection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Componente de aplicación encargado de gestionar las suscripciones
 * entre clientes y topics.
 *
 * <p>
 * Mantiene en memoria la relación:
 * </p>
 *
 * <pre>
 *   topic → conexiones suscritas
 * </pre>
 *
 * <p>
 * Este componente permite:
 * <ul>
 *   <li>Registrar suscripciones dinámicas (cliente → topic)</li>
 *   <li>Eliminar suscripciones</li>
 *   <li>Obtener los clientes interesados en un topic</li>
 *   <li>Eliminar completamente una conexión (por desconexión)</li>
 * </ul>
 * </p>
 *
 * <p>
 * Características:
 * <ul>
 *   <li>Thread-safe (uso de ConcurrentHashMap)</li>
 *   <li>Optimizado para lectura frecuente (dispatch de eventos)</li>
 *   <li>Sin dependencia de infraestructura (hexagonal)</li>
 * </ul>
 * </p>
 *
 * <p>
 * Nota: En entornos distribuidos este componente podría evolucionar
 * hacia una implementación basada en Redis, Kafka o similar.
 * </p>
 */
public class SubscriptionRegistry {

    /**
     * Mapa de suscripciones:
     *
     * <pre>
     *   topic → conjunto de conexiones
     * </pre>
     */
    private final Map<String, Set<ClientConnection>> subscriptions = new ConcurrentHashMap<>();

    /**
     * Registra una suscripción de un cliente a un topic.
     *
     * <p>
     * Si el topic no existe, se crea automáticamente.
     * </p>
     *
     * @param topic topic al que se suscribe el cliente
     * @param connection conexión del cliente
     */
    public void subscribe(String topic, ClientConnection connection) {
        subscriptions
            .computeIfAbsent(topic, t -> ConcurrentHashMap.newKeySet())
            .add(connection);
    }

    /**
     * Elimina la suscripción de un cliente a un topic.
     *
     * <p>
     * Si tras eliminar la conexión no quedan más suscriptores,
     * el topic se elimina del mapa.
     * </p>
     *
     * @param topic topic
     * @param connection conexión del cliente
     */
    public void unsubscribe(String topic, ClientConnection connection) {
        Set<ClientConnection> conns = subscriptions.get(topic);

        if (conns != null) {
            conns.remove(connection);

            if (conns.isEmpty()) {
                subscriptions.remove(topic);
            }
        }
    }

    /**
     * Elimina completamente una conexión de todos los topics.
     *
     * <p>
     * Se utiliza cuando un cliente se desconecta o se produce un error.
     * </p>
     *
     * @param connection conexión a eliminar
     */
    public void removeConnection(ClientConnection connection) {
        subscriptions.values().forEach(set -> set.remove(connection));
    }

    /**
     * Devuelve los clientes suscritos a un topic.
     *
     * <p>
     * Si no hay suscriptores, devuelve un conjunto vacío.
     * </p>
     *
     * @param topic topic
     * @return conjunto de conexiones suscritas
     */
    public Set<ClientConnection> getSubscribers(String topic) {
        return subscriptions.getOrDefault(topic, Collections.emptySet());
    }
}
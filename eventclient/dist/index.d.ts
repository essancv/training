type ServerEvent<T = any> = {
    topic: string;
    type: string;
    data: T;
    id?: string;
    timestamp: number;
};
type EventHandler<T = any> = (event: ServerEvent<T>) => void | Promise<void>;
interface Subscription {
    unsubscribe(): void;
}
type TransportType = 'sse' | 'websocket';
interface EventClientConfig {
    url: string;
    topic: string;
    transport?: TransportType;
    getToken?: () => Promise<string>;
    heartbeat?: {
        interval?: number;
        timeout?: number;
    };
    bufferSize?: number;
}

declare class EventStream<T> implements AsyncIterable<ServerEvent<T>> {
    private subscribeFn;
    private topic;
    private bufferSize;
    private queue;
    private resolvers;
    constructor(subscribeFn: Function, topic: string, bufferSize: number);
    [Symbol.asyncIterator](): AsyncGenerator<ServerEvent<T>, void, unknown>;
}

declare class EventClient {
    private config;
    private emitter;
    private subs;
    private ack;
    private queue;
    private transport;
    private topics;
    constructor(config: EventClientConfig);
    connect(): void;
    disconnect(): void;
    on(event: string, handler: (...args: any[]) => void): void;
    subscribe(topic: string, handler: EventHandler): {
        unsubscribe: () => void;
    };
    stream(topic: string): EventStream<unknown>;
    private authenticate;
    private send;
    private handleMessage;
}

export { EventClient, type EventClientConfig, type EventHandler, type ServerEvent, type Subscription, type TransportType };

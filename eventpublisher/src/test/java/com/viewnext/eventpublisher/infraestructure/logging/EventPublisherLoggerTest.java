package com.viewnext.eventpublisher.infrastructure.logging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
class EventPublisherLoggerTest {

    @Test
    void testLoggerDoesNotThrow() {
        EventPublisherLogger logger = new EventPublisherLogger("test-logger");
        assertDoesNotThrow(() -> logger.info("info test"));
        assertDoesNotThrow(() -> logger.warn("warn test"));
        assertDoesNotThrow(() -> logger.error("error test"));
        assertDoesNotThrow(() -> logger.error("error with exception", new RuntimeException("x")));
    }

}

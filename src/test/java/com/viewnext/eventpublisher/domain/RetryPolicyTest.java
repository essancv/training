package com.viewnext.eventpublisher.domain;

import com.viewnext.eventpublisher.domain.model.RetryPolicy;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class RetryPolicyTest {

    @Test
    void testCanRetry() {
        RetryPolicy policy = new RetryPolicy(3, Duration.ofMillis(1000));

        assertTrue(policy.canRetry(1));
        assertTrue(policy.canRetry(2));
        assertFalse(policy.canRetry(3));
    }

    @Test
    void testBackoff() {
        RetryPolicy policy = new RetryPolicy(3, Duration.ofMillis(500));
        assertEquals(500, policy.getBackoff().toMillis());
    }
}

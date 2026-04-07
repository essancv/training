package com.viewnext.eventpublisher.infrastructure.aop;

import com.viewnext.eventpublisher.application.service.PublishEventUseCase;
import com.viewnext.eventpublisher.infrastructure.logging.EventPublisherLogger;
import com.viewnext.eventpublisher.infrastructure.aop.PublishEventAspect;
import com.viewnext.eventpublisher.infrastructure.aop.PublishEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.mockito.Mockito.*;

class PublishEventAspectTest {

    @Test
    void testAspectPublishesEvent() throws Throwable {
        PublishEventUseCase useCase = mock(PublishEventUseCase.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        PublishEventAspect aspect = new PublishEventAspect(useCase, logger);

        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        when(pjp.proceed()).thenReturn("OK");

        Signature signature = mock(Signature.class);
        when(signature.toShortString()).thenReturn("testMethod()");
        when(pjp.getSignature()).thenReturn(signature);

        PublishEvent annotation = mock(PublishEvent.class);
        when(annotation.topic()).thenReturn("topic-test");

        aspect.aroundPublishEvent(pjp, annotation);

        verify(useCase).publishAsync("topic-test", "OK");

        // 🔥 Log real del aspecto
        verify(logger).info(
                contains("finalizó correctamente"),
                eq("topic-test"),
                eq("testMethod()")
        );
    }

    @Test
    void testAspectHandlesExceptionFromProceed() throws Throwable {
        PublishEventUseCase useCase = mock(PublishEventUseCase.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        PublishEventAspect aspect = new PublishEventAspect(useCase, logger);

        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        when(pjp.proceed()).thenThrow(new RuntimeException("boom"));

        Signature signature = mock(Signature.class);
        when(signature.toShortString()).thenReturn("testMethod()");
        when(pjp.getSignature()).thenReturn(signature);

        PublishEvent annotation = mock(PublishEvent.class);
        when(annotation.topic()).thenReturn("topic-test");

        // El aspecto PROPAGA la excepción → el test debe esperarla
        assertThrows(RuntimeException.class, () -> aspect.aroundPublishEvent(pjp, annotation));

        // Verificamos el log REAL del aspecto
        verify(logger).error(
                contains("lanzó excepción"),
                eq("testMethod()"),
                any(RuntimeException.class)
        );

        // Y NO debe publicar nada
        verify(useCase, never()).publishAsync(any(), any());
    }

    @Test
    void testAspectLogsBeforeAndAfter() throws Throwable {
        PublishEventUseCase useCase = mock(PublishEventUseCase.class);
        EventPublisherLogger logger = mock(EventPublisherLogger.class);

        PublishEventAspect aspect = new PublishEventAspect(useCase, logger);

        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        when(pjp.proceed()).thenReturn("RESULT");

        Signature signature = mock(Signature.class);
        when(signature.toShortString()).thenReturn("myMethod()");
        when(pjp.getSignature()).thenReturn(signature);

        PublishEvent annotation = mock(PublishEvent.class);
        when(annotation.topic()).thenReturn("topic-xyz");

        aspect.aroundPublishEvent(pjp, annotation);

        // 🔥 Este es el log REAL del aspecto
        verify(logger).info(
                contains("finalizó correctamente"),
                eq("topic-xyz"),
                eq("myMethod()")
        );
    }
}

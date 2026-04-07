package com.qa.eventpublisher.web;

import com.qa.eventpublisher.service.QaTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para que QA pueda disparar los escenarios
 * definidos en el plan de pruebas / BDD.
 */
@RestController
@RequestMapping("/qa/events")
public class QaTestController {

    private final QaTestService qaTestService;

    public QaTestController(QaTestService qaTestService) {
        this.qaTestService = qaTestService;
    }

    /**
     * Dispara el escenario "OK" (evento publicado correctamente).
     */
    @PostMapping("/ok")
    public ResponseEntity<?> triggerOkEvent() {
        var result = qaTestService.okEvent();
        return ResponseEntity.ok(result);
    }

    /**
     * Dispara el escenario "null" (no se publica evento).
     */
    @PostMapping("/null")
    public ResponseEntity<?> triggerNullEvent() {
        var result = qaTestService.nullEvent();
        return ResponseEntity.ok(result);
    }

    /**
     * Dispara el escenario "exception" (método lanza excepción).
     */
    @PostMapping("/exception")
    public ResponseEntity<?> triggerExceptionEvent() {
        try {
            qaTestService.exceptionEvent();
            return ResponseEntity.ok("No debería llegar aquí");
        } catch (RuntimeException ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    /**
     * Dispara un evento custom con un id concreto.
     */
    @PostMapping("/custom/{id}")
    public ResponseEntity<?> triggerCustomEvent(@PathVariable String id) {
        var result = qaTestService.customEvent(id);
        return ResponseEntity.ok(result);
    }
}

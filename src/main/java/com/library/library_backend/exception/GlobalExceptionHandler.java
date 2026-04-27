// En vez de que cada error explote con un mensaje feo de Java, esta clase captura todos los errores del backend y los convierte en respuestas JSON limpias

package com.library.library_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

// @RestControllerAdvice le dice a Spring que esta clase
// intercepta todos los errores de todos los controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura RuntimeException — errores como "Book not found", "User not found"
    // Retorna código 404 NOT FOUND
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Captura IllegalArgumentException — errores de validación como "Email already exists"
    // Retorna código 400 BAD REQUEST
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // Captura cualquier otro error inesperado
    // Retorna código 500 INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + ex.getMessage());
    }

    // Construye la respuesta JSON con el formato estándar
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", OffsetDateTime.now());  // cuándo ocurrió el error
        body.put("status", status.value());            // código HTTP (404, 400, 500)
        body.put("error", status.getReasonPhrase());   // texto del código (Not Found, Bad Request)
        body.put("message", message);                  // mensaje descriptivo del error
        return ResponseEntity.status(status).body(body);
    }
}
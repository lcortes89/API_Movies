package org.luisa.globals;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.luisa.country.exceptions.CountryException;
import org.luisa.country.exceptions.CountryExceptionNotFound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobaExceptionHandler {

    // CountryNotFoundGloblaException
    @ExceptionHandler(CountryExceptionNotFound.class)
    public ResponseEntity<String> handleCountryNotFoundException(CountryExceptionNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    // CountryGlobalException
    @ExceptionHandler(CountryException.class)
    public ResponseEntity<String> handleCountryException(CountryException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    // ApiException (Genre, Year, Actor, Movie) - structured JSON error body
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException exception) {
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus status = responseStatus != null ? responseStatus.code() : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", exception.getMessage());

        return ResponseEntity.status(status).body(body);
    }

    // GenericExceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
    }

    // Uso de la dependencia Validation - Para un manejomás limpio y consistente con el resto del código, lo recomendable es capturarla en un `@RestControllerAdvice`
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}

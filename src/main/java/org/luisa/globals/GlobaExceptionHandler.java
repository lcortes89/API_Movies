package org.luisa.globals;

import java.util.HashMap;
import java.util.Map;

import org.luisa.country.exceptions.CountryException;
import org.luisa.country.exceptions.CountryExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    // GenericExceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
    }

    // Uso de la dependencia Validation - Para un manejo más limpio y consistente con el resto del código, lo recomendable es capturarla en un `@RestControllerAdvice`
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
                
        return ResponseEntity.badRequest().body(errors);
    }

}

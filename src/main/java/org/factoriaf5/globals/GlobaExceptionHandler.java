package org.factoriaf5.globals;

import org.factoriaf5.country.exceptions.CountryException;
import org.factoriaf5.country.exceptions.CountryExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}

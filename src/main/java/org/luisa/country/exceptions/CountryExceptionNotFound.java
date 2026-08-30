package org.luisa.country.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryExceptionNotFound extends CountryException {

    public CountryExceptionNotFound(String message) {
        super(message);
    }

    public CountryExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }
    
}

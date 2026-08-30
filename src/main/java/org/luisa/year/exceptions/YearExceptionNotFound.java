package org.luisa.year.exceptions;

import org.luisa.globals.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Year not found")
public class YearExceptionNotFound extends ApiException {

    public YearExceptionNotFound(String message) {
        super(message);
    }
}

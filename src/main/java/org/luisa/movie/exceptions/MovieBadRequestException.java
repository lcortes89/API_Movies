package org.luisa.movie.exceptions;

import org.luisa.globals.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid movie data")
public class MovieBadRequestException extends ApiException {

    public MovieBadRequestException(String message) {
        super(message);
    }
}

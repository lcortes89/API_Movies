package org.luisa.movie.exceptions;

import org.luisa.globals.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Movie not found")
public class MovieNotFoundException extends ApiException {

    public MovieNotFoundException(String message) {
        super(message);
    }
}

package org.luisa.genre.exceptions;

import org.luisa.globals.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Genre not found")
public class GenreExceptionNotFound extends ApiException {

    public GenreExceptionNotFound(String message) {
        super(message);
    }
}

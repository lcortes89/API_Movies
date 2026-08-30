package org.luisa.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Movie already exists")
public class MovieConflictException extends RuntimeException {

    public MovieConflictException(String message) {
        super(message);
    }
}

package org.luisa.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid movie data")
public class MovieBadRequestException extends RuntimeException {

    public MovieBadRequestException(String message) {
        super(message);
    }
}

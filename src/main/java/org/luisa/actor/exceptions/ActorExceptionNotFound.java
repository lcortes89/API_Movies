package org.luisa.actor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor not found")
public class ActorExceptionNotFound extends RuntimeException {

    public ActorExceptionNotFound(String message) {
        super(message);
    }
}

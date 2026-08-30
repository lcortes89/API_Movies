package org.luisa.actor.exceptions;

import org.luisa.globals.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor not found")
public class ActorExceptionNotFound extends ApiException {

    public ActorExceptionNotFound(String message) {
        super(message);
    }
}

package org.luisa.actor.dtos;

import jakarta.validation.constraints.NotBlank;

public record ActorDTORequest(
        @NotBlank(message = "Name cannot be blank")
        String name) {
}

package org.luisa.actor.mappers;

import org.luisa.actor.ActorEntity;
import org.luisa.actor.dtos.ActorDTORequest;
import org.luisa.actor.dtos.ActorDTOResponse;

public class ActorMapper {

    private ActorMapper() {
    }

    public static ActorDTOResponse toDTO(ActorEntity entity) {
        return new ActorDTOResponse(entity.getId(), entity.getName());
    }

    public static ActorEntity toEntity(ActorDTORequest dtoRequest) {
        ActorEntity actor = new ActorEntity();
        actor.setName(dtoRequest.name());
        return actor;
    }
}

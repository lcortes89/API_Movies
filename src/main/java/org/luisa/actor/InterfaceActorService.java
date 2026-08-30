package org.luisa.actor;

import java.util.List;

import org.luisa.actor.dtos.ActorDTOResponse;

public interface InterfaceActorService {

    List<ActorDTOResponse> getEntities();

    ActorDTOResponse getById(Long id);
}

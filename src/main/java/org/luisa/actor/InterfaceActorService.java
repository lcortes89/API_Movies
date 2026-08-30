package org.luisa.actor;

import java.util.List;

import org.luisa.actor.dtos.ActorDTORequest;
import org.luisa.actor.dtos.ActorDTOResponse;
import org.luisa.implementations.InterfaceGenericeEditService;

public interface InterfaceActorService extends InterfaceGenericeEditService<ActorDTORequest, ActorDTOResponse> {

    List<ActorDTOResponse> getEntities();

    ActorDTOResponse getById(Long id);

    ActorDTOResponse updateEntity(Long id, ActorDTORequest dto);
}

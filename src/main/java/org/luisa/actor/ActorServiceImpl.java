package org.luisa.actor;

import java.util.List;

import org.luisa.actor.dtos.ActorDTORequest;
import org.luisa.actor.dtos.ActorDTOResponse;
import org.luisa.actor.exceptions.ActorExceptionNotFound;
import org.luisa.actor.mappers.ActorMapper;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements InterfaceActorService {

    private final ActorRepository repository;

    public ActorServiceImpl(ActorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ActorDTOResponse> getEntities() {
        return repository.findAll().stream()
                .map(ActorMapper::toDTO)
                .toList();
    }

    @Override
    public ActorDTOResponse getById(Long id) {
        ActorEntity actor = repository.findById(id)
                .orElseThrow(() -> new ActorExceptionNotFound("Actor not found. Id " + id + " does not exist."));
        return ActorMapper.toDTO(actor);
    }

    public ActorDTOResponse storeEntity(ActorDTORequest dto) {
        ActorEntity actorToSave = ActorMapper.toEntity(dto);

        Example<ActorEntity> example = Example.of(actorToSave);
        boolean alreadyExists = !repository.findAll(example).isEmpty();

        if (alreadyExists)
            return null;

        ActorEntity actorSaved = repository.save(actorToSave);
        return ActorMapper.toDTO(actorSaved);
    }

    public ActorDTOResponse updateEntity(Long id, ActorDTORequest dto) {
        boolean actorExists = repository.existsById(id);
        if (!actorExists)
            throw new ActorExceptionNotFound("Actor not found. Id " + id + " does not exist.");

        boolean nameExists = repository.existsByNameAndIdNot(dto.name(), id);
        if (nameExists)
            return null;

        ActorEntity actorToUpdate = new ActorEntity(id, dto.name());
        ActorEntity actorUpdated = repository.save(actorToUpdate);
        return ActorMapper.toDTO(actorUpdated);
    }
}

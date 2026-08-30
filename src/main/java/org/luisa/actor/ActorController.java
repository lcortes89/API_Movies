package org.luisa.actor;

import java.util.List;

import org.luisa.actor.dtos.ActorDTORequest;
import org.luisa.actor.dtos.ActorDTOResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/actors")
public class ActorController {

    private final InterfaceActorService service;

    public ActorController(InterfaceActorService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<ActorDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public ActorDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<ActorDTOResponse> store(@Valid @RequestBody ActorDTORequest dto) {
        ActorDTOResponse dtoResponse = service.storeEntity(dto);

        if (dtoResponse == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<ActorDTOResponse> update(@PathVariable Long id, @Valid @RequestBody ActorDTORequest dto) {
        ActorDTOResponse dtoResponse = service.updateEntity(id, dto);

        if (dtoResponse == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.ok(dtoResponse);
    }
}

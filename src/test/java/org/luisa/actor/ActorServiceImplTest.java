package org.luisa.actor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.luisa.actor.dtos.ActorDTORequest;
import org.luisa.actor.dtos.ActorDTOResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

@ExtendWith(MockitoExtension.class)
class ActorServiceImplTest {

    @InjectMocks
    private ActorServiceImpl service;

    @Mock
    private ActorRepository repository;

    @BeforeEach
    void setUp() {
        service = new ActorServiceImpl(repository);
    }

    @Test
    void testGetEntities() {
        List<ActorEntity> actorsMock = List.of(
                new ActorEntity(1L, "Keanu Reeves"),
                new ActorEntity(2L, "Leonardo DiCaprio")
        );

        when(repository.findAll()).thenReturn(actorsMock);
        List<ActorDTOResponse> actors = service.getEntities();

        assertThat(actors.size(), is(equalTo(2)));
        assertThat(actors.get(0).name(), is(equalTo("Keanu Reeves")));
        assertThat(actors.get(1).name(), is(equalTo("Leonardo DiCaprio")));
    }

    @Test
    void testGetById() {
        ActorEntity actorMock = new ActorEntity(1L, "Keanu Reeves");

        when(repository.findById(1L)).thenReturn(Optional.of(actorMock));
        ActorDTOResponse actor = service.getById(1L);

        assertThat(actor.id(), is(equalTo(1L)));
        assertThat(actor.name(), is(equalTo("Keanu Reeves")));
    }

    @Test
    void testStoreActor() {
        ActorDTORequest dto = new ActorDTORequest("Morgan Freeman");
        ActorEntity savedEntity = new ActorEntity(1L, dto.name());

        when(repository.save(Mockito.any(ActorEntity.class))).thenReturn(savedEntity);
        when(repository.findAll(Mockito.<Example<ActorEntity>>any())).thenReturn(List.of());
        ActorDTOResponse entity = service.storeEntity(dto);

        assertThat(entity.name(), is(equalTo("Morgan Freeman")));
    }

    @Test
    void testStoreActor_ActorExist() {
        ActorDTORequest dto = new ActorDTORequest("Morgan Freeman");
        ActorEntity actor = new ActorEntity(1L, "Morgan Freeman");

        when(repository.findAll(Mockito.<Example<ActorEntity>>any())).thenReturn(List.of(actor));
        ActorDTOResponse entity = service.storeEntity(dto);

        assertThat(entity, nullValue());
    }

    @Test
    void testUpdateActor() {
        ActorDTORequest dto = new ActorDTORequest("Tom Hanks");
        ActorEntity updatedEntity = new ActorEntity(1L, "Tom Hanks");

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.existsByNameAndIdNot("Tom Hanks", 1L)).thenReturn(false);
        when(repository.save(Mockito.any(ActorEntity.class))).thenReturn(updatedEntity);

        ActorDTOResponse entity = service.updateEntity(1L, dto);

        assertThat(entity.name(), is(equalTo("Tom Hanks")));
    }

    @Test
    void testUpdateActor_ActorExist() {
        ActorDTORequest dto = new ActorDTORequest("Morgan Freeman");

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.existsByNameAndIdNot("Morgan Freeman", 1L)).thenReturn(true);

        ActorDTOResponse entity = service.updateEntity(1L, dto);

        assertThat(entity, nullValue());
    }
}

package org.luisa.actor.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import org.luisa.actor.ActorEntity;
import org.luisa.actor.dtos.ActorDTORequest;
import org.luisa.actor.dtos.ActorDTOResponse;

class ActorMapperTest {

    @Test
    void testToDTO() {
        ActorEntity actor = new ActorEntity(1L, "Keanu Reeves");

        ActorDTOResponse dto = ActorMapper.toDTO(actor);

        assertThat(dto.id(), is(equalTo(1L)));
        assertThat(dto.name(), is(equalTo("Keanu Reeves")));
    }

    @Test
    void testToEntity() {
        ActorDTORequest dto = new ActorDTORequest("Keanu Reeves");

        ActorEntity actor = ActorMapper.toEntity(dto);

        assertThat(actor.getName(), is(equalTo("Keanu Reeves")));
    }

    @Test
    void testConstructor_ShouldBePrivate() throws Exception {
        Constructor<ActorMapper> constructor = ActorMapper.class.getDeclaredConstructor();

        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }
}

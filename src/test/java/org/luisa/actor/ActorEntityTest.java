package org.luisa.actor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class ActorEntityTest {

    @Test
    void testActorEntity_InitializationWithIdAndName() {
        ActorEntity actor = new ActorEntity(1L, "Keanu Reeves");

        assertThat(actor, is(instanceOf(ActorEntity.class)));
        assertThat(actor.getClass().getDeclaredFields().length, is(equalTo(2)));
    }

    @Test
    void testActorEntity() {
        ActorEntity actor = new ActorEntity(1L, "Keanu Reeves");

        assertThat(actor.getId(), is(equalTo(1L)));
        assertThat(actor.getName(), is(equalTo("Keanu Reeves")));
    }
        @Test
        void testActorEntity_Builder() {
            ActorEntity actor = ActorEntity.builder()
                    .id(1L)
                    .name("Tom Hanks")
                    .build();

            assertThat(actor, instanceOf(ActorEntity.class));
            assertThat(actor.getId(), is(equalTo(1L)));
            assertThat(actor.getName(), is(equalTo("Tom Hanks")));
        }
}

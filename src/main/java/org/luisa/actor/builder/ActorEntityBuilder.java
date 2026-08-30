package org.luisa.actor.builder;

import org.luisa.actor.ActorEntity;

public class ActorEntityBuilder implements IActorBuilder {

    private final ActorEntity entity;

    public ActorEntityBuilder() {
        entity = new ActorEntity();
    }

    @Override
    public ActorEntityBuilder id(Long id) {
        entity.setId(id);
        return this;
    }

    @Override
    public ActorEntityBuilder name(String name) {
        entity.setName(name);
        return this;
    }

    @Override
    public ActorEntity build() {
        return entity;
    }
}

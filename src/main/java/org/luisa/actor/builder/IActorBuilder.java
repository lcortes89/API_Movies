package org.luisa.actor.builder;

import org.luisa.actor.ActorEntity;

public interface IActorBuilder {

    ActorEntityBuilder id(Long id);

    ActorEntityBuilder name(String name);

    ActorEntity build();
}

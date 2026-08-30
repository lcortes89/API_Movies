package org.luisa.genre.builder;

import org.luisa.genre.GenreEntity;

public class GenreEntityBuilder implements IGenreBuilder {

    private final GenreEntity entity;

    public GenreEntityBuilder() {
        entity = new GenreEntity();
    }

    @Override
    public GenreEntityBuilder id(Long id) {
        entity.setId(id);
        return this;
    }

    @Override
    public GenreEntityBuilder name(String name) {
        entity.setName(name);
        return this;
    }

    @Override
    public GenreEntity build() {
        return entity;
    }
}
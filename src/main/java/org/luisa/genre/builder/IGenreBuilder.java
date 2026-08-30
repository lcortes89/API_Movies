package org.luisa.genre.builder;

import org.luisa.genre.GenreEntity;

public interface IGenreBuilder {

    GenreEntityBuilder id(Long id);

    GenreEntityBuilder name(String name);

    GenreEntity build();
}
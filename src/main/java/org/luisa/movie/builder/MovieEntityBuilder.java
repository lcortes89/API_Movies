package org.luisa.movie.builder;

import java.util.List;

import org.luisa.actor.ActorEntity;
import org.luisa.genre.GenreEntity;
import org.luisa.movie.MovieEntity;
import org.luisa.year.YearEntity;

public class MovieEntityBuilder implements IMovieBuilder {

    private final MovieEntity entity;

    public MovieEntityBuilder() {
        entity = new MovieEntity();
    }

    @Override
    public MovieEntityBuilder id(Long id) {
        entity.setId(id);
        return this;
    }

    @Override
    public MovieEntityBuilder title(String title) {
        entity.setTitle(title);
        return this;
    }

    @Override
    public MovieEntityBuilder synopsis(String synopsis) {
        entity.setSynopsis(synopsis);
        return this;
    }

    @Override
    public MovieEntityBuilder year(YearEntity year) {
        entity.setYear(year);
        return this;
    }

    @Override
    public MovieEntityBuilder genre(GenreEntity genre) {
        entity.setGenre(genre);
        return this;
    }

    @Override
    public MovieEntityBuilder actors(List<ActorEntity> actors) {
        entity.setActors(actors);
        return this;
    }

    @Override
    public MovieEntity build() {
        return entity;
    }
}

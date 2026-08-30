package org.luisa.movie.builder;

import java.util.List;

import org.luisa.actor.ActorEntity;
import org.luisa.genre.GenreEntity;
import org.luisa.movie.MovieEntity;
import org.luisa.year.YearEntity;

public interface IMovieBuilder {

    MovieEntityBuilder id(Long id);

    MovieEntityBuilder title(String title);

    MovieEntityBuilder synopsis(String synopsis);

    MovieEntityBuilder year(YearEntity year);

    MovieEntityBuilder genre(GenreEntity genre);

    MovieEntityBuilder actors(List<ActorEntity> actors);

    MovieEntity build();
}

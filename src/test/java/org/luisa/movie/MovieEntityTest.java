package org.luisa.movie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.luisa.actor.ActorEntity;
import org.luisa.genre.GenreEntity;
import org.luisa.year.YearEntity;

public class MovieEntityTest {

    @Test
    void testMovieEntity_InitializationWithAllFields() {
        GenreEntity genre = new GenreEntity(1L, "Sci-Fi");
        YearEntity year = new YearEntity(1L, 1999);
        ActorEntity actor = new ActorEntity(1L, "Keanu Reeves");

        MovieEntity movie = new MovieEntity();
        movie.setId(1L);
        movie.setTitle("The Matrix");
        movie.setSynopsis("A hacker discovers his reality is a simulation.");
        movie.setGenre(genre);
        movie.setYear(year);
        movie.setActors(List.of(actor));

        assertThat(movie, is(instanceOf(MovieEntity.class)));
        assertThat(movie.getTitle(), is(equalTo("The Matrix")));
        assertThat(movie.getGenre(), is(equalTo(genre)));
        assertThat(movie.getYear(), is(equalTo(year)));
        assertThat(movie.getActors(), is(equalTo(List.of(actor))));
    }

    @Test
    void testMovieEntity_Builder() {
        GenreEntity genre = new GenreEntity(1L, "Sci-Fi");
        YearEntity year = new YearEntity(1L, 1999);
        ActorEntity actor = new ActorEntity(1L, "Keanu Reeves");

        MovieEntity movie = MovieEntity.builder()
                .id(1L)
                .title("The Matrix")
                .synopsis("A hacker discovers his reality is a simulation.")
                .genre(genre)
                .year(year)
                .actors(List.of(actor))
                .build();

        assertThat(movie, instanceOf(MovieEntity.class));
        assertThat(movie.getId(), is(equalTo(1L)));
        assertThat(movie.getTitle(), is(equalTo("The Matrix")));
        assertThat(movie.getGenre(), is(equalTo(genre)));
        assertThat(movie.getYear(), is(equalTo(year)));
        assertThat(movie.getActors(), is(equalTo(List.of(actor))));
    }
}

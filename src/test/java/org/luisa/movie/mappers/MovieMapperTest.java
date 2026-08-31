package org.luisa.movie.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.luisa.genre.GenreEntity;
import org.luisa.movie.MovieEntity;
import org.luisa.movie.dtos.MovieDTOResponse;
import org.luisa.year.YearEntity;

class MovieMapperTest {

    @Test
    void testToDTO() {
        GenreEntity genre = new GenreEntity(1L, "Sci-Fi");
        YearEntity year = new YearEntity(1L, 1999);

        MovieEntity movie = new MovieEntity();
        movie.setId(1L);
        movie.setTitle("The Matrix");
        movie.setSynopsis("A hacker discovers his reality is a simulation.");
        movie.setGenre(genre);
        movie.setYear(year);
        movie.setActors(List.of());

        MovieDTOResponse dto = MovieMapper.toDTO(movie);

        assertThat(dto.id(), is(equalTo(1L)));
        assertThat(dto.title(), is(equalTo("The Matrix")));
        assertThat(dto.genre().name(), is(equalTo("Sci-Fi")));
        assertThat(dto.year().releaseYear(), is(equalTo(1999)));
    }

    @Test
    void testConstructor_ShouldBePrivate() throws Exception {
        Constructor<MovieMapper> constructor = MovieMapper.class.getDeclaredConstructor();

        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }
}

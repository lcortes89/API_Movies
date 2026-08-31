package org.luisa.genre.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import org.luisa.genre.GenreEntity;
import org.luisa.genre.dtos.GenreDTORequest;
import org.luisa.genre.dtos.GenreDTOResponse;

class GenreMapperTest {

    @Test
    void testToDTO() {
        GenreEntity genre = new GenreEntity(1L, "Terror");

        GenreDTOResponse dto = GenreMapper.toDTO(genre);

        assertThat(dto.id(), is(equalTo(1L)));
        assertThat(dto.name(), is(equalTo("Terror")));
    }

    @Test
    void testToEntity() {
        GenreDTORequest dto = new GenreDTORequest("Terror");

        GenreEntity genre = GenreMapper.toEntity(dto);

        assertThat(genre.getName(), is(equalTo("Terror")));
    }

    @Test
    void testConstructor_ShouldBePrivate() throws Exception {
        Constructor<GenreMapper> constructor = GenreMapper.class.getDeclaredConstructor();

        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }
}

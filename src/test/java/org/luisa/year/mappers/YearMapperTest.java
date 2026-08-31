package org.luisa.year.mappers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import org.luisa.year.YearEntity;
import org.luisa.year.dtos.YearDTORequest;
import org.luisa.year.dtos.YearDTOResponse;

class YearMapperTest {

    @Test
    void testToDTO() {
        YearEntity year = new YearEntity(1L, 1999);

        YearDTOResponse dto = YearMapper.toDTO(year);

        assertThat(dto.id(), is(equalTo(1L)));
        assertThat(dto.releaseYear(), is(equalTo(1999)));
    }

    @Test
    void testToEntity() {
        YearDTORequest dto = new YearDTORequest(1999);

        YearEntity year = YearMapper.toEntity(dto);

        assertThat(year.getReleaseYear(), is(equalTo(1999)));
    }

    @Test
    void testConstructor_ShouldBePrivate() throws Exception {
        Constructor<YearMapper> constructor = YearMapper.class.getDeclaredConstructor();

        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }
}

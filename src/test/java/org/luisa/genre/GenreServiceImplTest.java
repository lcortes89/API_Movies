package org.luisa.genre;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.luisa.genre.dtos.GenreDTORequest;
import org.luisa.genre.dtos.GenreDTOResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @InjectMocks
    private GenreServiceImpl service;

    @Mock
    private GenreRepository repository;

    @BeforeEach
    void setUp() {
        service = new GenreServiceImpl(repository);
    }

    @Test
    void testGetEntities() {
        List<GenreEntity> genresMock = List.of(
                new GenreEntity(1L, "Sci-Fi"),
                new GenreEntity(2L, "Drama")
        );

        when(repository.findAll()).thenReturn(genresMock);
        List<GenreDTOResponse> genres = service.getEntities();

        assertThat(genres.size(), is(equalTo(2)));
        assertThat(genres.get(0).name(), is(equalTo("Sci-Fi")));
        assertThat(genres.get(1).name(), is(equalTo("Drama")));
    }

    @Test
    void testGetById() {
        GenreEntity genreMock = new GenreEntity(1L, "Sci-Fi");

        when(repository.findById(1L)).thenReturn(Optional.of(genreMock));
        GenreDTOResponse genre = service.getById(1L);

        assertThat(genre.id(), is(equalTo(1L)));
        assertThat(genre.name(), is(equalTo("Sci-Fi")));
    }

    @Test
    void testStoreGenre() {
        GenreDTORequest dto = new GenreDTORequest("Comedy");

        when(repository.save(Mockito.any(GenreEntity.class))).thenReturn(new GenreEntity(1L, dto.name()));
        when(repository.findAll(Mockito.<Example<GenreEntity>>any())).thenReturn(List.of());
        GenreDTOResponse entity = service.storeEntity(dto);

        assertThat(entity.name(), is(equalTo("Comedy")));
    }

    @Test
    void testStoreGenre_GenreExist() {
        GenreDTORequest dto = new GenreDTORequest("Comedy");
        GenreEntity genre = new GenreEntity(1L, "Comedy");

        when(repository.findAll(Mockito.<Example<GenreEntity>>any())).thenReturn(List.of(genre));
        GenreDTOResponse entity = service.storeEntity(dto);

        assertThat(entity, nullValue());
    }

    @Test
    void testUpdateGenre() {
        GenreDTORequest dto = new GenreDTORequest("Suspense");

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.existsByNameAndIdNot("Suspense", 1L)).thenReturn(false);
        when(repository.save(Mockito.any(GenreEntity.class))).thenReturn(new GenreEntity(1L, "Suspense"));

        GenreDTOResponse entity = service.updateEntity(1L, dto);

        assertThat(entity.name(), is(equalTo("Suspense")));
    }

    @Test
    void testUpdateGenre_GenreExist() {
        GenreDTORequest dto = new GenreDTORequest("Comedy");

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.existsByNameAndIdNot("Comedy", 1L)).thenReturn(true);

        GenreDTOResponse entity = service.updateEntity(1L, dto);

        assertThat(entity, nullValue());
    }
}

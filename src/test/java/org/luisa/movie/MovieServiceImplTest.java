package org.luisa.movie;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.luisa.actor.ActorRepository;
import org.luisa.genre.GenreEntity;
import org.luisa.genre.GenreRepository;
import org.luisa.movie.dtos.MovieDTORequest;
import org.luisa.movie.dtos.MovieDTOResponse;
import org.luisa.movie.exceptions.MovieBadRequestException;
import org.luisa.movie.exceptions.MovieConflictException;
import org.luisa.movie.exceptions.MovieNotFoundException;
import org.luisa.year.YearEntity;
import org.luisa.year.YearRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl service;

    @Mock
    private MovieRepository repository;

    @Mock
    private YearRepository yearRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private ActorRepository actorRepository;

    @BeforeEach
    void setUp() {
        service = new MovieServiceImpl(repository, yearRepository, genreRepository, actorRepository);
    }

    @Test
    void testGetEntities() {
        MovieEntity movieMock = new MovieEntity();
        movieMock.setId(1L);
        movieMock.setTitle("The Matrix");

        when(repository.findAll()).thenReturn(List.of(movieMock));

        List<MovieDTOResponse> movies = service.getEntities();

        assertThat(movies.size(), is(equalTo(1)));
        assertThat(movies.get(0).title(), is(equalTo("The Matrix")));
    }

    @Test
    void testGetById() {
        MovieEntity movieMock = new MovieEntity();
        movieMock.setId(1L);
        movieMock.setTitle("The Matrix");

        when(repository.findById(1L)).thenReturn(Optional.of(movieMock));

        MovieDTOResponse movie = service.getById(1L);

        assertThat(movie.id(), is(equalTo(1L)));
        assertThat(movie.title(), is(equalTo("The Matrix")));
    }

    @Test
    void testGetById_NotFound_ShouldThrowException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void testStoreMovie() {
        GenreEntity genre = new GenreEntity(1L, "Sci-Fi");
        YearEntity year = new YearEntity(1L, 1999);
        MovieDTORequest dto = new MovieDTORequest("The Matrix", "synopsis", 1L, 1L, null);
        MovieEntity savedMovie = new MovieEntity();
        savedMovie.setId(1L);
        savedMovie.setTitle("The Matrix");

        when(repository.existsByTitleIgnoreCase("The Matrix")).thenReturn(false);
        when(yearRepository.findById(1L)).thenReturn(Optional.of(year));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(repository.save(Mockito.any(MovieEntity.class))).thenReturn(savedMovie);

        MovieDTOResponse movie = service.storeEntity(dto);

        assertThat(movie.title(), is(equalTo("The Matrix")));
    }

    @Test
    void testStoreMovie_TitleExists_ShouldThrowConflictException() {
        MovieDTORequest dto = new MovieDTORequest("The Matrix", "synopsis", 1L, 1L, null);

        when(repository.existsByTitleIgnoreCase("The Matrix")).thenReturn(true);

        assertThrows(MovieConflictException.class, () -> service.storeEntity(dto));
    }

    @Test
    void testStoreMovie_YearNotFound_ShouldThrowBadRequestException() {
        MovieDTORequest dto = new MovieDTORequest("The Matrix", "synopsis", 1L, 1L, null);

        when(repository.existsByTitleIgnoreCase("The Matrix")).thenReturn(false);
        when(yearRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MovieBadRequestException.class, () -> service.storeEntity(dto));
    }

    @Test
    void testStoreMovie_GenreNotFound_ShouldThrowBadRequestException() {
        YearEntity year = new YearEntity(1L, 1999);
        MovieDTORequest dto = new MovieDTORequest("The Matrix", "synopsis", 1L, 1L, null);

        when(repository.existsByTitleIgnoreCase("The Matrix")).thenReturn(false);
        when(yearRepository.findById(1L)).thenReturn(Optional.of(year));
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MovieBadRequestException.class, () -> service.storeEntity(dto));
    }

    @Test
    void testUpdateMovie() {
        GenreEntity genre = new GenreEntity(1L, "Sci-Fi");
        YearEntity year = new YearEntity(1L, 1999);
        MovieDTORequest dto = new MovieDTORequest("The Matrix Reloaded", "synopsis", 1L, 1L, null);
        MovieEntity existingMovie = new MovieEntity();
        existingMovie.setId(1L);
        MovieEntity updatedMovie = new MovieEntity();
        updatedMovie.setId(1L);
        updatedMovie.setTitle("The Matrix Reloaded");

        when(repository.findById(1L)).thenReturn(Optional.of(existingMovie));
        when(repository.existsByTitleIgnoreCaseAndIdNot("The Matrix Reloaded", 1L)).thenReturn(false);
        when(yearRepository.findById(1L)).thenReturn(Optional.of(year));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(repository.save(Mockito.any(MovieEntity.class))).thenReturn(updatedMovie);

        MovieDTOResponse movie = service.updateEntity(1L, dto);

        assertThat(movie.title(), is(equalTo("The Matrix Reloaded")));
    }

    @Test
    void testUpdateMovie_NotFound_ShouldThrowException() {
        MovieDTORequest dto = new MovieDTORequest("The Matrix Reloaded", "synopsis", 1L, 1L, null);

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> service.updateEntity(1L, dto));
    }

    @Test
    void testDeleteMovie() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteEntity(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void testDeleteMovie_NotFound_ShouldThrowException() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(MovieNotFoundException.class, () -> service.deleteEntity(1L));
    }

    @Test
    void testFindByTitle() {
        MovieEntity movie = new MovieEntity();
        movie.setId(1L);
        movie.setTitle("The Matrix");

        when(repository.findByTitleContainingIgnoreCase("Matrix")).thenReturn(List.of(movie));

        List<MovieDTOResponse> movies = service.findByTitle("Matrix");

        assertThat(movies.size(), is(equalTo(1)));
        assertThat(movies.get(0).title(), is(equalTo("The Matrix")));
    }

    @Test
    void testFindByGenre() {
        MovieEntity movie = new MovieEntity();
        movie.setId(1L);
        movie.setTitle("The Matrix");

        when(repository.findByGenre_NameContainingIgnoreCase("Sci-Fi")).thenReturn(List.of(movie));

        List<MovieDTOResponse> movies = service.findByGenre("Sci-Fi");

        assertThat(movies.size(), is(equalTo(1)));
        assertThat(movies.get(0).title(), is(equalTo("The Matrix")));
    }
}

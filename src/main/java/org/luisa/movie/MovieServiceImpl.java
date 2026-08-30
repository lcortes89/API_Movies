package org.luisa.movie;

import java.util.List;

import org.luisa.actor.ActorEntity;
import org.luisa.actor.ActorRepository;
import org.luisa.genre.GenreEntity;
import org.luisa.genre.GenreRepository;
import org.luisa.movie.dtos.MovieDTORequest;
import org.luisa.movie.dtos.MovieDTOResponse;
import org.luisa.movie.exceptions.MovieBadRequestException;
import org.luisa.movie.exceptions.MovieConflictException;
import org.luisa.movie.exceptions.MovieNotFoundException;
import org.luisa.movie.mappers.MovieMapper;
import org.luisa.year.YearEntity;
import org.luisa.year.YearRepository;

import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements InterfaceMovieService {

    private final MovieRepository repository;
    private final YearRepository yearRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    public MovieServiceImpl(MovieRepository repository, YearRepository yearRepository,
            GenreRepository genreRepository, ActorRepository actorRepository) {
        this.repository = repository;
        this.yearRepository = yearRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public List<MovieDTOResponse> getEntities() {
        return repository.findAll().stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    @Override
    public MovieDTOResponse getById(Long id) {
        MovieEntity movie = repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found. Id " + id + " does not exist."));
        return MovieMapper.toDTO(movie);
    }

    public MovieDTOResponse storeEntity(MovieDTORequest dto) {
        if (repository.existsByTitleIgnoreCase(dto.title())) {
            throw new MovieConflictException("Movie already exists with title '" + dto.title() + "'.");
        }

        MovieEntity movie = buildMovieFromDTO(new MovieEntity(), dto);
        MovieEntity savedMovie = repository.save(movie);
        return MovieMapper.toDTO(savedMovie);
    }

    public MovieDTOResponse updateEntity(Long id, MovieDTORequest dto) {
        MovieEntity movie = repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found. Id " + id + " does not exist."));

        if (repository.existsByTitleIgnoreCaseAndIdNot(dto.title(), id)) {
            throw new MovieConflictException("Movie already exists with title '" + dto.title() + "'.");
        }

        MovieEntity updatedMovie = buildMovieFromDTO(movie, dto);
        MovieEntity savedMovie = repository.save(updatedMovie);
        return MovieMapper.toDTO(savedMovie);
    }

    public void deleteEntity(Long id) {
        if (!repository.existsById(id)) {
            throw new MovieNotFoundException("Movie not found. Id " + id + " does not exist.");
        }
        repository.deleteById(id);
    }

    public List<MovieDTOResponse> findByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title).stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    public List<MovieDTOResponse> findByGenre(String genreName) {
        return repository.findByGenres_NameContainingIgnoreCase(genreName).stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    private MovieEntity buildMovieFromDTO(MovieEntity movie, MovieDTORequest dto) {
        YearEntity year = yearRepository.findById(dto.yearId())
                .orElseThrow(() -> new MovieBadRequestException("Year not found. Id " + dto.yearId() + " does not exist."));

        List<GenreEntity> genres = genreRepository.findAllById(dto.genreIds());
        if (genres.size() != dto.genreIds().size()) {
            throw new MovieBadRequestException("One or more genre ids do not exist.");
        }

        List<ActorEntity> actors = dto.actorIds() != null
                ? actorRepository.findAllById(dto.actorIds())
                : List.of();

        movie.setTitle(dto.title());
        movie.setSynopsis(dto.synopsis());
        movie.setYear(year);
        movie.setGenres(genres);
        movie.setActors(actors);
        return movie;
    }
}

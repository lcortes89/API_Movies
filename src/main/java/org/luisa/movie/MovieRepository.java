package org.luisa.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findByTitleContainingIgnoreCase(String title);

    List<MovieEntity> findByGenres_NameContainingIgnoreCase(String genreName);

    boolean existsByTitleIgnoreCaseAndIdNot(String title, Long id);
}

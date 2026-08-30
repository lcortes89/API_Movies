package org.luisa.year;

import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<YearEntity, Long> {
    boolean existsByReleaseYearAndIdNot(Integer releaseYear, Long id);
}

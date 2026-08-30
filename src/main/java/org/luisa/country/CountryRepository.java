package org.luisa.country;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * CountryRepository
 * JPA Query Methods:
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    Optional<CountryEntity> findByName(String name);

    List<CountryEntity> findByNameStartingWith(String letter);

    // JPQL
    @Query("SELECT c FROM CountryEntity c WHERE c.name LIKE %?1%")
    List<CountryEntity> findBySyllable(String syllable);

    
    @NativeQuery(value = "SELECT * FROM countries WHERE name LIKE CONCAT('%', :syllable, '%')")
    List<CountryEntity> findBySyllableNative(@Param("syllable") String syllable);
    

}

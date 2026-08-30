package org.luisa.year;

import org.luisa.year.builder.YearEntityBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "years")
public class YearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer releaseYear;

    public YearEntity() {
    }

    public YearEntity(Long id, Integer releaseYear) {
        this.id = id;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public static YearEntityBuilder builder() {
        return new YearEntityBuilder();
    }
}

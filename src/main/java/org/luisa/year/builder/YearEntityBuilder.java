package org.luisa.year.builder;

import org.luisa.year.YearEntity;

public class YearEntityBuilder implements IYearBuilder {

    private final YearEntity entity;

    public YearEntityBuilder() {
        entity = new YearEntity();
    }

    @Override
    public YearEntityBuilder id(Long id) {
        entity.setId(id);
        return this;
    }

    @Override
    public YearEntityBuilder releaseYear(Integer releaseYear) {
        entity.setReleaseYear(releaseYear);
        return this;
    }

    @Override
    public YearEntity build() {
        return entity;
    }
}

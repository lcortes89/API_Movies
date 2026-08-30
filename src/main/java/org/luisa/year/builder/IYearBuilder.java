package org.luisa.year.builder;

import org.luisa.year.YearEntity;

public interface IYearBuilder {

    YearEntityBuilder id(Long id);

    YearEntityBuilder releaseYear(Integer releaseYear);

    YearEntity build();
}

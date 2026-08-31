package org.luisa.year;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class YearEntityTest {

    @Test
    void testYearEntity_InitializationWithIdAndReleaseYear() {
        YearEntity year = new YearEntity(1L, 1999);

        assertThat(year, is(instanceOf(YearEntity.class)));
        assertThat(year.getClass().getDeclaredFields().length, is(equalTo(2)));
    }

    @Test
    void testYearEntity() {
        YearEntity year = new YearEntity(1L, 1999);

        assertThat(year.getId(), is(equalTo(1L)));
        assertThat(year.getReleaseYear(), is(equalTo(1999)));
    }
        @Test
        void testYearEntity_Builder() {
            YearEntity year = YearEntity.builder()
                    .id(1L)
                    .releaseYear(2015)
                    .build();

            assertThat(year, instanceOf(YearEntity.class));
            assertThat(year.getId(), is(equalTo(1L)));
            assertThat(year.getReleaseYear(), is(equalTo(2015)));
        }
}

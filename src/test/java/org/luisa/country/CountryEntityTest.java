package org.factoriaf5.country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class CountryEntityTest {

    @Test
    void testCountryEntity_InitializationWithIdAndName() {
        CountryEntity country = new CountryEntity(1L, "Spain");

        assertThat(country, is(instanceOf(CountryEntity.class)));
        assertThat(country.getClass().getDeclaredFields().length, is(equalTo(2)));
    }

    @Test
    void testCountryEntity() {
        CountryEntity country = new CountryEntity(1L, "Spain");

        assertThat(country.getId(), is(equalTo(1L)));
        assertThat(country.getName(), is(equalTo("Spain")));
    }

}

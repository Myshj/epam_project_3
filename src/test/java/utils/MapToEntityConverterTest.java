package utils;

import models.City;
import models.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import orm.queries.SqlQueryContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapToEntityConverterTest {
    private MapToEntityConverter<Country> countryConverter = new MapToEntityConverter<>(new SqlQueryContext<>(Country.class, null, null));
    private MapToEntityConverter<City> cityConverter = new MapToEntityConverter<>(new SqlQueryContext<>(City.class, null, null));

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void applyToBadMap() {
        Map badMap = Mockito.mock(Map.class);
        Mockito.when(badMap.get(Mockito.any())).thenThrow(new RuntimeException());
        assertEquals(null, countryConverter.apply(badMap));
    }

    @Test
    void applyCountry() {
        Country country = countryConverter.apply(
                new HashMap<String, Object>() {{
                    put("id", 1);
                    put("name", "test");
                }}
        );
        assertEquals("test", country.getName().getValue());
        assertEquals(1, country.getId().getValue().longValue());
    }

    @Test
    void applyCity() {
        City city = cityConverter.apply(
                new HashMap<String, Object>() {{
                    put("id", 1);
                    put("name", "test");
                    put("country_id", 1);
                }}
        );

        assertEquals("test", city.getName().getValue());
        assertEquals(1, city.getId().getValue().longValue());

    }
}
package utils;

import models.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpServletRequestToEntityConverterTest {
    private HttpServletRequestToEntityConverter<Country> converter = new HttpServletRequestToEntityConverter<>(
            Country.class
    );

    @Mock
    private HttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockRequest.getParameter("id")).thenReturn("1");
        Mockito.when(mockRequest.getParameter("name")).thenReturn("test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void apply() {
        Country country = converter.apply(mockRequest);

        assertEquals("test", country.getName().getValue());
    }

}
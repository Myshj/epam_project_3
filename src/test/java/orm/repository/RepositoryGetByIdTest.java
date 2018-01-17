package orm.repository;

import models.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RepositoryGetByIdTest {
    private Repository<Country> repository;

    @Mock
    private Connection connection;

    @Mock
    private ResultSet resultSet;

    @Mock
    private ResultSetMetaData resultSetMetaData;

    @Mock
    private PreparedStatement preparedStatement;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);

        when(resultSetMetaData.getColumnCount()).thenReturn(2);
        when(resultSetMetaData.getColumnName(1)).thenReturn("id");
        when(resultSetMetaData.getColumnName(2)).thenReturn("name");

        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.first()).thenReturn(true);

        when(resultSet.getMetaData()).thenReturn(resultSetMetaData);

        when(resultSet.getObject(1)).thenReturn(1);
        when(resultSet.getObject(2)).thenReturn("testCountry");


        repository = new Repository<>(Country.class, connection);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getById() throws SQLException {

        Country c = repository.getById(1).orElse(null);

        assertEquals(1, c.getId().getValue().longValue());
        assertEquals("testCountry", c.getName().getValue());
    }
}
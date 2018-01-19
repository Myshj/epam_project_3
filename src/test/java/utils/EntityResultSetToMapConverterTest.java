package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.converters.EntityResultSetToMapConverter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityResultSetToMapConverterTest {
    private EntityResultSetToMapConverter converter = new EntityResultSetToMapConverter();
    @Mock
    private ResultSet goodResultSet;

    @Mock
    private ResultSet badResultSet;

    @Mock
    private ResultSetMetaData resultSetMetaData;

    @Test
    void applyToGood() {

        Map<String, Object> map = converter.apply(goodResultSet);
        assertEquals("object1", map.get("testColumn1"));
        assertEquals("object2", map.get("testColumn2"));
    }

    @Test
    void applyToBad() {
        assertEquals(null, converter.apply(badResultSet));
    }

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(resultSetMetaData.getColumnCount()).thenReturn(2);
        Mockito.when(resultSetMetaData.getColumnName(1)).thenReturn("testColumn1");
        Mockito.when(resultSetMetaData.getColumnName(2)).thenReturn("testColumn2");

        Mockito.when(goodResultSet.getObject(1)).thenReturn("object1");
        Mockito.when(goodResultSet.getObject(2)).thenReturn("object2");

        Mockito.when(goodResultSet.getMetaData()).thenReturn(resultSetMetaData);

        Mockito.when(badResultSet.getMetaData()).thenThrow(new RuntimeException());
    }
}
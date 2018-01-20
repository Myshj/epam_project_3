package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import orm.queries.SqlQueryContext;
import utils.converters.EntityFromMapWriter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityFromMapWriterTest {

    private EntityFromMapWriter<TestEntity> writer = new EntityFromMapWriter<>(new SqlQueryContext<>(TestEntity.class, null, null));

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void apply() {
        TestEntity e = new TestEntity();
        e = writer.apply(
                e,
                new HashMap<String, Object>() {{
                    put("stringField", "test");
                    put("integerField", 1);
                    put("decimalField", BigDecimal.ONE);
                    put("enumField", "CASE_1");
                    put("id", 1);
                    put("timeStampField", Timestamp.valueOf(LocalDateTime.MAX));
                }}
        );

        assertEquals("test", e.stringField.getValue());
        assertEquals(1, e.integerField.getValue().longValue());
        assertEquals(BigDecimal.ONE, e.decimalField.getValue());
        assertEquals(TestEntity.TestEnum.CASE_1, e.enumField.getValue());
        assertEquals(1, e.getId().getValue().longValue());
        assertEquals(Timestamp.valueOf(LocalDateTime.MAX), e.timeStampField.getValue());
    }

}
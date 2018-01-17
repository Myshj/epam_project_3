package utils.meta.functions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestEntity;
import utils.meta.FieldMetaInfo;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetFieldsTest {
    private GetFields getter = new GetFields();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void apply() {
        Map<String, FieldMetaInfo> fields = getter.apply(TestEntity.class);
        assertEquals(6, fields.size());
    }

}
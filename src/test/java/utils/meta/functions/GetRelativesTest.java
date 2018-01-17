package utils.meta.functions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import orm.Model;
import utils.TestEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetRelativesTest {
    private GetRelatives getter = new GetRelatives();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void apply() {
        Map<String, Class<? extends Model>> relatives = getter.apply(TestEntity.class);
        assertEquals(1, relatives.size());
    }

}
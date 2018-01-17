package utils.meta.functions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestEntity;
import utils.meta.ModelNames;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetNamesTest {
    private GetNames getter = new GetNames();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void apply() {
        ModelNames names = getter.apply(TestEntity.class);
        assertEquals("testEntity", names.getSingular());
        assertEquals("testEntities", names.getPlural());
    }

}
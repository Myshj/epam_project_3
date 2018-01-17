package utils.meta.functions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GetActualParameterClassTest {
    private GetActualParameterClass getter = new GetActualParameterClass();
    private ArrayList<Integer> arrayList;
    private Integer integer;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void apply() throws NoSuchFieldException {
        assertEquals(
                Integer.class,
                getter.apply(GetActualParameterClassTest.class.getDeclaredField("arrayList"))
        );

        assertNull(
                getter.apply(GetActualParameterClassTest.class.getDeclaredField("integer"))
        );
    }

}
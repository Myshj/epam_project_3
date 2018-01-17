package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DefaultInstantiatorTest {
    private static class WithDefaultConstructor {
        public WithDefaultConstructor() {
        }
    }

    private static class WithoutDefaultConstructor {
        public WithoutDefaultConstructor(int a) {
        }
    }

    private DefaultInstantiator<WithDefaultConstructor> goodInstantiator = new DefaultInstantiator<>(
            WithDefaultConstructor.class
    );

    private DefaultInstantiator<WithoutDefaultConstructor> badInstantiator = new DefaultInstantiator<>(
            WithoutDefaultConstructor.class
    );

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get() {
        assertNotNull(goodInstantiator.get());
        assertNull(badInstantiator.get());
    }

}
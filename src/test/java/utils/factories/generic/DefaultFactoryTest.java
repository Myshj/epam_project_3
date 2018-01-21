package utils.factories.generic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DefaultFactoryTest {
    private static class WithDefaultConstructor {
        public WithDefaultConstructor() {
        }
    }

    private static class WithoutDefaultConstructor {
        public WithoutDefaultConstructor(int a) {
        }
    }

    private DefaultFactory<WithDefaultConstructor> goodInstantiator = new DefaultFactory<>(
            WithDefaultConstructor.class
    );

    private DefaultFactory<WithoutDefaultConstructor> badInstantiator = new DefaultFactory<>(
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
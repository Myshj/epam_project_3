package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DefaultConstructorExtractorTest {
    private DefaultConstructorExtractor extractor = new DefaultConstructorExtractor<>();

    private static class HasDefaultConstructor {
        public HasDefaultConstructor() {
        }
    }

    private static class WithoutDefaultConstructor {
        public WithoutDefaultConstructor(int value) {
        }
    }

    @BeforeEach
    void setUp() throws NoSuchMethodException {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void apply() {
        assertNotNull(extractor.apply(HasDefaultConstructor.class));
        assertNull(extractor.apply(WithoutDefaultConstructor.class));
    }

}
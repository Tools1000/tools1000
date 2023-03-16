package tools1000;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tools1000.SecretsLoader;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class SecretsLoaderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Disabled // secrets.properties on gitignore
    @Test
    void test01() throws IOException {
        Properties properties = new SecretsLoader().load().getProperties();
        assertNotNull(properties);
        assertEquals("test one", properties.getProperty("test.one"));
    }
}
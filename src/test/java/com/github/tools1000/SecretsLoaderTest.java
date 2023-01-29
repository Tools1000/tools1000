package com.github.tools1000;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void test01() throws IOException {
        Properties properties = new SecretsLoader().load().getProperties();
        assertNotNull(properties);
        assertEquals("test one", properties.getProperty("test.one"));
    }
}
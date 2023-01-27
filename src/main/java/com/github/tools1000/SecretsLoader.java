package com.github.tools1000;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class SecretsLoader {

    private final Properties properties;

    public SecretsLoader() {
        properties = new Properties();
    }

    public SecretsLoader load() throws IOException {
        try (InputStream stream = getClass().getResourceAsStream("/secret.properties")) {
            properties.load(stream);
        }
        log.debug("properties loaded: {}", properties);
        return this;
    }

    public Properties getProperties() {
        return properties;
    }
}

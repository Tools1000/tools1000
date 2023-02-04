package com.github.tools1000;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class SecretsLoader {

    private final Properties properties;

    public SecretsLoader() throws IOException {
        properties = new Properties();
        load();
    }

    SecretsLoader load() throws IOException {
        try (InputStream stream = getClass().getResourceAsStream("/secret.properties")) {
            properties.load(stream);
        }
        log.debug("properties loaded: {}", properties);
        return this;
    }

    Properties getProperties() {
        return properties;
    }

    public String getSecret(String name){
        return getProperties().getProperty(name);
    }
}

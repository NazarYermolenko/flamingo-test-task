package com.flamingo.framework.ui.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties = new Properties();

    public ConfigReader() {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("config.properties file not found in resources");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    public String getProperty(ConfigKey key) {
        String sysProp = System.getProperty(key.getKey());
        if (sysProp != null) {
            return sysProp;
        }
        return properties.getProperty(key.getKey(), key.getDefaultValue());
    }
}

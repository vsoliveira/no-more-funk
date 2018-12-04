package com.victor.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private Properties properties;
    private String property;

    public ApplicationProperties(String propertiesFile) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream propertiesStream = classLoader.getResourceAsStream(propertiesFile);

        try {
            Properties object = new Properties();
            object.load(propertiesStream);
            this.properties = object;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String getProperty(String property) {
        return this.properties.getProperty(property);
    }
}

package com.korom.filefinder.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * A configuration class to map the application properties to a JAVA object.
 */
@Configuration
public class ApplicationConfig {
    @Value("${com.korom.filefinder.rootpath}")
    private String rootPath;

    @Value("${com.korom.filefinder.username}")
    private String username;

    public String getRootPath() {
        return rootPath;
    }

    public String getUsername() {
        return username;
    }
}

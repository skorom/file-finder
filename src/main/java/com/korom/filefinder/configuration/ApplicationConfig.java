package com.korom.filefinder.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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

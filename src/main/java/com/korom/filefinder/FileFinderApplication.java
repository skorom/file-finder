package com.korom.filefinder;

import com.korom.filefinder.configuration.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;

@EnableWebMvc
@SpringBootApplication
public class FileFinderApplication {

    private final Logger logger = LoggerFactory.getLogger(FileFinderApplication.class);

    @Autowired
    ApplicationConfig applicationConfig;

    public static void main(String[] args) {
        SpringApplication.run(FileFinderApplication.class, args);
    }

    @PostConstruct
    public void logStartUpInformation() {
        Path rootPath = Paths.get(applicationConfig.getRootPath());
        if (!rootPath.toFile().exists()){
            logger.warn("The given directory ({}) is not exists. The service will never find anything",rootPath);
        }
        else if (! rootPath.toFile().isDirectory()){
            logger.warn("The given path ({}) is not a directory.",rootPath);
        }else {
            logger.info("The service will use the following root path for finding the files: {}",applicationConfig.getRootPath());
        }
    }
}

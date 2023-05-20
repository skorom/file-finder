package com.korom.filefinder.service.finder;

import com.korom.filefinder.configuration.ApplicationConfig;
import com.korom.filefinder.exception.FileFinderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileFinderServiceImpl implements FileFinderService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ApplicationConfig applicationConfig;

    @Autowired
    public FileFinderServiceImpl(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public List<Path> findFilesByFileExtension(String fileExtension) {
        List<Path> files;
        if (fileExtension == null || fileExtension.isEmpty()){
            throw new FileFinderException("The file extension cannot be empty.","101");
        }
        try {
            files = Files.find(Paths.get(applicationConfig.getRootPath()),
                            Integer.MAX_VALUE,
                            (filePath, fileAttr) -> fileExtension.equals(getFileExtension(filePath.toString()).orElse(""))).collect(Collectors.toList());
        }catch(IOException ex){
            throw new FileFinderException("Unexpected error during finding the files.", "102");
        }
        log.info("The following files were found for {} file extension: {}", fileExtension, files.stream().map(Path::toString).collect(Collectors.joining(",")));
        return files;
    }

    @Override
    public List<Path> findUniqueFilesByFileExtension(String fileExtension) {
        List<Path> files = findFilesByFileExtension(fileExtension);
        Map<String,Path> uniqueFiles = new HashMap<>();
        files.forEach(file->uniqueFiles.put(file.getFileName().toString(),file));

        return uniqueFiles.values().stream().toList();
    }

    private Optional<String> getFileExtension(String path){
        return Optional.ofNullable(path)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1));
    }
}

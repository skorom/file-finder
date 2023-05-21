package com.korom.filefinder.service.finder;

import java.nio.file.Path;
import java.util.List;

/**
 * One of the two high level features of the microservice: find files in a directory.
 */
public interface FileFinderService {

    List<Path> findFilesByFileExtension(String fileExtension);
    List<Path> findUniqueFilesByFileExtension(String fileExtension);


}

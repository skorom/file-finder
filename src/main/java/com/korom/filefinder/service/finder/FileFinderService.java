package com.korom.filefinder.service.finder;

import java.nio.file.Path;
import java.util.List;

public interface FileFinderService {

    List<Path> findFilesByFileExtension(String fileExtension);
    List<Path> findUniqueFilesByFileExtension(String fileExtension);


}

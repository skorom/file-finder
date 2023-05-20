package com.korom.filefinder.service.finder;

import com.korom.filefinder.configuration.ApplicationConfig;
import com.korom.filefinder.exception.FileFinderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileFinderServiceImplTest {

    @TempDir
    private File tempDir;
    @Mock
    ApplicationConfig applicationConfig;

    @InjectMocks
    private FileFinderServiceImpl fileFinderService;
    private String absolutePathOfTempDir;

    @BeforeEach
    void setUp() throws IOException {
        absolutePathOfTempDir=tempDir.getAbsolutePath();
        new File(Paths.get(absolutePathOfTempDir,"a").toUri()).mkdir();
        new File(Paths.get(absolutePathOfTempDir,"a","a.txt").toUri()).createNewFile();
        new File(Paths.get(absolutePathOfTempDir,"a","b.txt").toUri()).createNewFile();
        new File(Paths.get(absolutePathOfTempDir,"a","b.png").toUri()).createNewFile();
        new File(Paths.get(absolutePathOfTempDir,"b").toUri()).mkdir();
        new File(Paths.get(absolutePathOfTempDir,"b","b.txt").toUri()).createNewFile();
        new File(Paths.get(absolutePathOfTempDir,"b","c.txt").toUri()).createNewFile();
        new File(Paths.get(absolutePathOfTempDir,"b","b.png").toUri()).createNewFile();
        new File(Paths.get(absolutePathOfTempDir,"b","c.png").toUri()).createNewFile();
    }

    @Test
    @DisplayName("Finding the files fails if the given extension is empty.")
    void findFilesFailsForEmptyExtensionString() {
        FileFinderException exception = assertThrows(FileFinderException.class, () -> fileFinderService.findFilesByFileExtension(""));
        assertEquals("101",exception.getErrorCode());
    }

    @Test
    @DisplayName("Finding the files fails if the given extension is null.")
    void findFilesFailsForNullExtensionString() {
        FileFinderException exception = assertThrows(FileFinderException.class, () -> fileFinderService.findFilesByFileExtension(null));
        assertEquals("101",exception.getErrorCode());
    }

    @Test
    @DisplayName("Finding the files fails if root path is not exists.")
    void findFilesFailsForNonExistingRootPath() {
        when(applicationConfig.getRootPath()).thenReturn("wrongPath");
        FileFinderException exception = assertThrows(FileFinderException.class, () -> fileFinderService.findFilesByFileExtension("txt"));
        assertEquals("102",exception.getErrorCode());
    }

    @Test
    @DisplayName("Finding the files finding the files.")
    void findFilesFindsExactlyWhatIsNeeded() {
        when(applicationConfig.getRootPath()).thenReturn(absolutePathOfTempDir);
        List<Path> actualFileList= fileFinderService.findFilesByFileExtension("txt");
        String[] expected = new String[] {
                Paths.get(absolutePathOfTempDir,"a","a.txt").toString(),
                Paths.get(absolutePathOfTempDir,"a","b.txt").toString(),
                Paths.get(absolutePathOfTempDir,"b","b.txt").toString(),
                Paths.get(absolutePathOfTempDir,"b","c.txt").toString(),
        };
        assertThat(expected).hasSameElementsAs(actualFileList.stream().map(Path::toString).toList());

    }

    @Test
    @DisplayName("Finds and filters by filename.")
    void findUniqueFilesFindsUniqueFiles() {
        when(applicationConfig.getRootPath()).thenReturn(absolutePathOfTempDir);
        List<Path> actualFileList= fileFinderService.findUniqueFilesByFileExtension("txt");
        String[] expected = new String[] {
                Paths.get(absolutePathOfTempDir,"a","a.txt").toString(),
                Paths.get(absolutePathOfTempDir,"b","b.txt").toString(),
                Paths.get(absolutePathOfTempDir,"b","c.txt").toString(),
        };
        assertThat(expected).hasSameElementsAs(actualFileList.stream().map(Path::toString).toList());

    }

}
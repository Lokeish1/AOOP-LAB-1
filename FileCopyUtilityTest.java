import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileCopyUtilityTest {

    @TempDir
    File tempDir;

    @Test
    void testCopySmallFile() throws IOException {
        File sourceFile = new File(tempDir, "smallFile.txt");
        File destFile = new File(tempDir, "copiedSmallFile.txt");

        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write("This is a small file for testing.");
        }

        FileCopyUtility.copyFile(sourceFile, destFile);

        assertTrue(destFile.exists());
        assertEquals(sourceFile.length(), destFile.length());
    }

    @Test
    void testCopyLargeFile() throws IOException {
        File sourceFile = new File(tempDir, "largeFile.txt");
        File destFile = new File(tempDir, "copiedLargeFile.txt");

        // Create a large file with 10MB of data
        try (FileWriter writer = new FileWriter(sourceFile)) {
            for (int i = 0; i < 1024 * 1024 * 10 / 20; i++) {
                writer.write("This is a line in a large file.\n");
            }
        }

        FileCopyUtility.copyFile(sourceFile, destFile);

        assertTrue(destFile.exists());
        assertEquals(sourceFile.length(), destFile.length());
    }

    @Test
    void testInvalidInputNullSource() {
        File sourceFile = null;
        File destFile = new File(tempDir, "copiedFile.txt");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                FileCopyUtility.copyFile(sourceFile, destFile));

        assertEquals("Source and destination files must not be null.", exception.getMessage());
    }

    @Test
    void testInvalidInputNonExistentSource() {
        File sourceFile = new File(tempDir, "nonExistentFile.txt");
        File destFile = new File(tempDir, "copiedFile.txt");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                FileCopyUtility.copyFile(sourceFile, destFile));

        assertEquals("Source file does not exist or is not a file.", exception.getMessage());
    }

    @Test
    void testInvalidInputDirectoryAsDestination() throws IOException {
        File sourceFile = new File(tempDir, "sourceFile.txt");
        File destDir = Files.createTempDirectory(tempDir.toPath(), "destDir").toFile();

        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write("This is a test file.");
        }

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                FileCopyUtility.copyFile(sourceFile, destDir));

        assertEquals("Destination path is not a file.", exception.getMessage());
    }
}
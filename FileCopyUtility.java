import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyUtility {

    public static void copyFile(File source, File destination) throws IOException {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Source and destination files must not be null.");
        }

        if (!source.exists() || !source.isFile()) {
            throw new IllegalArgumentException("Source file does not exist or is not a file.");
        }

        if (destination.exists() && !destination.isFile()) {
            throw new IllegalArgumentException("Destination path is not a file.");
        }

        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }

    public static void main(String[] args) {
        try {
            File sourceFile = new File("path/to/source/file");
            File destFile = new File("path/to/destination/file");
            copyFile(sourceFile, destFile);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
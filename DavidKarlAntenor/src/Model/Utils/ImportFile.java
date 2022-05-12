package Model.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ImportFile {
    public static String getFileContent(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (Exception e) {
            return null;
        }
    }
}
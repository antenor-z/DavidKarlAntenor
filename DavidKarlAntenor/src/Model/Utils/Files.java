package Model.Utils;

import java.io.*;
import java.awt.Desktop;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class Files {
    public static String getFileContent(String path) {
        try {
            return java.nio.file.Files.readString(Paths.get(path), StandardCharsets.US_ASCII);
        } catch (Exception e) {
            return null;
        }
    }
}

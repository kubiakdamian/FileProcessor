package pl.qbsapps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {
    public int countLetters(String filePath) {
        Path path = Paths.get(filePath);
        String data = null;

        try {
            data = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data != null) {
            return data.length();
        }

        return 0;
    }
}

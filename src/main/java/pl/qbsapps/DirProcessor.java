package pl.qbsapps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DirProcessor {
    public int countLetters(String path) {
        FileProcessor fileProcessor = new FileProcessor();
        ArrayList<String> paths = new ArrayList<>();
        int sum = 0;

        try (Stream<Path> filePathStream = Files.walk(Paths.get(path))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    paths.add(filePath.toString());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String filePath : paths) {
            sum += fileProcessor.countLetters(filePath);
        }

        return sum;
    }
}

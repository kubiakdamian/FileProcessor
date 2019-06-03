import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import pl.qbsapps.DirProcessor;
import pl.qbsapps.FileProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileProcessorTest {
    private FileProcessor fileProcessor = new FileProcessor();
    private DirProcessor dirProcessor = new DirProcessor();

    @Test
    void shouldReturnProperNumberOfLetters(@TempDir Path tempDir) {
        assertThatProperNumberOfLettersWasReturned(tempDir, "", 0);
        assertThatProperNumberOfLettersWasReturned(tempDir, "a", 1);
        assertThatProperNumberOfLettersWasReturned(tempDir, "abcdefgh", 8);
    }

    @Test
    void shouldReturnProperNumberOfLettersForDir(@TempDir Path tempDir) {
        assertThatProperNumberOfLettersWasReturnedForDir(tempDir, "", 0, "", 0, "", 0);
        assertThatProperNumberOfLettersWasReturnedForDir(tempDir, "a", 1, "b", 1, "c", 1);
        assertThatProperNumberOfLettersWasReturnedForDir(tempDir, "abc", 3, "abcde", 5, "abcdefgh", 8);
    }

    @Test
    void shouldReturn0ForFileDifferentThanTxt(@TempDir Path tempDir) {
        Path file = tempDir.resolve("test.docx");

        try {
            Files.write(file, "abcd".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(0, fileProcessor.countLetters(file.toString()));
    }

    @Test
    void shouldReturnProperNumberOfLettersMock(@TempDir Path tempDir) {
        FileProcessor fileProcessorMock = mock(FileProcessor.class);

        when(fileProcessorMock.countLetters(tempDir.toString())).thenReturn(10);
        assertEquals(10, fileProcessorMock.countLetters(tempDir.toString()));
    }

    @Test
    void shouldReturnProperNumberOfLettersForDirMock(@TempDir Path tempDir) {
        DirProcessor dirProcessorMock = mock(DirProcessor.class);

        when(dirProcessorMock.countLetters(tempDir.toString())).thenReturn(10);
        assertEquals(10, dirProcessorMock.countLetters(tempDir.toString()));
    }

    private void assertThatProperNumberOfLettersWasReturned(@TempDir Path tempDir, String content, int numberOfLetters) {
        Path file = tempDir.resolve("test.txt");

        try {
            Files.write(file, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(numberOfLetters, fileProcessor.countLetters(file.toString()));
    }

    private void assertThatProperNumberOfLettersWasReturnedForDir(@TempDir Path tempDir, String firstContent, int firstNumberOfLetters,
                                                                  String secondContent, int secondNumberOfLetters,
                                                                  String thirdContent, int thirdNumberOfLetters) {
        Path file = tempDir.resolve("test.txt");
        Path file2 = tempDir.resolve("test2.txt");
        Path file3 = tempDir.resolve("test3.txt");

        try {
            Files.write(file, firstContent.getBytes());
            Files.write(file2, secondContent.getBytes());
            Files.write(file3, thirdContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int sumOfLetters = firstNumberOfLetters + secondNumberOfLetters + thirdNumberOfLetters;

        assertEquals(sumOfLetters, dirProcessor.countLetters(tempDir.toString()));
    }
}

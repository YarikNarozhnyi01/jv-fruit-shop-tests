package core.basesyntax.services.impl;

import core.basesyntax.services.FileDataWriter;
import core.basesyntax.services.ReportGenerator;
import core.basesyntax.storage.Storage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileDataWriterImplTest {
    private static final String expectedString = "fruit,quantity" + System.lineSeparator()
            + "banana,152" + System.lineSeparator()
            + "apple,90" + System.lineSeparator();
    private static final String OUTPUT_PATH = "src/test/resources/output1.csv";
    private static final String EXPECTED = "src/test/resources/outputTestExpected.csv";

    private ReportGenerator reportGenerator;
    private Storage storage;
    private Map<String, Integer> storageNew;
    private FileDataWriter fileDataWriter;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        storageNew = storage.getStorage();
        reportGenerator = new ReportGeneratorImpl(storage);
        fileDataWriter = new FileDataWriterImpl(Path.of(OUTPUT_PATH));
    }

    @AfterEach
    void tearDown() {
        storage.clearAll();
    }

    @Test
    void writeToFile_validFile_ok() {
        storageNew.put("banana", 152);
        storageNew.put("apple", 90);
        String actualString = reportGenerator.getReport();
        Assertions.assertEquals(expectedString, actualString);

        File actualFile = fileDataWriter.writeData(actualString, OUTPUT_PATH);
        String actualFileContent = null;
        try {
            actualFileContent = new String(Files.readAllBytes(Paths.get(actualFile.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedFileContent = null;
        try {
            expectedFileContent = new String(Files.readAllBytes(Paths.get(EXPECTED)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(expectedFileContent, actualFileContent, "Файли не збігаються!");
    }
}

package canadiantire.mapper;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvMapperTest {

    @Test
    void shouldMapCountriesWithDoubleQuotes() throws IOException {
        var testInstance = new CsvMapper();
        var classLoader = getClass().getClassLoader();
        var actual = new File(classLoader.getResource("countriesWithDoubleQuotes.csv").getFile());
   
        testInstance.mapFile(actual.getPath());

        var expected = new File(classLoader.getResource("expected.csv").getFile());

        assertTrue(FileUtils.contentEquals(actual, expected));
    }

    @Test
    void shouldCountriesWithoutDoubleQuotesAndShield() throws IOException {
        var testInstance = new CsvMapper();
        var classLoader = getClass().getClassLoader();
        var actual = new File(classLoader.getResource("countriesWithoutDoubleQuotesAndShield.csv").getFile());

        testInstance.mapFile(actual.getPath());

        var expected = new File(classLoader.getResource("expected.csv").getFile());

        assertTrue(FileUtils.contentEquals(actual, expected));
    }
}

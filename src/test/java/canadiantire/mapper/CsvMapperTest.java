package canadiantire.mapper;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvMapperTest {

    @ParameterizedTest
    @MethodSource("shouldProcess_testArgs")
    void shouldMapCountriesWithDoubleQuotes(String pathBeforeMapping, String pathAfterMapping) throws IOException {
        var testInstance = new CsvMapper();
        var classLoader = getClass().getClassLoader();
        var actual = new File(classLoader.getResource(pathBeforeMapping).getFile());
   
        testInstance.mapFile(actual.getPath());
        var actualAfterMapping = new File(classLoader.getResource(pathAfterMapping).getFile());

        var expected = new File(classLoader.getResource("expected.csv").getFile());

        assertTrue(FileUtils.contentEquals(actualAfterMapping, expected));
    }

    static Stream<Arguments> shouldProcess_testArgs() {

        return Stream.of(
            Arguments.of("countriesWithDoubleQuotes.csv", "countriesWithDoubleQuotesAfterMapping.csv"),
            Arguments.of("countriesWithoutDoubleQuotesAndShield.csv",
                "countriesWithoutDoubleQuotesAndShieldAfterMapping.csv"),
            Arguments.of("countriesWithShield.csv", "countriesWithShieldAfterMapping.csv"),
            Arguments.of("countriesWithShieldAndDoubleQuotes.csv", "countriesWithShieldAndDoubleQuotesAfterMapping.csv")
        );
    }
}

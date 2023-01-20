package canadiantire.mapper;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvMapper {

    public void mapFile(String pathToFile) {
        var result = new ArrayList<String>();
        try (var scanner = new Scanner(new FileReader(pathToFile))) {
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (!"".equals(line)) {
                    var splitLines = line.split(",");
                    var lineWithDoubleQuotes = Arrays.stream(splitLines)
                        .map(this::deleteShieldsIfExists)
                        .map(this::addDoubleQuotesIfNotExists)
                        .collect(Collectors.joining("\\,"));
                    result.add(lineWithDoubleQuotes);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var resultCsv = result.stream().collect(Collectors.joining(System.lineSeparator()));
        writeDataToFile(pathToFile, resultCsv);
    }

    private String deleteShieldsIfExists(String line) {
        if ("".equals(line)) {
            return "";
        }
        return line.charAt(line.length() - 1) == '\\' ? line.substring(0, line.length() - 1) : line;
    }

    private void writeDataToFile(String pathToFile, String data) {
        var pathWithoutExtension = pathToFile.substring(0, pathToFile.length() - 4);
        var newPath = pathWithoutExtension + "AfterMapping.csv";
        try(var fileWriter = new FileWriter(newPath);
            var bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(data);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String addDoubleQuotesIfNotExists(String input) {
        if (input != null && input.charAt(0) != '\"' && input.charAt(input.length() - 1) != '\"') {

            return "\"" + input + "\"";
        }
        return input;
    }
}

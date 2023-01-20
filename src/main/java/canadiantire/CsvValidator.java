package canadiantire;

import canadiantire.mapper.CsvMapper;

public class CsvValidator {

    public static void main(String[] args) {
        var pathToFile = args[0];
        var csvMapper = new CsvMapper();
        csvMapper.mapFile(pathToFile);
    }
}

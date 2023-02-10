package br.rs.mann.rest.util;

import br.rs.mann.rest.core.BaseTest;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.Iterator;
import java.util.logging.Logger;

public class CsvUtil extends BaseTest{

    private static final Logger LOGGER = Logger.getLogger(BaseTest.class.getName());

    public CsvUtil() {
    }

    public static Iterator<String[]> readAll(String path, boolean header) throws Exception {
        CSVReader csvReader = new CSVReader(new FileReader(path));
        Iterator<String[]> it = csvReader.readAll().iterator();
        csvReader.close();
        if (header) {
            it.next();
            it.remove();
        }
        LOGGER.info("Realizada a leitura do arquivo restrictions.csv");
        return it;
    }
}
package com.jemhourmehdi.importbdx;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.stream.Collectors;

@SpringBootApplication
public class ImportBdxApplication {

    public final static String PATH = "/Users/medinet/Berexia/import-bdx/test.csv";

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ImportBdxApplication.class, args);
//        InputStream inputStream = ImportBdxApplication.pathToInputStream("/Users/medinet/Berexia/import-bdx/AmRisc_Processed_BDX_2020-04.xlsx");
        InputStream inputStream = ImportBdxApplication.pathToInputStream("/Users/medinet/Berexia/import-bdx/AmRisc_Processed_BDX_2020-04.xlsx");

//        Sheet sheet = new ExcelReader().readSheet(inputStream, "AC");
//        new ExcelService(sheet).loadColumnHeaders(sheet);
//        new ExcelService(sheet).marshaller();

    }

    public static void generate(String toUri, String[] headers, Object[] records) throws IOException {
        Path output = Paths.get(toUri);
        BufferedWriter writer = Files.newBufferedWriter(output);
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers));
        csvPrinter.printRecords(records);
        csvPrinter.flush();
        csvPrinter.close();
    }

    public static InputStream pathToInputStream(String uri) throws IOException {
        Path path = Paths.get(uri);
        return new FileInputStream(path.toFile());
    }

}

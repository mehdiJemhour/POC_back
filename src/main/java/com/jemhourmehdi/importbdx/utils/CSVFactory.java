package com.jemhourmehdi.importbdx.utils;

import com.jemhourmehdi.importbdx.payload.BordereauData;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CSVFactory {

    public byte[] createCSV(BordereauData bordereauData) throws IOException {
        String[] columns = bordereauData.getColumns().stream()
                .map(column -> String.valueOf(column.get("field")))
                .collect(Collectors.toList())
                .toArray(new String[]{});

        Object[] records = bordereauData.getRows().stream()
                .map(row -> {
                    List<Object> items = new ArrayList<>();
                    for (String column : columns)
                        items.add(row.get(column));
                    return items;
                }).collect(Collectors.toList()).toArray();
        return this.toByte(columns, records);
    }

    private byte[] toByte(String[] headers, Object[] records) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(out);
        generate(printWriter,headers,records);
        return out.toByteArray();
    }

    private void generate(Writer writer, String[] headers, Object[] records) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers));
        csvPrinter.printRecords(records);
        csvPrinter.flush();
        csvPrinter.close();
    }

}

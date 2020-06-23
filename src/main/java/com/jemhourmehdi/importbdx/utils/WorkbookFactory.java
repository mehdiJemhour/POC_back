package com.jemhourmehdi.importbdx.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class WorkbookFactory {

    static Map<String, Function<InputStream, Workbook>> workbook = new HashMap<>();

    static{
        workbook.put("xls", inputStream -> {
            try {
                return new HSSFWorkbook(inputStream);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });

        workbook.put("xlsx", inputStream -> {
            try {
                return new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
    }


    public static Workbook constructWorkbook(String extension, InputStream inputStream) {
        Function<InputStream, Workbook> workbookFunction = Optional.ofNullable(workbook.get(extension)).orElseThrow(RuntimeException::new);
        return workbookFunction.apply(inputStream);
    }
}

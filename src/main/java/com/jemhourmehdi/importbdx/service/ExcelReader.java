package com.jemhourmehdi.importbdx.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ExcelReader {

    public Sheet readSheet(InputStream inputStream, String name) {
        try{
            // TODO Construct Workbook based on file extension
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            return workbook.getSheet(name);
        }catch (IOException e){
            throw new RuntimeException("File not found");
        }
    }

}

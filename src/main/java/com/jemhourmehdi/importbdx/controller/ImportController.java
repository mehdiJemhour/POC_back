package com.jemhourmehdi.importbdx.controller;

import com.jemhourmehdi.importbdx.service.ExcelReader;
import com.jemhourmehdi.importbdx.service.ExcelService;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/import")
@CrossOrigin("*") // TODO Add WebConfigurer filter for cross origin
public class ImportController {

    @Autowired
    ExcelReader excelReader;

    @PostMapping("/visualize/{sheetName}")
    // TODO Implement Global error handling
    public HttpEntity<Map> viewSheetData(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable String sheetName) {
        try {
            Sheet sheet = excelReader.readSheet(multipartFile.getInputStream(), sheetName);
            Map data = new ExcelService(sheet).marshaller();
            return ResponseEntity.ok(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

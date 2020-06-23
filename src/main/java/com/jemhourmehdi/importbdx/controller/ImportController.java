package com.jemhourmehdi.importbdx.controller;

import com.jemhourmehdi.importbdx.service.ExcelReader;
import com.jemhourmehdi.importbdx.service.ExcelService;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    ExcelReader excelReader;

    @PostMapping("/visualize/{sheetName}")
    public HttpEntity<Map> viewSheetData(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable String sheetName) {
        Sheet sheet = excelReader.read(multipartFile).withSheetName(sheetName);
        Map data = new ExcelService(sheet).marshaller();
        return ResponseEntity.ok(data);
    }
}

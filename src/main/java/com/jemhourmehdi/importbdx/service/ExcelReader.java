package com.jemhourmehdi.importbdx.service;

import com.jemhourmehdi.importbdx.utils.Utils;
import com.jemhourmehdi.importbdx.utils.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ExcelReader {

    private Workbook workbook;

    public Sheet withSheetName(String name) {
        return Optional.ofNullable(workbook.getSheet(name)).orElseThrow(()-> new RuntimeException("Sheet name not exist"));
    }


    public ExcelReader read(MultipartFile multipartFile) {
        try {
            String extension = Utils.getExtension(multipartFile.getOriginalFilename());
            InputStream inputStream = multipartFile.getInputStream();
            this.workbook = WorkbookFactory.constructWorkbook(extension, inputStream);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

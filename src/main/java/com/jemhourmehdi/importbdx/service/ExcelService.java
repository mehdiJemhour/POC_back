package com.jemhourmehdi.importbdx.service;

import org.apache.poi.ss.usermodel.*;

import java.util.*;
import java.util.stream.StreamSupport;

public class ExcelService {

    private Map<Integer, String> titles = new HashMap<>();
    private Set<Map<String, String>> financials = new HashSet<>();
    private Sheet sheet;

    public ExcelService(Sheet sheet) {
        this.sheet = sheet;
    }

    public Map<String, Object> marshaller() {
        List<Map> data = init();

        Map<String, Object> result = new HashMap();
        result.put("financialColumns", financials);
        result.put("data", data);
        return result;
    }

    private List<Map> init() {
        List<Map> mapList = new ArrayList<>();
        StreamSupport.stream(rows(), false)
                .forEach(row -> {
                    Map<String, Object> maps = new HashMap<>();
                    row.forEach(cell -> {
                        if (!cell.getCellType().equals(CellType.BLANK)) {
                            maps.put(titles.get(cell.getColumnIndex()), formatDataCell(cell));
                            if (cell.getCellType().equals(CellType.NUMERIC) && !DateUtil.isCellDateFormatted(cell)) {
                                Map<String, String> field = new HashMap<>();
                                field.put("field", titles.get(cell.getColumnIndex()));
                                financials.add(field);
                            }
                        }
                    });
                    mapList.add(maps);
                });
        return mapList;
    }

    private Spliterator<Row> rows() {
        this.loadColumnHeaders(sheet);
        sheet.removeRow(sheet.getRow(0));
        return sheet.spliterator();
    }

    private void loadColumnHeaders(Sheet sheet) {
        Row firstRow = sheet.getRow(0);
        for (Cell cell : firstRow) {
            if (!cell.getCellType().equals(CellType.BLANK))
                titles.put(cell.getColumnIndex(), cell.getStringCellValue());
        }
    }

    private Object formatDataCell(Cell cell) {
        if (cell.getCellType().equals(CellType.NUMERIC))
            if (DateUtil.isCellDateFormatted(cell)) return cell.getDateCellValue();
            else return cell.getNumericCellValue();
        return cell.getStringCellValue();
    }
}

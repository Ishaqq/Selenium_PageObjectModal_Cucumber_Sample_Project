package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    public static List<HashMap<String, String>> getExcelData(String path, String sheetName) {
        List<HashMap<String, String>> testData = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);
            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                HashMap<String, String> rowMap = new HashMap<>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = new DataFormatter().formatCellValue(row.getCell(j));
                    rowMap.put(key, value);
                }
                testData.add(rowMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }
}

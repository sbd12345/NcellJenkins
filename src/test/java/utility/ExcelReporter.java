package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelReporter {
    private List<Object[]> testData = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void addTestResult(String testName, String status, long duration, String errorMessage) {
        String timestamp = dateFormat.format(new Date());
        testData.add(new Object[]{testName, status, timestamp, duration + " ms", errorMessage});
    }

    public void generateReport(String filePath) throws IOException {
        Workbook workbook;
        Sheet sheet;
        File file = new File(filePath);

        // Ensure directory exists
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = WorkbookFactory.create(fis);  
            }
            sheet = workbook.getSheetAt(0);
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Test Results");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Test Case", "Status", "Execution Time", "Duration", "Error Message"};

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
        }

        int rowNum = sheet.getLastRowNum() + 1;

        for (Object[] data : testData) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < data.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(data[i].toString());

                // Apply status-based coloring
                if (i == 1) {
                    CellStyle style = workbook.createCellStyle();
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    switch (data[i].toString()) {
                        case "PASS":
                            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                            break;
                        case "FAIL":
                            style.setFillForegroundColor(IndexedColors.RED.getIndex());
                            break;
                        case "SKIP":
                            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
                            break;
                    }
                    cell.setCellStyle(style);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}


package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static FileInputStream fileInputStream;
	static Workbook workBook;
	static FileReaderManager excellData ;
	
	public static String getCellValue(String sheetName, String id, String colName) {

		File file = new File(excellData.getProperty("excelpath"));
		String value = "";
		try {
			fileInputStream = new FileInputStream(file);
			workBook = new XSSFWorkbook(fileInputStream);
			int col_Num = -1;
			Sheet sheet = workBook.getSheet(sheetName);
			Row row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
					col_Num = i;
					break;
				}
			}

			if (col_Num == -1) {
				throw new Exception("Column not found: " + colName);
			}
			DataFormatter dataFormatter = new DataFormatter();
			int numberOfRows = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < numberOfRows; i++) {
				Row currentRow = sheet.getRow(i);
				if (currentRow != null && currentRow.getCell(0).getStringCellValue().equals(id)) {
					Cell cell = currentRow.getCell(col_Num);
					value = dataFormatter.formatCellValue(cell);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
		return value;
	}
}
package com.ebayAutomation.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import static com.ebayAutomation.base.BaseClass.testDataExcelFileName;

public class ExcelUtil {
	public static final String currentDir = System.getProperty("user.dir");

	// Location of Test data excel file
	public static String testDataExcelPath = null;

	// Excel WorkBook
	private static XSSFWorkbook excelWBook;

	// Excel Sheet
	private static XSSFSheet excelWSheet;

	// Excel cell
	private static XSSFCell cell;

	// Excel row
	private static XSSFRow row;

	// Row Number
	public static int rowNumber;

	// Column Number
	public static int columnNumber;

	// Setter and Getters of row and columns
	public static void setRowNumber(int pRowNumber) {
		rowNumber = pRowNumber;
	}

	public static int getRowNumber() {
		return rowNumber;
	}

	public static void setColumnNumber(int pColumnNumber) {
		columnNumber = pColumnNumber;
	}

	public static int getColumnNumber() {
		return columnNumber;
	}

	/**
	 * This method has two parameters: "Test data excel file name" and "Excel sheet
	 * name"
	 * 
	 * @param sheetName
	 * @throws Exception
	 */
	// It creates FileInputStream and set excel file and excel sheet to excelWBook
	// and excelWSheet variables.
	public static void setExcelFileSheet(String sheetName) throws Exception {
		// MAC or Windows Selection for excel path
		if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
			testDataExcelPath = currentDir + "//src//test//resources//";
		} else if (Platform.getCurrent().toString().contains("WIN")) {
			testDataExcelPath = currentDir + "\\src\\test\\resources\\";
		}
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * This method reads the test data from the Excel cell.
	 * 
	 * @param RowNum
	 * @param ColNum
	 * @return
	 * @throws Exception
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			cell = excelWSheet.getRow(RowNum).getCell(ColNum);

			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			Log.info(cellData);
			return cellData;
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * This method takes row number as a parameter and returns the data of given row
	 * number.
	 * 
	 * @param RowNum
	 * @return
	 * @throws Exception
	 */
	public static XSSFRow getRowData(int RowNum) throws Exception {
		try {
			row = excelWSheet.getRow(RowNum);
			return row;
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method returns the row number where the @param text is present
	 * 
	 * @param text
	 * @return
	 */
	public static int readexcel(String text) {
		int rn = 0;
		Iterator<Row> rowIterator = excelWSheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				DataFormatter formatter = new DataFormatter();
				String cellData = formatter.formatCellValue(cell);
				if (cellData.equalsIgnoreCase(text))
					rn = cell.getRowIndex();
				else
					continue;
			}

		}
		return rn;
	}

	/**
	 * This method gets excel file, row and column number and set a value to the
	 * that cell.
	 * 
	 * @param value
	 * @param RowNum
	 * @param ColNum
	 * @throws Exception
	 */
	public static void setCellData(String value, int RowNum, int ColNum) throws Exception {
		try {
			row = excelWSheet.getRow(RowNum);
			cell = row.getCell(ColNum);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
			excelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}

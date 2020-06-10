package com.qa.hubspot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.hubspot.base.BasePage;

public class ExcelUtil extends BasePage {
	
	public static String TESTDATA_SHEET_PATH = "D:\\Selenium Project\\MyPOMSeries\\src\\main\\java\\com\\qa\\hubspot\\testdata\\hubspot_testdata.xlsx";
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		
		try {
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0; i<sheet.getLastRowNum(); i++) {
				for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			return data;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
}

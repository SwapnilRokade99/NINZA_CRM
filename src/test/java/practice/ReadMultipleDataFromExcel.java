package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import genaricUtilities.ExcelFileUtility;

public class ReadMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		/*FileInputStream fis=new FileInputStream("C:\\Users\\Swapnil Rokade\\Downloads\\Ninza_CRM.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("practice");*/
		
		ExcelFileUtility eLIB=new ExcelFileUtility();
		int rowCount = eLIB.getRowCount("practice");
		for(int row=1; row<=rowCount; row++) {
			//String Data = sh.getRow(row).getCell(0).getStringCellValue();
			String Data =eLIB.readDataFromExcelFile("practice", row, 0);
			System.out.println(Data);
		}

	}

}

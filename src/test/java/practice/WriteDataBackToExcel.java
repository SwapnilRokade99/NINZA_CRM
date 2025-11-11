package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBackToExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("C:\\\\Users\\\\Swapnil Rokade\\\\Downloads\\\\Ninza_CRM.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("practice");
		Row r = sh.getRow(1);
		Cell c = r.createCell(1, CellType.STRING);
		c.setCellValue("NIL");
		
		FileOutputStream fio=new FileOutputStream("C:\\\\Users\\\\Swapnil Rokade\\\\Downloads\\\\Ninza_CRM.xlsx");
		wb.write(fio);
		wb.close();
		
		

	}

}

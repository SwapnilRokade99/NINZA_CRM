package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//create java representation object of physical file
		FileInputStream fis=new FileInputStream("C:\\Users\\Swapnil Rokade\\Downloads\\Ninza_CRM.xlsx");
		
		//open excel in read mode
		Workbook wb=WorkbookFactory.create(fis);
		
		//get control of sheet
		Sheet sh = wb.getSheet("campaigns");
		
		//get control off row
		Row r = sh.getRow(1);
		
		//get control of cell
		Cell c = r.getCell(2);
		
		//read the data
		String CampaignName = c.getStringCellValue();
		System.out.println(CampaignName);
		
		//reading data with method chaining
		String targetSize = wb.getSheet("campaigns").getRow(1).getCell(3).getStringCellValue();
		System.out.println(targetSize);
		
		//Cell name = wb.getSheet("campaigns").getRow(4).getCell(2);
		//name.setCellValue("swapnil");
		
		
		//close the workbook
		wb.close();
		

	}

}

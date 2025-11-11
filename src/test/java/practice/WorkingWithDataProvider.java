package practice;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genaricUtilities.ExcelFileUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class WorkingWithDataProvider {
	
	@Test(dataProvider = "LoginDetails")
	public void Login(String un,String pw) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("http://49.249.28.218:8098/",un ,pw );
		
		HomePage hp=new HomePage(driver);
		hp.logout();
		driver.quit();
		
	}
	
	@DataProvider
	public Object [][] LoginDetails() throws EncryptedDocumentException, IOException{
		
		Object[][] ObjArr=new Object[4][2];
		/*
		 * ObjArr[0][0]="rmgyantra"; ObjArr[0][1]="rmgy@9999"; ObjArr[1][0]="rmgyantra";
		 * ObjArr[1][1]="rmgy@9999"; ObjArr[2][0]="rmgyantra"; ObjArr[2][1]="rmgy@9999";
		 * ObjArr[3][0]="rmgyantra"; ObjArr[3][1]="rmgy@9999";
		 */
		
		ExcelFileUtility elib=new ExcelFileUtility();
		
		for(int i=1;i<=elib.getRowCount("DataProvider");i++) {
			ObjArr[i-1][0]=elib.readDataFromExcelFile("DataProvider", i, 0);
			ObjArr[i-1][1]=elib.readDataFromExcelFile("DataProvider", i, 1);
			
		}
		return ObjArr;
		
	}

}

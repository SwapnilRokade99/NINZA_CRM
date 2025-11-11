package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genaricUtilities.PropertyFileUtility;
import genaricUtilities.WebDriverUtility;
import objectrepository.ProductsPage;

public class CreateProductMobile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/* FileInputStream fis=new FileInputStream("C:\\Users\\Swapnil Rokade\\Downloads\\commondata.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("un");
		String PWD = prop.getProperty("pwd"); */
		
		PropertyFileUtility pLIB=new PropertyFileUtility();
		String BROWSER=pLIB.readDataFromPropertyFile("browser");
		String URL=pLIB.readDataFromPropertyFile("url");
		String UN=pLIB.readDataFromPropertyFile("un");
		String PWD=pLIB.readDataFromPropertyFile("pwd");
		WebDriverUtility wLIB= new WebDriverUtility();

		
		FileInputStream fis1=new FileInputStream("C:\\Users\\Swapnil Rokade\\Downloads\\Ninza_CRM.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		
		String PRODUCT_NAME = wb.getSheet("product").getRow(2).getCell(2).getStringCellValue();
		String CATEGORY_DD = wb.getSheet("product").getRow(2).getCell(3).getStringCellValue();
		String QUANTITY = wb.getSheet("product").getRow(2).getCell(4).getStringCellValue();
		String PRICE_PER_UNIT = wb.getSheet("product").getRow(2).getCell(5).getStringCellValue();
		String VENDOR_ID_DD = wb.getSheet("product").getRow(2).getCell(6).getStringCellValue();
		wb.close();
		
		ChromeOptions settings = new ChromeOptions(); 
		Map<String, Object> prefs = new HashMap<>(); 
		prefs.put("profile.password_manager_leak_detection", false); 
		settings.setExperimentalOption("prefs", prefs);
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else if(BROWSER.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver(settings);
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(BROWSER.equalsIgnoreCase("safari"))
			driver=new SafariDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wLIB.implicitWait(driver);

		
		
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		ProductsPage productsPage=new ProductsPage(driver);
		productsPage.getProductBtn().click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		
		driver.findElement(By.name("productName")).sendKeys(PRODUCT_NAME);
		driver.findElement(By.xpath("//option[text()='Electronics']")).click();
		WebElement QTT = driver.findElement(By.name("quantity"));
		QTT.clear();
		QTT.sendKeys(QUANTITY);
		
		WebElement PR = driver.findElement(By.name("price"));
		PR.clear();
		PR.sendKeys(PRICE_PER_UNIT);
		
		driver.findElement(By.xpath("//option[text()='Vendor_25991 - (Electronics)']")).click();
		
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.visibilityOf(toastMsg));
		wLIB.waituntilElementToVisible(driver, toastMsg);
		
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		if(msg.contains("Successfully Added"))
			System.out.println("product Created");
		else
			System.out.println("product not created");
		
		
		driver.findElement(By.xpath("//button[@aria-label = 'close']")).click();
		
		WebElement userIcon =  driver.findElement(By.className("user-icon"));
		Actions action = new Actions(driver);
		action.moveToElement(userIcon).perform();
		WebElement logoutBtn= driver.findElement(By.xpath("//div[text()='Logout ']"));
		action.moveToElement(logoutBtn).click().perform();
		
		driver.quit();

	}

}

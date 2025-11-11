package practice;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genaricUtilities.ExcelFileUtility;
import genaricUtilities.JavaUtility;
import genaricUtilities.PropertyFileUtility;
import genaricUtilities.WebDriverUtility;
import objectrepository.AddProductPage;
import objectrepository.HomePage;
import objectrepository.ProductsPage;

public class CreateProductLaptop {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PropertyFileUtility fil=new PropertyFileUtility();
		String BROWSER = fil.readDataFromPropertyFile("browser");
		String URL = fil.readDataFromPropertyFile("url");
		String UN = fil.readDataFromPropertyFile("un");
		String PWD = fil.readDataFromPropertyFile("pwd");
		
		JavaUtility JLib=new JavaUtility();
		WebDriverUtility wLIB= new WebDriverUtility();
		
		ExcelFileUtility efil=new ExcelFileUtility();
		String PRODUCT_NAME = efil.readDataFromExcelFile("product", 2, 2);
		String CATEGORY_DD = efil.readDataFromExcelFile("product", 2, 3);
		String QUANTITY = efil.readDataFromExcelFile("product", 2, 4);
		String PRICE_PER_UNIT = efil.readDataFromExcelFile("product", 2, 5);
		String VENDER_ID = efil.readDataFromExcelFile("product", 2, 6);
		
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if(BROWSER.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver(settings);
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(BROWSER.equalsIgnoreCase("safari"))
			driver = new SafariDriver();
		
		driver.manage().window().maximize();
		wLIB.implicitWait(driver);
		
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		HomePage hp=new HomePage(driver);
		hp.getProductsLink().click();
		ProductsPage productsPage=new ProductsPage(driver);
		productsPage.getProductBtn().click();
		
		AddProductPage addProductPage=new AddProductPage(driver);
		addProductPage.getProductNameTF().sendKeys(PRODUCT_NAME);
		
		addProductPage.getProductNameTF().sendKeys(PRODUCT_NAME + JLib.generateRandomNumber());
		
		WebElement CATEGORY_DROP = addProductPage.getProductCategoryDD();
		wLIB.select(CATEGORY_DROP, CATEGORY_DD);
		
		WebElement QTT = addProductPage.getQuantityTF();
		QTT.clear();
		QTT.sendKeys(QUANTITY);
		
		WebElement PPU = addProductPage.getPricePerUnitTF();
		PPU.clear();
		PPU.sendKeys(PRICE_PER_UNIT);
		
		WebElement VI = addProductPage.getVendorIdDD();
		Select VENDOR_ID_DD= new Select(VI);
		VENDOR_ID_DD.selectByValue(VENDER_ID);
		addProductPage.getAddBtn().click();
		
		HomePage homepage= new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLIB.waituntilElementToVisible(driver, toastMsg);
		
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		if(msg.contains("Successfully Added"))
			System.out.println("product created");
		else
			System.out.println("product not created");
		
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
		homepage.getToastMsg().click();
		homepage.logout();

		driver.quit();
		
		
		

	}

}

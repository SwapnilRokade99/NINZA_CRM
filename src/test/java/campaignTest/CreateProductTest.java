package campaignTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.collections.OneToTwoDimArrayIterator;

import genaricUtilities.BaseClass;
import genaricUtilities.ExcelFileUtility;
import genaricUtilities.JavaUtility;
import genaricUtilities.PropertyFileUtility;
import genaricUtilities.WebDriverUtility;
import objectrepository.AddProductPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.ProductsPage;

public class CreateProductTest extends BaseClass{
	
	@Test
	public void createProductLaptop() throws IOException {
		
		
		String PRODUCT_NAME = ELib.readDataFromExcelFile("product", 2, 2);
		String CATEGORY_DD = ELib.readDataFromExcelFile("product", 2, 3);
		String QUANTITY = ELib.readDataFromExcelFile("product", 2, 4);
		String PRICE_PER_UNIT = ELib.readDataFromExcelFile("product", 2, 5);
		String VENDER_ID = ELib.readDataFromExcelFile("product", 2, 6);
		
		
		HomePage hp=new HomePage(driver);
		hp.getProductsLink().click();
		ProductsPage productsPage=new ProductsPage(driver);
		productsPage.getProductBtn().click();
		
		AddProductPage addProductPage=new AddProductPage(driver);
		addProductPage.getProductNameTF().sendKeys(PRODUCT_NAME);
		
		addProductPage.getProductNameTF().sendKeys(PRODUCT_NAME + JLib.generateRandomNumber());
		
		WebElement CATEGORY_DROP = addProductPage.getProductCategoryDD();
		WLib.select(CATEGORY_DROP, CATEGORY_DD);
		
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
		WLib.waituntilElementToVisible(driver, toastMsg);
		
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		/*
		 * if(msg.contains("Successfully Added")) System.out.println("product created");
		 * else System.out.println("product not created");
		 * 
		 * driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		 */
		
		homepage.getToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));

	}
	
	@Test
	public void CreateProduct() throws IOException{
		PropertyFileUtility fil=new PropertyFileUtility();
		String BROWSER = fil.readDataFromPropertyFile("browser");
		String URL = fil.readDataFromPropertyFile("url");
		String UN = fil.readDataFromPropertyFile("un");
		String PWD = fil.readDataFromPropertyFile("pwd");
		
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
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
			driver =new EdgeDriver();
		else if(BROWSER.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver(settings);
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(BROWSER.equalsIgnoreCase("safari"))
			driver=new SafariDriver();
		
		driver.manage().window().maximize();
		wlib.implicitWait(driver);
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(URL, UN, PWD);
		
		HomePage hp=new HomePage(driver);
		hp.getProductsLink().click();
		ProductsPage productsPage=new ProductsPage(driver);
		productsPage.getProductBtn().click();
		
		AddProductPage addProductPage=new AddProductPage(driver);
		addProductPage.getAddBtn().click();
		addProductPage.getProductNameTF().sendKeys(PRODUCT_NAME);
		
		addProductPage.getProductNameTF().sendKeys(PRODUCT_NAME + jlib.generateRandomNumber());
		
		WebElement CAT = addProductPage.getProductCategoryDD();
		wlib.select(CAT, CATEGORY_DD);
		
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
		wlib.waituntilElementToVisible(driver, toastMsg);
		
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		/*
		 * if(msg.contains("Successfully Added")) System.out.println("product created");
		 * else System.out.println("product not created");
		 * 
		 * driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		 */
		
		homepage.getToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));
		homepage.logout();

		driver.quit();
		
		
	}

}

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

import genaricUtilities.ExcelFileUtility;
import genaricUtilities.PropertyFileUtility;
import genaricUtilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithExpectedCloseDateReadDataUsingExcel {

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
		
		
		ExcelFileUtility eLIB=new ExcelFileUtility();
		String CAMPAIGN_NAME = eLIB.readDataFromExcelFile("Campaigns", 7, 2);
		String TARGET_SIZE1 = eLIB.readDataFromExcelFile("Campaigns", 7, 3);
		String EXPECTED_CLOSE_DATA = eLIB.readDataFromExcelFile("Campaigns", 7, 5);
		
		ChromeOptions settings = new ChromeOptions(); 
		Map<String, Object> prefs = new HashMap<>(); 
		prefs.put("profile.password_manager_leak_detection", false); 
		settings.setExperimentalOption("prefs", prefs);
		
		
		//launch browser
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
		
		
		//login
		/*
		 * driver.get(URL); driver.findElement(By.id("username")).sendKeys(UN);
		 * driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		 * driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		 */
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(URL, UN, PWD);
		
		//create campaign
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignButton().click();
		
		CreateCampaignPage createCampaignPage =new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getExpectedCloseDateTF().sendKeys(EXPECTED_CLOSE_DATA);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE1);
		createCampaignPage.getCreateCampaignBtn().click();

		
		//verify toast msg
		HomePage homepage= new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLIB.waituntilElementToVisible(driver, toastMsg);
		
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		if(msg.contains("Successfully Added"))
			System.out.println("Campaign Created");
		else
			System.out.println("campaign not created");
		
		homepage.getToastMsg().click();
		homepage.logout();
		
		/*
		 * driver.findElement(By.xpath("//button[@aria-label = 'close']")).click();
		 * 
		 * WebElement userIcon = driver.findElement(By.className("user-icon")); Actions
		 * action = new Actions(driver); action.moveToElement(userIcon).perform();
		 * WebElement logoutBtn=
		 * driver.findElement(By.xpath("//div[text()='Logout ']"));
		 * action.moveToElement(logoutBtn).click().perform();
		 */
		driver.quit();


	}

}

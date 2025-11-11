package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

import genaricUtilities.BaseClass;
import genaricUtilities.ExcelFileUtility;
import genaricUtilities.PropertyFileUtility;
import genaricUtilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithStatus extends BaseClass{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		PropertyFileUtility pLIB=new PropertyFileUtility();
		String BROWSER=pLIB.readDataFromPropertyFile("browser");
		String URL=pLIB.readDataFromPropertyFile("url");
		String UN=pLIB.readDataFromPropertyFile("un");
		String PWD=pLIB.readDataFromPropertyFile("pwd");
		WebDriverUtility wLIB= new WebDriverUtility();
		
	
		
		ExcelFileUtility eLIB=new ExcelFileUtility();
		String CAMPAIGN_NAME = eLIB.readDataFromExcelFile("Campaigns", 7, 2);
		String TARGET_SIZE1 = eLIB.readDataFromExcelFile("Campaigns", 7, 3);
		String STATUS = eLIB.readDataFromExcelFile("Campaigns", 7, 4);

		
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
		wLIB.implicitWait(driver);


		//login
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(URL, UN, PWD);
		
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignButton().click();
		
		CreateCampaignPage createCampaignPage=new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE1);
		createCampaignPage.getCampaignStatusTF().sendKeys(STATUS);		
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
		
		driver.quit();

	}

}

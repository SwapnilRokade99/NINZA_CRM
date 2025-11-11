package campaignTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genaricUtilities.BaseClass;
import genaricUtilities.ExcelFileUtility;
import genaricUtilities.PropertyFileUtility;
import genaricUtilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

@Listeners(genaricUtilities.ListenerImplimentation.class)

public class CreateCampaignTest extends BaseClass{
	@Test(groups = {"smoke","regression"})
	public void createCampaignWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException {
		
		
		String CAMPAIGN_NAME = ELib.readDataFromExcelFile("Campaigns", 1, 2);
		String TARGET_SIZE = ELib.readDataFromExcelFile("Campaigns", 1, 3);
		
		
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignButton().click();
		
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();
		
		HomePage homepage= new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		WLib.waituntilElementToVisible(driver, toastMsg);
		
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		/*
		 * if(msg.contains("Successfully Added"))
		 * System.out.println("Campaign Created"); else
		 * System.out.println("campaign not created");
		 */
		
		homepage.getToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));
			
}
	
	@Test(groups = "regression")
	public void createCampaignWithStatusTest() throws EncryptedDocumentException, IOException {
		
		String CAMPAIGN_NAME = ELib.readDataFromExcelFile("Campaigns", 7, 2);
		String TARGET_SIZE1 = ELib.readDataFromExcelFile("Campaigns", 7, 3);
		String STATUS = ELib.readDataFromExcelFile("Campaigns", 7, 4);

		
		
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignButton().click();
		
		CreateCampaignPage createCampaignPage=new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE1);
		createCampaignPage.getCampaignStatusTF().sendKeys(STATUS);		
		createCampaignPage.getCreateCampaignBtn().click();
		
		HomePage homepage= new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		WLib.waituntilElementToVisible(driver, toastMsg);
		
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		/*
		 * if(msg.contains("Successfully Added"))
		 * System.out.println("Campaign Created"); else
		 * System.out.println("campaign not created");
		 */
		
		homepage.getToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));
		System.out.println("hello");
		System.out.println("new add");

		
	}
	
	@Test(groups = "regression")
	public void createCampaignWithExpectedCloseDateTest() throws EncryptedDocumentException, IOException {
		
		String CAMPAIGN_NAME = ELib.readDataFromExcelFile("Campaigns", 7, 2);
		String TARGET_SIZE1 = ELib.readDataFromExcelFile("Campaigns", 7, 3);
		String EXPECTED_CLOSE_DATA = ELib.readDataFromExcelFile("Campaigns", 7, 5);
		
		
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignButton().click();
		
		CreateCampaignPage createCampaignPage =new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getExpectedCloseDateTF().sendKeys(EXPECTED_CLOSE_DATA);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE1);
		createCampaignPage.getCreateCampaignBtn().click();

		HomePage homepage= new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		WLib.waituntilElementToVisible(driver, toastMsg);
		
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		/*
		 * if(msg.contains("Successfully Added"))
		 * System.out.println("Campaign Created"); else
		 * System.out.println("campaign not created");
		 */
		
		homepage.getToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));


		
	}

}

package campaignTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genaricUtilities.BaseClass;
import genaricUtilities.ExcelFileUtility;
import genaricUtilities.PropertyFileUtility;
import genaricUtilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaigntest1 extends BaseClass{
	
	@Test(groups = {"smoke","regression"})
	
	public void CreateCampaignWithMandetoryFields() throws IOException {
		
		String CAMPAIGN_NAME = ELib.readDataFromExcelFile("Campaigns", 7, 2);
		String CAMPAIGN_STATUS = ELib.readDataFromExcelFile("Campaigns", 7, 3);
		String TARGET_SIZE = ELib.readDataFromExcelFile("Campaigns", 7, 4);
		String EXPECTED_CLOSE_DATE = ELib.readDataFromExcelFile("Campaigns", 7, 5);
		
		
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignButton().click();
		
		CreateCampaignPage campaignPage=new CreateCampaignPage(driver);
		campaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		campaignPage.getCampaignStatusTF().sendKeys(CAMPAIGN_STATUS);
		WebElement TS = campaignPage.getTargetSizeTF();
		TS.clear();
		TS.sendKeys(TARGET_SIZE);
		
		campaignPage.getExpectedCloseDateTF().sendKeys(EXPECTED_CLOSE_DATE);
		campaignPage.getCreateCampaignBtn().click();
		
		HomePage homepage= new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		WLib.waituntilElementToVisible(driver, toastMsg);
		
		String msg = toastMsg.getText();
		System.out.println(msg);
		/*
		 * if(msg.contains("Successfully Added"))
		 * System.out.println("Campaign Created"); else
		 * System.out.println("Campaign Not Created");
		 */
		
		homepage.getCloseToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));

		
		
	}

}

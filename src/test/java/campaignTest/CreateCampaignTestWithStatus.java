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

public class CreateCampaignTestWithStatus extends BaseClass{
	
	@Test
	
	public void CreateCampaignTestWithStatus() throws IOException {
		
		
		String CAMPAIGN_NAME = ELib.readDataFromExcelFile("Campaigns", 7, 2);
		String CAMPAIGN_TARGET_SIZE = ELib.readDataFromExcelFile("Campaigns", 7, 3);
		String CAMPAIGN_STATUS = ELib.readDataFromExcelFile("Campaigns", 7, 3);
		
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignButton().click();
		
		CreateCampaignPage campaignPage=new CreateCampaignPage(driver);
		campaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		campaignPage.getCampaignStatusTF().sendKeys(CAMPAIGN_STATUS);
		WebElement TARGET = campaignPage.getTargetSizeTF();
		TARGET.clear();
		TARGET.sendKeys(CAMPAIGN_TARGET_SIZE);
		
		campaignPage.getCreateCampaignBtn().click();
		
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

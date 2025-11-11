package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class Demo {
	
	@Test //(priority = -200, invocationCount = 5,threadPoolSize = 2)
	public void ProductCreation() {
		Reporter.log("ProductCreation",true);
		/* WebDriver driver =new ChromeDriver(); driver.close(); */
		
	}
	@Test (dependsOnMethods = "ProductCreation")//(invocationCount = 2,enabled = false)
	public void UpdateProduct() {
		Reporter.log("UpdateProduct",true);
	}
	
	
	@Test (dependsOnMethods = {"ProductCreation","UpdateProduct"})//(priority = 1, invocationCount = 0)
	public void DeleteProduct() {
		Reporter.log("DeleteProduct",true);
	}
	
	/*
	 * public void abc() { Reporter.log("abc",true); }
	 * 
	 * public void ABC() { Reporter.log("ABC",true); }
	 * 
	 * public void ABc() { Reporter.log("ABc",true); }
	 * 
	 * public void Abc() { Reporter.log("Abc",true); }
	 */

}

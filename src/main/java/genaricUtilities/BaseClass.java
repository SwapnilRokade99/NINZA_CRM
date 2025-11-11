package genaricUtilities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectrepository.HomePage;
import objectrepository.LoginPage;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	public WebDriver driver = null;
	public PropertyFileUtility pLib = new PropertyFileUtility();
	public WebDriverUtility WLib = new WebDriverUtility();
	public JavaUtility JLib = new JavaUtility();
	public ExcelFileUtility ELib = new ExcelFileUtility();
	public static WebDriver sdriver = null;

	@BeforeMethod(groups = { "smoke", "regression" })
	public void beforeMethod() throws IOException {
		System.out.println("Login");

		String URL = pLib.readDataFromPropertyFile("url");
		String UN = pLib.readDataFromPropertyFile("un");
		String PWD = pLib.readDataFromPropertyFile("pwd");

		/*
		 * String URL=System.getProperty("url"); String UN=System.getProperty("un");
		 * String PWD=System.getProperty("pwd");
		 */

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(URL, UN, PWD);

	}

	@AfterMethod(groups = { "smoke", "regression" })
	public void afterMethod() {
		System.out.println("Logout");

		HomePage homepage = new HomePage(driver);
		homepage.logout();
	}

	// @Parameters("browser")
	@BeforeClass(groups = { "smoke", "regression" })
	public void beforeClass() throws IOException {
		System.out.println("launch browser");
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		String BROWSER = pLib.readDataFromPropertyFile("browser");
		// String BROWSER=System.getProperty("browser");

		if (BROWSER.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver(settings);
		else if (BROWSER.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("safari"))
			driver = new SafariDriver();

		sdriver = driver;
		driver.manage().window().maximize();
		WLib.implicitWait(driver);

	}

	@AfterClass(groups = { "smoke", "regression" })
	public void afterClass() {
		System.out.println("Close Browser");

		driver.quit();
	}

	@BeforeTest(groups = { "smoke", "regression" })
	public void beforeTest() {
		System.out.println("Pre-Condition Parallel execution");
	}

	@AfterTest(groups = { "smoke", "regression" })
	public void afterTest() {
		System.out.println("Post-Condition Parallel execution");
	}

	@BeforeSuite(groups = { "smoke", "regression" })
	public void beforeSuite() {
		System.out.println("Establish the Database connections");
	}

	@AfterSuite(groups = { "smoke", "regression" })
	public void afterSuite() {
		System.out.println("Close the Database Connection");
	}

}

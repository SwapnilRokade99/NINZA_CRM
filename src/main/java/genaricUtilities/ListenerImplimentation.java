package genaricUtilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplimentation implements ITestListener,ISuiteListener {

	ExtentReports reports;
	ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		JavaUtility jLib=new JavaUtility();
		System.out.println("Report Configuration");
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReport/"+jLib.getCurrentDateAndTime()+"report.html");
		spark.config().setDocumentTitle("CRM Reports");
		spark.config().setReportName("Ninza CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		reports=new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", "Windows");
		reports.setSystemInfo("Browser", "chrome");
			
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Configuration");
		reports.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testCasename = result.getMethod().getMethodName();
		//System.out.println(testCasename+" Report Backup");
		test=reports.createTest(testCasename);
		test.log(Status.INFO, testCasename+"execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCasename = result.getMethod().getMethodName();
		//System.out.println(testCasename+" Test Execution success");	
		test.log(Status.PASS, testCasename+"execution success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCasename = result.getMethod().getMethodName();
		test.log(Status.FAIL, testCasename+"execution success");
		System.out.println(testCasename+" Test Execution failed");
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
		
		
		/*
		 * File dest = new
		 * File("./ErrorSS/"+testCasename+"_"+jLib.getCurrentDateAndTime()+".png"); try
		 * { FileHandler.copy(src, dest); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCasename = result.getMethod().getMethodName();
		//System.out.println(testCasename+" Test Execution skipped");
		test.log(Status.SKIP, testCasename+"execution success");
	}
	

}

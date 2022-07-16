package com.orangeHRM.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangeHRM.utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readConfig=new ReadConfig();

	public static WebDriver driver;
	public String URL=readConfig.getApplicationURL();
	public String userName=readConfig.getUserName();
	public String password=readConfig.getPass();

	@Parameters("browser")
	@BeforeMethod
	public void setup(String br) {

		if(br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		} 
		else if(br.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		} 
		else if(br.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		} 
		else {
			System.out.println("Enter a valid browser");
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(URL);
	}
	
	
//	@AfterMethod
//	public void tearDown() {
//		driver.quit();
//	}
	
	public ExtentReports extent;
	public ExtentSparkReporter spark;
	public ExtentTest test;
	public static String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


	@BeforeTest
	public void setExtent() {
		String repName="Test-Report-"+timestamp+".html";
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);

		spark.config().setDocumentTitle("Automation Testing");
		spark.config().setReportName("Functional Testing");
		spark.config().setTheme(Theme.DARK);

		extent=new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "Local Host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Author", "Nana");
		extent.setSystemInfo("Test", "SmokeTest");
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case pass is "+result.getName());
		} else if (result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test case skipped is "+result.getName());
		} else if (result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test case failed is "+result.getName());
			test.log(Status.FAIL, "Test case failed error is "+result.getThrowable());

			String screenshotPath=BaseClass.getScreenshot(driver,result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		}
		driver.quit();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destinationPath=new File(System.getProperty("user.dir")+"/screenshots/"+screenshotName+timestamp+".png");
		String absolutePath=destinationPath.getAbsolutePath();
		FileUtils.copyFile(source, destinationPath);
		return absolutePath;
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	
}

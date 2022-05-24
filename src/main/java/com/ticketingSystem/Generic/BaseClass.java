package com.ticketingSystem.Generic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.LogStatus;
import com.ticketingSystem.Generic.functions.propertyFile;
import com.ticketingSystem.Generic.functions.screenShot;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends functions  {

	public String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss").format(new java.util.Date());
	protected String currentDir = System.getProperty("user.dir");

	functions c = new functions();
	
	public functions.propertyFile p = c.new propertyFile();
	public functions.webDriverUtilities utilities = c.new webDriverUtilities();
	public loginLogout ll = new loginLogout();
	public functions.screenShot ss = c.new screenShot();

	static public WebDriver driver;

	@BeforeTest
	@org.testng.annotations.Parameters("browser")
	public void launchBrowser(@Optional("chrome") String browser) throws Exception
	{		
		String repName = "Test-Automation-Report_" + timestamp + ".html";

		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(currentDir+ repoPath + repName);
		htmlReporter.config().setDocumentTitle("Automation Testing"); 
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		extent.attachReporter(htmlReporter);  //Extend report in HTML format will be generated.

		//extent.addSystemInfo("Environment","Environment Name")
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");

		extent.setSystemInfo("Environment", "Automation Testing");
		//extent.setSystemInfo("User Name", "Rajkumar SM");
		extent.setSystemInfo("QA Name", "Amreen");
		extent.setSystemInfo("HostName", "Localhost");
		extent.setSystemInfo("OS", "Windows");

		createTest("Amreen",""); 

		if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
			test.log(Status.INFO, "Firefox browser launched");
		}

		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			test.log(Status.INFO, "Chrome browser launched");
		}

		//Check if parameter passed as 'Edge'
		else if(browser.equalsIgnoreCase("Edge")){
			//set path to Edge.exe
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			test.log(Status.INFO, "EdgeDriver browser launched");
		}

		else
		{

			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
			//WebDriverManager.chromedriver().setup();

		}

		WebDriverManager.chromedriver().setup();
		Thread.sleep(3000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS); 
		String url = p.toReadDataFromPropertyFile("URL");
		driver.get(url);
		test.info("Working On : "+url+" URL.");

		Thread.sleep(3000); 
	}


	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, Exception 
	{

		Thread.sleep(3000); 

		if (result.getStatus() == ITestResult.FAILURE) 
		{
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable().getMessage()); // to add error/exception in extent report

			test.fail("", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentAshot(driver,this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName())).build());
			test.fail(new RuntimeException(result.getThrowable().getMessage()));

		} 

		else if (result.getStatus() == ITestResult.SKIP)
		{
			test.skip(new RuntimeException(result.getThrowable().getMessage()));
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
			test.skip("", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentAshot(driver,this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName())).build());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) 
		{
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
			test.pass("", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentAshot(driver,this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName())).build());
		} 
	}

	
	@AfterClass
	public void closeApp() throws InterruptedException, Exception
	{
		ll.logoutPage(driver);
	}

	@AfterTest
	public void closeBrowser() throws Exception 
	{
		//	ll.logoutPage(driver);
		//	driver.close();
		extent.flush();

	}
	

	@AfterClass
	public void endReport() throws IOException, InterruptedException
	{
		//driver.quit();
		Thread.sleep(3000);
	}

	public ExtentTest createTest(String authName, String category)
	{
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = this.getClass().getSimpleName();
		test=extent.createTest(className + " :: "+methodName);	
		test.assignAuthor(authName);
		test.assignCategory(category);
		return test;
	}

	public void onTestSuccess(String msg) throws IOException 
	{
		//test.log(Status.PASS, msg);
		test.log(Status.PASS, MarkupHelper.createLabel(msg, ExtentColor.GREEN));
		test.pass("", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentScreenShot(driver,this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName())).build());
	}

	public void onTestFailure(String msg) throws IOException 
	{ 
		//test.log(Status.FAIL, msg);
		test.log(Status.FAIL, MarkupHelper.createLabel(msg, ExtentColor.RED));
		test.fail("", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentScreenShot(driver,this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName())).build());
	}

	public void onTestSkipped(String msg) throws IOException
	{	
		//test.log(Status.SKIP, msg);
		//	test.log(Status.SKIP, tr.getThrowable());		
		test.log(Status.SKIP, MarkupHelper.createLabel(msg, ExtentColor.AMBER));
		test.skip("", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentScreenShot(driver,this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName())).build());
	}

	public class loginLogout 
	{
		public void loginPageWithDefaultValues(WebDriver driver) throws InterruptedException, Exception, IOException
		{
			createTest("Amreen","");

			driver.findElement(By.id("email")).sendKeys(p.toReadDataFromPropertyFile("Uname"));
			Thread.sleep(3000);

			driver.findElement(By.id("password")).sendKeys(p.toReadDataFromPropertyFile("pwd"));
			Thread.sleep(3000);

			driver.findElement(By.id("login")).click();
			Thread.sleep(3000);

			onTestSuccess("Logged in successfully");
		}

		public void loginPageWithPArameters(WebDriver driver, String uname, String password) throws InterruptedException, Exception
		{		

			createTest("Amreen","");
			driver.findElement(By.id("email")).sendKeys(uname);
			Thread.sleep(1000);

			driver.findElement(By.id("password")).sendKeys(password);
			Thread.sleep(1000);

			driver.findElement(By.id("login")).click();
			Thread.sleep(3000);
			onTestSuccess("Logged in successfully");

		}

		public void logoutPage(WebDriver driver) throws InterruptedException, Exception {

			createTest("Amreen","");

			driver.findElement(By.xpath("(//img[@src='http://15.207.120.175/NewTicketService/storage/app/Profile/Ashot_dailyStockAdd_dateBranchStock_2022_03_02_06_34_50.jpg'])[1]")).click();
			Thread.sleep(3000);

			driver.findElement(By.xpath("//button[text()='Signout']")).click();
			Thread.sleep(3000);

			onTestSuccess("Logged Out successfully");

		} 
		
	}
	
}
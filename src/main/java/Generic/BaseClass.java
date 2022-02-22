package Generic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends functions {

	//public PropertyFile p=new PropertyFile();

	functions c = new functions();
	
	//public functions.propertyFile p1= c.new propertyFile();
	
	public functions.propertyFile p = c.new propertyFile();
	public functions.webDriverUtilities utilities = c.new webDriverUtilities();
	public functions.AutoConstant autoConstant = c.new AutoConstant();
	public functions.loginLogout ll = c.new loginLogout();
	public functions.screenShot ss = c.new screenShot();

	
	public WebDriver driver;

	//public WebDriverUtilities utilities=new WebDriverUtilities();

	static ExtentTest test;
	static ExtentReports report;
	
	
	//public String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new java.util.Date());
	//dd MMM yyyy HH:mm:ss
	public String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss").format(new java.util.Date());

	@BeforeTest
	@org.testng.annotations.Parameters("browser")
	public void launchBrowser(@Optional("chrome") String browser) throws Exception
	{
//		report = new ExtentReports(System.getProperty("user.dir")+autoConstant.reportPath+"ReportResults"+timestamp+".html");
//		test = report.startTest("FrameWorkDemo");
		if(browser.equalsIgnoreCase("firefox")){
			//create firefox instance
			//	System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}

		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
			//System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");

			WebDriverManager.chromedriver().setup();

			//create chrome instance
			driver = new ChromeDriver();
		}
		
		//Check if parameter passed as 'Edge'
		else if(browser.equalsIgnoreCase("Edge")){
			//set path to Edge.exe
			WebDriverManager.edgedriver().setup();

			//			System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
			//create Edge instance
			driver = new EdgeDriver();
		}
		
		else{

			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
			//WebDriverManager.chromedriver().setup();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverManager.chromedriver().setup();
		//WebDriverManager.firefoxdriver().setup();
		//WebDriverManager.edgedriver().setup();

		//System.setProperty("webdriver.chrome.driver", "./driver.exe");

		//driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS); 
		driver.get(p.toReadDataFromPropertyFile("url"));
		
		Thread.sleep(3000);
		//test.log(LogStatus.PASS,"Test Passed");
		System.out.println(driver.getCurrentUrl());
	}

	@BeforeClass
	public void login() throws FileNotFoundException, IOException, InterruptedException
	{
		report = new ExtentReports(System.getProperty("user.dir")+autoConstant.reportPath+"ReportResults"+timestamp+".html");
		test = report.startTest("FrameWorkDemo"+timestamp);
		if(driver.getCurrentUrl().equalsIgnoreCase("http://10.2.11.23/connectus-dummy/admin/login"))
		{
			test.log(LogStatus.PASS, "Navigated to the specified URL");
			
		}
		else
		{
			test.log(LogStatus.FAIL, "Test Failed");
		}

		String uname = p.toReadDataFromPropertyFile("salesmanUname");
		String pwd = p.toReadDataFromPropertyFile("salesmanPassword");

		ll.loginPage(driver, uname, pwd);

		Thread.sleep(5000);

	}
	
//	@BeforeMethod
	public void openReport()
	{
		report = new ExtentReports(System.getProperty("user.dir")+autoConstant.reportPath+"ReportResults"+timestamp+".html");
		test = report.startTest("FrameWorkDemo"+timestamp);
		if(driver.getCurrentUrl().equalsIgnoreCase("http://10.2.11.23/connectus-dummy/admin/login"))
		{
			test.log(LogStatus.PASS, "Navigated to the specified URL");
			
		}
		else
		{
			test.log(LogStatus.FAIL, "Test Failed");
		}
	}

//	@AfterMethod
	public void closeApp() throws IOException, InterruptedException
	{
		report.endTest(test);
		report.flush();
		
	}

	@AfterClass
	public void endReport() throws IOException, InterruptedException
	{
		
		ll.logoutPage(driver);
		Thread.sleep(3000);
		report.endTest(test);
		report.flush();
	}
	
	@AfterTest
	public void closeBrowser() throws IOException, InterruptedException
	{
		driver.close();
	}
	/*
	 * @AfterMethod
	public void closeApp(ITestResult r) throws IOException
	{
		int status = r.getStatus();
		String name = r.getName();

		/*1=pass test case
	 * 2=fail test case
	 * 3=all test case
	 */

	/*	if(status==1) {
			Reporter.log("TestCase : " + name + " is pass", true);

			Photo p=new Photo();
			p.getPhoto(driver, name);
		}
		else
		{
			Reporter.log("TestCase : " + name + " is failed", true);
			Photo p=new Photo();

			p.getPhoto(driver, name);
		}
		driver.close();
	 */
	/*{
			public void closeBrowser(ITestResult itestresult) throws IOException {
				int status = itestresult.getStatus();
				String name = itestresult.getName();
				if (status == 1) {
					Reporter.log("TestCase : " + name + " is pass", true);
				} else {
					ScreenShot.takePic(driver, name + ssExt, screenShotFolder);
					Reporter.log("TestCase : " + name + " is fail", true);
				}

				driver.close();
			}*/
}

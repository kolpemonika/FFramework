package com.ticketingSystem.Generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import com.ticketingSystem.Generic.functions.propertyFile;
import com.ticketingSystem.Generic.functions.screenShot;

import extendReport.extendReport.Utility;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class functions implements autoConstant {
	public String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss").format(new java.util.Date());
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent = new ExtentReports();
	//public ExtentSparkReporter spark;
	public ExtentTest test;

	WebDriver driver;
	
	//read write data
	public class propertyFile
	{
		//PropertyFile p = new PropertyFile();

		public String toReadDataFromPropertyFile(String key) throws FileNotFoundException, IOException
		{
			Properties ps= new Properties();
			ps.load(new FileInputStream(datafile));
			return ps.getProperty(key);
		}

		//fetch the data from Excel.
		public String toReadDataFromExcel(int sheet, int row, int cell) throws FileNotFoundException, IOException
		{
			XSSFWorkbook wb = new XSSFWorkbook(currentDir+excelPath); 
			XSSFSheet sh = wb.getSheetAt(sheet);

			int noOfRows = sh.getPhysicalNumberOfRows();

			//For Numeric value
			try
			{
				int val = (int) sh.getRow(row).getCell(cell).getNumericCellValue();
				String value = Integer.toString(val);

				return value;
			}
			//For Value value
			catch (Exception e) 
			{
				String value = sh.getRow(row).getCell(cell).getStringCellValue();
				return value;
			}
		}

		public void toWriteDataInExcel(int sheet, int row, int cell,String arg) throws FileNotFoundException, IOException, InterruptedException
		{
			FileOutputStream fos = new FileOutputStream(toReadDataFromPropertyFile("sheet"));
			XSSFWorkbook wb = new XSSFWorkbook(toReadDataFromPropertyFile("sheet")); 
			XSSFSheet sh = wb.getSheetAt(sheet); 
			XSSFCell cellValue=sh.getRow(row).createCell(cell);//row index - 0,column index-2
			cellValue.setCellValue(arg);
			wb.write(fos);
			Thread.sleep(3000);			
		}
	}

	//Utilities
	public class webDriverUtilities
	{
		public void toSelectFromDropDown(WebElement ele, String text)
		{
			Select s=new Select(ele);
			s.selectByVisibleText(text);
			//s.selectByValue(text);
		}

		public void toDeselectAllFromDropDown(WebElement ele)
		{

			Select s=new Select(ele);
			s.deselectAll();
		}

		public void toPerformMouseHoverAction(WebDriver driver, WebElement target)
		{ 
			Actions a =new Actions(driver);
			a.moveToElement(target);
		}

		public void toSwitchToFrame(WebDriver driver, int frameNo)
		{
			driver.switchTo().frame(frameNo);
		}

		public void toSwitchBackToFrame(WebDriver driver)
		{
			driver.switchTo().defaultContent();
		}

		public void toAcceptAlertPopup(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}

		public void toDismissAlertPopup(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}

		public void toSwitchToTabs(WebDriver driver)
		{
			String parent = driver.getWindowHandle();
			Set<String> child = driver.getWindowHandles();
			child.remove(parent);
			for(String b:child)
			{ 
				driver.switchTo().window(b);
			}
		}

		public void toPerformDoubleClick(WebDriver driver, WebElement target)
		{
			Actions a = new Actions(driver);
			a.doubleClick(target).perform();
		}

		public void toPerformScrolling(WebDriver driver, int x, int y)
		{
			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("windows.scrollBy("+x+","+y+")");
		}

		public void toCompareWithString(String actual,String expected) {
			Assert.assertEquals(actual, expected);
		}

		public void toPerformDragDrop(WebDriver driver,WebElement source,WebElement target) {
			Actions a=new Actions(driver);
			a.dragAndDrop(source, target).perform();
		}

	}

	//Screenstot
	public class screenShot {

		public void takeScreenShot(WebDriver driver, String className, String SSName) throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			File dest=new File(currentDir+photo+"SS_"+className+"_"+SSName+"_"+timestamp+".png");
			Files.copy(src, dest);
		}

		public void getAshot(WebDriver driver, String className, String SSName) throws IOException
		{ 
			File fis= new File(currentDir+photo+"Ashot_"+className+"_"+SSName+"_"+timestamp+".png");
			Screenshot sh = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			ImageIO.write(sh.getImage(), "PNG", fis);

		}

		public String extentScreenShot(WebDriver driver,String className, String ssName) throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			String path=currentDir+repoPath+"SS_"+className+"_"+ssName+"_"+timestamp+".png";
			File destination=new File(path);
			Files.copy(src, destination);
			return path;
		}

		public String extentAshot(WebDriver driver,String className, String ssName) throws IOException
		{	
		String path =currentDir+ repoPath +"Ashot_"+className+"_"+ssName+"_"+timestamp+".png";
			File fis= new File(path);
			Screenshot sh = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			ImageIO.write(sh.getImage(), "PNG", fis);
			return path;

		}
	}
}

//Declare extent report
/* public class extentReport extends propertyFile 
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String className = this.getClass().getSimpleName(); 

		screenShot s = new screenShot();
		public void toCreateExtentReport() throws FileNotFoundException, IOException
		{
			ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath+"Report "+timestamp+".html");


			extent = new ExtentReports();

			extent.attachReporter(reporter);
			reporter.config().setReportName(toReadDataFromPropertyFile("reportName"));
			reporter.config().setDocumentTitle(toReadDataFromPropertyFile("docTile"));

		}

		public String toTakeScreenShot() throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot) driver;

			File src=ts.getScreenshotAs(OutputType.FILE);

			String path=System.getProperty("user.dir")+"/Photo/"+System.currentTimeMillis()+".png";

			File dest=new File(path);
			Files.copy(src, dest);
			return path;

		}
		public void toGeneratePassExtentReport(String msg) throws IOException
		{

			//logger=extent.createTest(className);
			logger.log(Status.PASS, msg);

			ITestResult result = null;
			logger.pass(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(toTakeScreenShot()).build());

		}

		public void toGenerateFailExtentReport(String msg) throws IOException
		{


//			/logger=extent.createTest(className);
			logger.log(Status.FAIL, msg);

			logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(toTakeScreenShot()).build());

		}
		public void tearDown(ITestResult result) throws IOException
		{
			System.out.println("Testing result");

			if(result.getStatus()==ITestResult.FAILURE)
			{


				//test.log(LogStatus.PASS, "Navigated to the specified URL");

				logger.log(Status.FAIL, "Navigated Fail");

				logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(toTakeScreenShot()).build());
			}else { 


				//test.log(LogStatus.PASS, "Navigated to the specified URL");

				logger.log(Status.PASS, "Navigated pass");

				logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(toTakeScreenShot()).build());

			}



		}

	}
 */




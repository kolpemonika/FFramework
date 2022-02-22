package Generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

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

import com.google.common.io.Files;

import Generic.CollectionTest.PropertyFile;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class functions {
	public String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss").format(new java.util.Date());
	
	//Declare all constants 
	public class AutoConstant
	{
		public final String photopath="./Photo/";

		public final String datafile="./appData.properties";

		public final String constant= "D:\\Amreen_Shaikh\\Eclipse\\Prototype2\\";
		public final String currentDir = System.getProperty("user.dir");
		public final String photo="\\photo\\";	 
		public final String reportPath="\\ExtentReport\\";
	}

	//Login Logout
	public class loginLogout {
		
		public void loginPage(WebDriver driver, String uname, String password) throws InterruptedException
		{
			driver.findElement(By.name("username")).sendKeys(uname);
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(3000);
		}
		
		public void logoutPage(WebDriver driver) throws InterruptedException {
			
			driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[3]")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//a[@title='Logout']")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//button[@class='confirm']")).click();
			Thread.sleep(3000);
		}

	}

	//read write data
	public class propertyFile extends AutoConstant
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
			XSSFWorkbook wb = new XSSFWorkbook(toReadDataFromPropertyFile("sheet")); 
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

		public void toSwitchToFrame(WebDriver driver, int fameNo)
		{
			driver.switchTo().frame(fameNo);
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

		public void takeScreenShot(WebDriver driver, String constant, String photo, String className, String SSName) throws IOException
		{
			
		//	String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new java.util.Date());
			//Copy the screenshot on the desire location with different name using current date and time
			//FileUtils.copyFile(scrFile, new File("C:/shots/" + fileName+" "+timestamp+extension));
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			File dest=new File(constant+photo+"SS_"+className+"_"+SSName+"_"+timestamp+".png");
			Files.copy(src, dest);
		}
		
		public void getAshot(WebDriver driver, String constant, String photo, String className, String SSName) throws IOException
		{
		
			//String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new java.util.Date());
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			
			//String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
			//js.executeScript("document.getElementsByClassName('navbar navbar-default navbar-fixed-top')[0].classList.remove('navbar-fixed-top');");
			File fis= new File(constant+photo+"Ashot_"+className+"_"+SSName+"_"+timestamp+".png");
			Screenshot sh = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			ImageIO.write(sh.getImage(), "PNG", fis);
			
		}
	}

}

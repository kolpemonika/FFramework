package ticketingSystem;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Generic.BaseClass;
import PomPages.DashboardPage;
import PomPages.HomePage;
import PomPages.ResourcePlanningReportPage;
import PomPages.TaskPage;

public class TestScript extends BaseClass {


	@Test
	public void test_01() throws Exception
	{
		driver.navigate().refresh();
		HomePage hp = new HomePage(driver);
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String className = this.getClass().getSimpleName();
		test=extent.createTest(className + " :: "+methodName);
		hp.dashboard().click();
		
	//	test.info("On Dashboard Page");

		Thread.sleep(1000);
	}
	
	

	@Test
	public void test_02() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String className = this.getClass().getSimpleName();
		test=extent.createTest(className + " :: "+methodName);

		HomePage hp = new HomePage(driver);
		DashboardPage dp = new DashboardPage(driver);
		dp.task1().click();
		Thread.sleep(1000);
	}
	
	@Test
	public void test_03() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String className = this.getClass().getSimpleName();
		test=extent.createTest(className + " :: "+methodName);
		TaskPage tp = new TaskPage(driver);
		tp.playBtn().click();
		
		onTestSuccess("Task in playmode on Task page");

		Thread.sleep(1000);
		driver.navigate().refresh();

	}
	
	@Test
	public void test_04() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String className = this.getClass().getSimpleName();
		test=extent.createTest(className + " :: "+methodName);

		HomePage hp = new HomePage(driver);
		hp.dashboard().click();
		Thread.sleep(1000);
		test.info("Task added in play moe on dashboard");
	}
	@Test
	public void test_05() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String className = this.getClass().getSimpleName();
		test=extent.createTest(className + " :: "+methodName);
		test.info("Task added in play moe on dashboard");

		HomePage hp = new HomePage(driver);
		DashboardPage dp = new DashboardPage(driver);
		dp.task1().click();
		Thread.sleep(1000);
	}
	
	@Test
	public void test_06() throws Exception
	{
		driver.navigate().refresh();

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String className = this.getClass().getSimpleName();
		test=extent.createTest(className + " :: "+methodName);
		TaskPage tp = new TaskPage(driver);
		tp.pauseBtn().click();
		onTestSuccess("TAsk in pause mode");
		Thread.sleep(1000);
		driver.navigate().refresh();

	}
	
	@Test
	public void test_07() throws Exception
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String className = this.getClass().getSimpleName();
		test=extent.createTest(className + " :: "+methodName);

		HomePage hp = new HomePage(driver);
		hp.dashboard().click();
		Thread.sleep(1000);
		test.info("Task removed from play mode on dashboard");
		driver.navigate().refresh();

	}
	
}

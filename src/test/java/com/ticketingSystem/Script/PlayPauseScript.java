package com.ticketingSystem.Script;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ticketingSystem.Generic.BaseClass;
import com.ticketingSystem.pageObject.DashboardPage;
import com.ticketingSystem.pageObject.HomePage;
import com.ticketingSystem.pageObject.ResourcePlanningReportPage;
import com.ticketingSystem.pageObject.TaskPage;

public class PlayPauseScript extends BaseClass 
{
	
	@BeforeClass
	public void openApp() throws InterruptedException, IOException, Exception
	{
		ll.loginPageWithDefaultValues(driver);
		onTestSuccess("Logged in successfully");
	}

	@Test
	public void test_01() throws Exception
	{
		driver.navigate().refresh();
		HomePage hp = new HomePage(driver);
		createTest("Amreen", "UI/UX");
//		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		String className = this.getClass().getSimpleName();
//		test=extent.createTest(className + " :: "+methodName);
		hp.dashboard().click();
		Thread.sleep(1000);
	}
	
	@Test
	public void test_02() throws Exception
	{
		createTest("Amreen", "UI/UX");
		HomePage hp = new HomePage(driver);
		DashboardPage dp = new DashboardPage(driver);
		dp.task1().click();
		Thread.sleep(1000);
	}
	
	@Test
	public void test_03() throws Exception
	{
		createTest("Priyanka", "Regression");
		TaskPage tp = new TaskPage(driver);
		tp.playBtn().click();
		onTestSuccess("Task in playmode on Task page");
		Thread.sleep(1000);
		driver.navigate().refresh();
	}
	
	@Test
	public void test_04() throws Exception
	{
		createTest("Priyanka", "Regression");
		HomePage hp = new HomePage(driver);
		hp.dashboard().click();
		Thread.sleep(1000);
		driver.navigate().refresh();
		test.info("Task added in play moe on dashboard");
	}
	
	@Test
	public void test_05() throws Exception
	{
		createTest("Priyanka", "Regression");
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
		createTest("Priyanka", "Regression");
		TaskPage tp = new TaskPage(driver);
		tp.pauseBtn().click();
		onTestSuccess("TAsk in pause mode");
		Thread.sleep(1000);
		driver.navigate().refresh();
	}
	
	@Test
	public void test_07() throws Exception
	{
		createTest("Amreen", "UI/UX");
		HomePage hp = new HomePage(driver);
		hp.dashboard().click();
		Thread.sleep(1000);
		test.info("Task removed from play mode on dashboard");
		driver.navigate().refresh();
	}
	
}

package com.ticketingSystem.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	
	public DashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h6[text()='Extent report -> KT']")
	private WebElement task1;
	
	public WebElement task1()
	{
		return task1;
	}

	
}

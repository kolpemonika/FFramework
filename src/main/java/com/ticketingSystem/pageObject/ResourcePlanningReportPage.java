package com.ticketingSystem.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResourcePlanningReportPage {
	
	public ResourcePlanningReportPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy (id ="react-select-2-input")
	private WebElement selectUser;
	
	public WebElement selectUser()
	{
		return selectUser;
	}
}

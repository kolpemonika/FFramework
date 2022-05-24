package com.ticketingSystem.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyTicketPage {
	
	public MyTicketPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="ticket_id")
	private WebElement ticketId;
	
	public WebElement ticketId()
	{
		return ticketId;
	}
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submit;
	
	public WebElement submit()
	{
		return submit;
	}
}

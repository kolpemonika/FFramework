package com.ticketingSystem.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//span[text()='Ticket Management']")
	private WebElement ticketManagement;
	
	public WebElement ticketManagement()
	{
		return ticketManagement;
	}
		
	@FindBy (xpath = "//span[text()='My Tickets']")
	private WebElement myTickets;
	
	public WebElement myTickets()
	{
		return myTickets;
	}
	
	
	@FindBy (xpath = "//span[text()='Create Ticket']")
	private WebElement createTicket;
	
	public WebElement createTicket()
	{
		return createTicket;
	}
	
	@FindBy (xpath = "//span[text()='Reports']")
	private WebElement reports;
	
	public WebElement reports()
	{
		return reports;
	}
	
	@FindBy (xpath = "//a[@href='/TechTicket/Report/ResourcePlanningReport']")
	private WebElement resourcePlanningReport;
	
	public WebElement resourcePlanningReport()
	{
		return resourcePlanningReport;
	}
	
	
	@FindBy (xpath = "//span[text()='Dashboard']")
	private WebElement dashboard;
	
	public WebElement dashboard()
	{
		return dashboard;
	}
	
}

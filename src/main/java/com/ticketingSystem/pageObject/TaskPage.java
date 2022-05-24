package com.ticketingSystem.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskPage {
	
	
			public TaskPage(WebDriver driver)
			{
				PageFactory.initElements(driver, this);
			}

			@FindBy(xpath="(//i[@class='icofont-play-alt-1 text-success'])[2]")
			private WebElement playBtn;
			
			public WebElement playBtn()
			{
				return playBtn;
			}
			
		
			@FindBy(xpath="//i[@class='icofont-pause text-danger']")
			private WebElement pauseBtn;
			
			public WebElement pauseBtn()
			{
				return pauseBtn;
			}
}

package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class profilePage {

	
	@FindBy(xpath = "//select[@class='form-control select2 select2-hidden-accessible']")
	private WebElement locationGRA;
	
	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement saveChanges;
	
	public void getSaveChanges() {
		saveChanges.click();
	}

	public profilePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void setLocationGRA(WebElement locationGRA) {
		this.locationGRA = locationGRA;
	}

	public WebElement getLocationGRA() {
		return locationGRA;
	}
	
	
}

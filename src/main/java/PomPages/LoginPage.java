package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
		
	@FindBy(name="username")
	private WebElement uname;
	
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;
	
		
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submit;


	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void getUname() {
		//uname.click();
	}

	public void setUname(String username) {
		uname.sendKeys(username);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void getPassword() {
		password.click();
	}

	public void getSubmit() {
		submit.click();
	}
	
	
}
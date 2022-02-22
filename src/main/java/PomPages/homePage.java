package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {
	
	
	@FindBy(xpath = "//a[@class='sidebar-toggle']")
	//@FindBy(xpath = "//*[@id=\"app\"]/header/nav/a")
	private WebElement toggle;
	
	@FindBy(xpath = "//span[text()='Ornament Packing History']")
	private WebElement ornamentPackingHistory;
	
	@FindBy(xpath = "//span[text()='Daily Stock Report']")
	private WebElement dailyStockReport;

	public homePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
 
	public void getUserProfile() {
		userProfile.click();
	}

	public void getProfileOption() {
		profileOption.click();
	}

	@FindBy(xpath = "(//a[@class='dropdown-toggle'])[3]")
	private WebElement userProfile;
	
	@FindBy(xpath = "//a[@title='Logout']")
	private WebElement logoutbtn;
	
	@FindBy(xpath = "//button[@class='confirm']")
	private WebElement logoutconfirm;
	
	public void getLogoutbtn() {
		logoutbtn.click();
	}

	public void getLogoutconfirm() {
		logoutconfirm.click();
	}

	@FindBy(xpath = "//a[@class='btn btn-default btn-flat']")
	private WebElement profileOption;
	

	public void getToggle() {
		toggle.click();
	}
	 
	public void getOrnamentPackingHistory() {
		ornamentPackingHistory.click();
	}


	public void getDailyStockReport()
	{
		dailyStockReport.click();
	}
}

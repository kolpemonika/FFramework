package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dailyStockPage {
	
	@FindBy(id = "btn_add_new_data")
	private WebElement addData;
	
	@FindBy(id="created_at")
	private WebElement createdAt;

	@FindBy(id="branch_id")
	private WebElement branchName;	

	@FindBy(id="salesman_id")
	private WebElement salesmanId;
	
	@FindBy(id="type")
	private WebElement stockStatus;
	
	@FindBy(id="counter_id")
	private WebElement counter;
	
	@FindBy(xpath = "//*[contains(text(),'Data Already Uploaded !!!')]")
	private WebElement errorMsg;
	
	@FindBy(id="opening_pcs_1")
	private WebElement item1;
	
	@FindBy(id="opening_pcs_2")
	private WebElement item2;
	
	@FindBy(id="opening_pcs_3")
	public WebElement item3;
	
	@FindBy(id="opening_pcs_4")
	private WebElement item4;
	
	@FindBy(id="opening_pcs_5")
	private WebElement item5;
	
	@FindBy(id="opening_pcs_6")
	private WebElement item6;
	
	@FindBy(id="opening_pcs_7")
	private WebElement item7;
	
	@FindBy(id="opening_pcs_8")
	private WebElement item8;
	
	@FindBy(id="opening_pcs_9")
	private WebElement item9;
	
	@FindBy(id="opening_pcs_10")
	private WebElement item10;
	
	@FindBy(id="opening_pcs_11")
	private WebElement item11;
	
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement submit;
	
	@FindBy(id="add-closing-stock")
	private WebElement closingStock;
	
	public void closingStock()
	{
		closingStock.click();
	}
	
	public WebElement getErrorMsg()
	{
		return errorMsg;
	}
	
	public void submit()
	{
		submit.click();
	}
	
	public void getItem1(int a)
	{
		item1.sendKeys(String.valueOf(a));
	}

	public void getItem2(int a)
	{
		item2.sendKeys(String.valueOf(a));
	}
	
	public void getItem3(int a)
	{
		item3.sendKeys(String.valueOf(a));
	}
	
	public void getItem4(int a)
	{
		item4.sendKeys(String.valueOf(a));
	}

	public void getItem5(int a)
	{
		item5.sendKeys(String.valueOf(a));
	}
	
	public void getItem6(int a)
	{
		item6.sendKeys(String.valueOf(a));
	}
	
	public void getItem7(int a)
	{
		item7.sendKeys(String.valueOf(a));
	}
	
	public void getItem8(int a)
	{
		item8.sendKeys(String.valueOf(a));
	}
	
	public void getItem9(int a)
	{
		item9.sendKeys(String.valueOf(a));
	}
	
	public void getItem10(int a)
	{
		item10.sendKeys(String.valueOf(a));
	}
	
	public void getItem11(int a)
	{
		item11.sendKeys(String.valueOf(a));
	}
	public dailyStockPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreatedAt() {
		return createdAt;
	}

	public WebElement getBranchName() {
		return branchName;
	}

	public WebElement getSalesmanId() {
		return salesmanId;
	}

	public WebElement getStockStatus() {
		return stockStatus;
	}

	public WebElement getCounter() {
		return counter;
	}

	public void getAddData()
	{
		addData.click();
	}
}

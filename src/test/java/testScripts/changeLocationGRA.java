package testScripts;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import Generic.BaseClass;
import PomPages.homePage;
import PomPages.profilePage;

public class changeLocationGRA extends BaseClass{

	homePage hp = new homePage(driver);
	profilePage pp= new profilePage(driver); 

	@Test (priority = 10)
	public void Login123() throws InterruptedException, FileNotFoundException, IOException
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		System.out.println(methodName);

		String className = this.getClass().getSimpleName(); 

		Thread.sleep(3000);


		//	ScreenShot ss = new ScreenShot();
		//	System.out.println(currentDir);
		//ss.getAshot(driver, currentDir, "./Photo/", methodName, className);

		ss.getAshot(driver, autoConstant.currentDir, "./Photo/", className, "HomePage");

		//click on user profile
		Thread.sleep(3000);
		hp.getUserProfile();
		Thread.sleep(3000);

		//click on profile
		hp.getProfileOption();


	}
	
	@Test
	public void mogin() throws InterruptedException
	{
		utilities.toSelectFromDropDown(pp.getLocationGRA(), "Karad Branch - Tops/ Small Ornament Counter KRD");
		Thread.sleep(3000);
		//wd.takeScreenShot(driver, className);

		Thread.sleep(3000);		
		//.ss.getAshot(driver, autoConstant.currentDir, "./Photo/", className, "AfterChange");

		//wd.takeScreenShot(driver, className);

		//click on save changes
		pp.getSaveChanges();
		Thread.sleep(3000);

	}
}

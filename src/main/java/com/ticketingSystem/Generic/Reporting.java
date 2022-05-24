package com.ticketingSystem.Generic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter 
{

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports xReports;
	public ExtentTest xTest;

	//LoC for configuring the extend report
	//	1.Format : HTML report
	//	2. Location : Reports folder in the current project folder
	//  3. New report should be generated for every execution
	//  4. Add the configuration details to be represented on the report

	public void onStart(ITestContext testContext) 
	{

		String dateStamp = new SimpleDateFormat("yyyy.MM.dd.mm.ss").format(new Date());
		String repName = "Test-Automation-Report" + dateStamp + ".html";
		//Where and what should be the name of the report and location should be captured.
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtenReport/" + repName);
		htmlReporter.config().setDocumentTitle("Automation Testing"); 
		htmlReporter.config().setReportName("Functional Testing"); 
		htmlReporter.config().setAutoCreateRelativePathMedia(true);//to add SS

		xReports = new ExtentReports();
		xReports.attachReporter(htmlReporter);  //Extend report in HTML format will be generated.
		xReports.setSystemInfo("QA Name", "Amreen");
		xReports.setSystemInfo("HostName", "Localhost");
		xReports.setSystemInfo("OS", "Windows");
	}

	//Once the test completes the reports  need to be generated as per the configuration will be after this method is executed.
	public void onFinish(ITestContext testContext) 
	{

		xReports.flush();
	} 

	//Fetch the test results,if the test(@Test) returns pass
	//If the test method executed with passed status,the entry should be made in the extent report.

	public void onTestSuccess(ITestResult tr) 
	{

		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.PASS, "Test is Passed");
		xTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr) 
	{
		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.FAIL, "Test is Failed");
		xTest.log(Status.FAIL, tr.getThrowable());
		xTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		//To add SS in the extent report
		String Path = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png"; 

		File  file = new File(Path);
		if(file.exists()){
			try {
				xTest.fail("Screenshot for the failed test is: "+ xTest.addScreenCaptureFromPath(Path));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}


	public void onTestSkipped(ITestResult tr) {

		xTest = xReports.createTest(tr.getName());
		xTest.log(Status.SKIP, "Test is Skipped");
		xTest.log(Status.SKIP, tr.getThrowable());
		xTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));
	}

}

package com.ticketingSystem.Generic;	


import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ListenersBlog extends BaseClass implements ITestListener {
	
//	public void onTestSuccess(ITestResult result) {
//		
//		logger = extent.createTest(result.getTestClass().getName() + " :: "+result.getMethod().getMethodName());
//
//		
//		System.out.println("Test Successfully Finished : " +result.getName());
//		logger.log(Status.PASS, "Test Case PASSED");
//
//		try {
//			logger.pass("PASS", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentScreenShot(driver)).build());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	}
//
//	public void onTestFailure(ITestResult result) {
//		logger = extent.createTest(result.getTestClass().getName() + " : : "+result.getMethod().getMethodName());
//
//		System.out.println("Test Failed" +result.getName());
//		logger.log(Status.FAIL, "Test Case FAILED");
//		logger.log(Status.FAIL, result.getThrowable());
//		try {
//			logger.fail("FAIL", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentScreenShot(driver)).build());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//	}
//
//	public void onTestSkipped(ITestResult result) {
//		
//		logger = extent.createTest(result.getTestClass().getName() + " : : "+result.getMethod().getMethodName());
//
//		System.out.println("Test Skipped" +result.getName());
//		logger.log(Status.SKIP, "Test Case SKIPPED");
//		logger.log(Status.SKIP, result.getThrowable());
//		try {
//			logger.skip("SKIPPED", MediaEntityBuilder.createScreenCaptureFromPath(ss.extentScreenShot(driver)).build());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	public void onFinish(ITestContext context, ITestResult result) {
		logger = extent.createTest(result.getTestClass().getName() + " : : "+result.getMethod().getMethodName());

		System.out.println("This is onFinish method" +context.getPassedTests());
		System.out.println("This is onFinish method" +context.getFailedTests());
	}
}
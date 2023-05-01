package com.project1.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.project1.qa.utils.ExtentReporter;
import com.project1.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extenttest;
	String testName;
	@Override
	public void onStart(ITestContext context) {
	extentReport = ExtentReporter.generateExtentReport();	
	}


	@Override
	public void onTestStart(ITestResult result) {
		testName =result.getName();
		extenttest = extentReport.createTest(testName);
		extenttest.log(Status.INFO, testName+" Execution Started");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extenttest.log(Status.PASS, testName+" got successfully Executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		System.out.println("screenshot taken");
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String DestinationScreenshotPath=Utilities.CaptureScreenShot(driver, testName);
		
		extenttest.addScreenCaptureFromPath(DestinationScreenshotPath);
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL, testName+" got failed");
		
		System.out.println(testName+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.SKIP, testName+" got skipped");
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
	extentReport.flush();
	String pathofextentreport= System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
	File extentReport = new File(pathofextentreport);
	try {
		Desktop.getDesktop().browse(extentReport.toURI());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}

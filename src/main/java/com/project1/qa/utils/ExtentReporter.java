package com.project1.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentReportFile);
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("Project1 Test Automation Results");
		sparkreporter.config().setDocumentTitle("Project1 Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh/mm/ss");
		
		extentReport.attachReporter(sparkreporter);
		
		Properties configprop = new Properties();
		File configpropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\project\\qa\\config\\config.properties");
		try {
		FileInputStream fisconfigprop = new FileInputStream(configpropFile);
		configprop.load(fisconfigprop);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application Url",configprop.getProperty("url"));
		extentReport.setSystemInfo("BrowserName", configprop.getProperty("browser"));
		extentReport.setSystemInfo("Email", configprop.getProperty("validemail"));
		extentReport.setSystemInfo("Password", configprop.getProperty("validpassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("java Version", System.getProperty("java.version"));
		return extentReport;

	}

}

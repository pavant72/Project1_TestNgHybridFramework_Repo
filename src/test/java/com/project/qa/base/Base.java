package com.project.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.project1.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	
	public  Base() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\project\\qa\\config\\config.properties");
		
		dataprop = new Properties();
		File datapropFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\project\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream fi2 = new FileInputStream(datapropFile);
		dataprop.load(fi2);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
		FileInputStream fi = new FileInputStream(propFile);
		prop.load(fi);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver IntializeBrowserAndOpenApplicationUrl(String Browser) {
	
			
			
			 if(Browser.equals("chrome"))
				 driver = new ChromeDriver();
			 else if(Browser.equals("firefox"))
				 driver = new FirefoxDriver();
			
			
			
			
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
			driver.get(prop.getProperty("url"));
			return driver;
	}
}

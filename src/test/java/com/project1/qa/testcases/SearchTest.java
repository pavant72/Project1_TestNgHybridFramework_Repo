package com.project1.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.project.qa.base.Base;
import com.project1.qa.pages.HomePage;
import com.project1.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchpage;
	
	public SearchTest() {
		super();
	}
	
	
	
	public WebDriver driver;

	
	@BeforeMethod
	public void setup() {
		driver = IntializeBrowserAndOpenApplicationUrl(prop.getProperty("browser"));
}
	@AfterMethod
	public void teardown() {
		driver.quit();
}
	@Test
	public void verifySearchWithValidProduct() {
		
		HomePage homepage = new HomePage(driver);
		searchpage=homepage.Search(dataprop.getProperty("validproduct"));
		Assert.assertTrue(driver.findElement(By.linkText(dataprop.getProperty("validproductresult"))).isDisplayed());
	}
	
	@Test
	public void VerifySearchWithInvalidProduct() {
		
		HomePage homepage = new HomePage(driver);
		searchpage=homepage.Search(dataprop.getProperty("invalidproduct"));
		Assert.assertEquals(searchpage.RetriveInvalidProductSearchResultsHeading(),dataprop.getProperty("invalidproductexpectedresult"),"product is available");
	}
	}

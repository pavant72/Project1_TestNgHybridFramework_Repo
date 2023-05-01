package com.project1.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.qa.base.Base;
import com.project1.qa.pages.AccountPage;
import com.project1.qa.pages.HomePage;
import com.project1.qa.pages.LoginPage;
import com.project1.qa.utils.Utilities;

public class LoginTest extends Base {
	AccountPage accountpage;
	LoginPage loginpage;
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		
		driver =IntializeBrowserAndOpenApplicationUrl(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		loginpage= homepage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();
	}
@Test(priority=1,dataProvider="validCredentialsSupplier")
public void loginwithvalidcredentials(String email,String password) {
		
		 AccountPage accountpage = loginpage.login(email, password);	
		Assert.assertTrue(accountpage.getDisplayStatusOfEdityouraccountinformation());
		
}

@DataProvider(name="validCredentialsSupplier")
public Object[][] SupplyTestData() {
	Object[][] data= Utilities.getTestDataFromExcel("Login");
	return data;

}

@Test
public void loginwithinvalidcrdentials() {
	
	loginpage.login(Utilities.GenerateTimeStamp(),dataprop.getProperty("invalidpassword"));
	

	String actualwarningmessage = loginpage.RetrieveEmailPasswordNotMatchingWarningText();
	String Expectedwarningmessage =dataprop.getProperty("emailpasswordnotmatchingwarning");
	Assert.assertTrue(actualwarningmessage.contains(Expectedwarningmessage),"Expected warning message not displayed");
	
	
}

@Test
public void verifyloginwithInvalidEmailAndValidPassword() {
	loginpage.login(Utilities.GenerateTimeStamp(), prop.getProperty("validpassword"));
	Assert.assertTrue(loginpage.RetrieveEmailPasswordNotMatchingWarningText().contains(dataprop.getProperty("emailpasswordnotmatchingwarning")),"Expected warning message not displayed");
	
}
@Test
public void VerifyLoginWithValidEmailAndInvalidPassword() {
	loginpage.login(prop.getProperty("validemail"), dataprop.getProperty("invalidpassword"));
	Assert.assertTrue(loginpage.RetrieveEmailPasswordNotMatchingWarningText().contains(dataprop.getProperty("emailpasswordnotmatchingwarning")),"Expected warning message not displayed");
	
}

@Test
public void VeryLoginWIthNoCredentials() {
	loginpage.clickLoginButton();
	
	
	String actualwarningmessage = loginpage.RetrieveEmailPasswordNotMatchingWarningText();
	String Expectedwarningmessage = dataprop.getProperty("emailpasswordnotmatchingwarning");
	Assert.assertTrue(actualwarningmessage.contains(Expectedwarningmessage),"Expected warning message not displayed");
	
}
}

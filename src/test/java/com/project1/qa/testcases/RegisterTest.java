package com.project1.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.project.qa.base.Base;
import com.project1.qa.pages.AccountSuccessPage;
import com.project1.qa.pages.HomePage;
import com.project1.qa.pages.RegisterPage;
import com.project1.qa.utils.Utilities;

public class RegisterTest extends Base {
	RegisterPage registerpage;
	public RegisterTest() {
		super();
	}
	
	
public WebDriver driver;
	
	
		
	@BeforeMethod
	public void setup() {
		
		driver =IntializeBrowserAndOpenApplicationUrl(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		registerpage=homepage.Register();
		
		
	}
	@AfterMethod
	public void teardown() {
		driver.quit();}
	
	
	@Test
	public void AccountRegistrationWithMandatoryFields() {
		
		AccountSuccessPage accountsuccesspage = registerpage.RegisterWithMandatoryFields(
				dataprop.getProperty("firstname"),
				dataprop.getProperty("lastname"), Utilities.GenerateTimeStamp(),
				dataprop.getProperty("telephone"), prop.getProperty("validpassword"),
				prop.getProperty("validpassword"));
		
		Assert.assertEquals(accountsuccesspage.RetrieveAccountCreateSuccessHeaading(),dataprop.getProperty("expectedheading"),"your account has not created");
		
	}
	@Test
	public void VerifyRegisteringAccountByProvidingAllFields() {
		
		AccountSuccessPage accountsuccesspage =
				registerpage.RegisterAccountByProvidingAllFields(dataprop.getProperty("firstname"),
				dataprop.getProperty("lastname"), Utilities.GenerateTimeStamp(),
				dataprop.getProperty("telephone"), prop.getProperty("validpassword"),
				prop.getProperty("validpassword"));
		
		Assert.assertEquals(accountsuccesspage.RetrieveAccountCreateSuccessHeaading(),dataprop.getProperty("expectedheading"),"your account has not created");
		
		
	}
	@Test
	public void VerifyResgisterAccountWithExistingEMailAddress() {
		
		
				registerpage.RegisterAccountByProvidingAllFields(dataprop.getProperty("firstname"),
				dataprop.getProperty("lastname"), prop.getProperty("validemail"),
				dataprop.getProperty("telephone"), prop.getProperty("validpassword"),
				prop.getProperty("validpassword"));
		
		
		
		Assert.assertEquals(registerpage.RetrieveExistingEmailWarningText(),dataprop.getProperty("existingemailwarning"),"your account has not created");
	}
	
		@Test
	public void verifyRegisteringAccountWithoutFillingDetails() {
			
			registerpage.ClickContinue();
		
		
String Actualsucessheading =registerpage.RetrievePrivacyPolicyWarningText();
		
		Assert.assertEquals(Actualsucessheading,dataprop.getProperty("privacypolicy"),"your account has not created");
		
		
	}
}

package com.project1.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement EmailAddressField;
	
	@FindBy(id="input-password")
	private WebElement PasswordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement LoginButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement EmailPasswordNotMatchingWarning;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void EnterEmailAddress(String emailText) {
		EmailAddressField.sendKeys(emailText);
	}
	
	public void EnterPassword(String PasswordText) {
		PasswordField.sendKeys(PasswordText);
	}
	
	public AccountPage clickLoginButton() {
		LoginButton.click();
		return new AccountPage(driver);
	}
	
	
	public AccountPage login(String emailText,String passwordText) {
		EmailAddressField.sendKeys(emailText);
		PasswordField.sendKeys(passwordText);
		LoginButton.click();
		return new AccountPage(driver);
		
	}
	
	public String RetrieveEmailPasswordNotMatchingWarningText() {
		String warningtext=EmailPasswordNotMatchingWarning.getText();
		return warningtext;
	}

}

package com.project1.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement FirstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement LastNameField;
	
	@FindBy(id="input-email")
	private WebElement EmailField;
	
	@FindBy(id="input-telephone")
	private WebElement TelephoneField;
	
	@FindBy(id="input-password")
	private WebElement PasswordField;
	
	@FindBy(id="input-confirm")
	private WebElement ConfirmPasswordField;
	
	@FindBy(name="agree")
	private WebElement IAgreeCheckbox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	private WebElement NewsletterRadioButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement ExistingEmailWarningAlert;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement PrivacyPolicyWarning;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void EnterFirstName(String firstNameText) {
		FirstNameField.sendKeys(firstNameText);
	}
	
	public void EnterLastName(String lastNameText) {
		LastNameField.sendKeys(lastNameText);
	}
	
	public void EnterEmail(String emailText) {
		EmailField.sendKeys(emailText);
	}
	
	public void EnterTelephone(String telephoneText) {
		TelephoneField.sendKeys(telephoneText);
	}
	
	public void EnterPassword(String passwordText) {
		PasswordField.sendKeys(passwordText);
	}
	
	public void EnterConfirmPassword(String confirmPasswordText) {
		ConfirmPasswordField.sendKeys(confirmPasswordText);
	}
	
	
	public void SelectNewsLetter() {
		NewsletterRadioButton.click();
	}
	
	public void SelectIAgree() {
		IAgreeCheckbox.click();
	}
	
	public AccountSuccessPage ClickContinue() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String RetrieveExistingEmailWarningText() {
		String emailwarning=ExistingEmailWarningAlert.getText();
		return emailwarning;
	}
	
	public String RetrievePrivacyPolicyWarningText() {
		String privacywarning=PrivacyPolicyWarning.getText();
		return privacywarning;
	}
	
	public AccountSuccessPage RegisterWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String confirmPasswordText) {
		FirstNameField.sendKeys(firstNameText);
		LastNameField.sendKeys(lastNameText);
		EmailField.sendKeys(emailText);
		TelephoneField.sendKeys(telephoneText);
		PasswordField.sendKeys(passwordText);
		ConfirmPasswordField.sendKeys(confirmPasswordText);
		IAgreeCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	
	public AccountSuccessPage RegisterAccountByProvidingAllFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String confirmPasswordText) {
		FirstNameField.sendKeys(firstNameText);
		LastNameField.sendKeys(lastNameText);
		EmailField.sendKeys(emailText);
		TelephoneField.sendKeys(telephoneText);
		PasswordField.sendKeys(passwordText);
		ConfirmPasswordField.sendKeys(confirmPasswordText);
		NewsletterRadioButton.click();
		IAgreeCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	

}
}

package com.project1.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	//objects
	@FindBy(xpath="//span[normalize-space()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement LoginOption;
	
	@FindBy(linkText="Register")
	private WebElement RegisterOption;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement SearchOption;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement Searchbutton;
	
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	
	
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public LoginPage clickLoginOption() {
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	
	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	
	public RegisterPage clickOnRegister() {
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public RegisterPage Register() {
		myAccountDropMenu.click();
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	
	
	
	
	public void EnterTextOnSearch(String searchtext) {
		SearchOption.sendKeys(searchtext);
	}
	
	public SearchPage ClickOnSearchButton() {
		Searchbutton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage Search(String searchtext) {
		SearchOption.sendKeys(searchtext);
		Searchbutton.click();
		return new SearchPage(driver);
	}
	
}

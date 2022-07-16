package com.orangeHRM.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class logInPage {

	WebDriver driver;
	
	public logInPage(WebDriver driver)
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
		
	
	@FindBy(id="txtUsername")
	@CacheLookup
	WebElement userName;
	
	@FindBy(id="txtPassword")
	@CacheLookup
	WebElement password;
	
	@FindBy(id="btnLogin")
	@CacheLookup
	WebElement logInButton;
	
	@FindBy(xpath="//a[contains(text(),'Forgot your password?')]")
	@CacheLookup
	WebElement forgotLink;
	
	public void enterUserName(String uname) {
		userName.sendKeys(uname);
	}
	
	public void enterPass(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickLogInBtn() {
		logInButton.click();
	}
}

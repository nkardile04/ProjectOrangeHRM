package com.orangeHRM.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchAdminUser {
	WebDriver driver;

	public SearchAdminUser(WebDriver driver)
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//b[contains(text(),'Admin')]")
	WebElement adminTab;
	
	@FindBy(id="searchSystemUser_userName")
	WebElement enterUser;
	
	@FindBy(xpath="//select[@id='searchSystemUser_userType']")
	WebElement RoleDropDown;
	
	@FindBy(xpath="//input[@id='searchSystemUser_employeeName_empName']")
	WebElement enterEmpName;
	
	@FindBy(xpath="//select[@id='searchSystemUser_status']")
	WebElement statusDropDown;
	
	@FindBy(xpath="//input[@id='searchBtn']")
	WebElement searchBtn;
	
	public void enterAdminTab() {
		adminTab.click();
	}
	
	public void enterUserName(String userName) {
		enterUser.sendKeys(userName);
	}
	
	public void roleDropDown(String role) {
		Select s1=new Select(RoleDropDown);
		s1.selectByVisibleText(role);
	}
	
	public void enterEmpName(String  empName) {
		enterEmpName.sendKeys(empName);
		}
	
	public void statusDropDown(String status) {
		Select s1=new Select(statusDropDown);
		s1.selectByVisibleText(status);
	}
	
	public void clickSearch() {
		searchBtn.click();
	}
}

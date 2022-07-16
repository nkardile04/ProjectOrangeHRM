package com.orangeHRM.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.orangeHRM.pageobjects.logInPage;

public class TC001_LogIn extends BaseClass {
	
	@Test
	public void logInTest() {
		test=extent.createTest("log In Test").assignAuthor("Nana").assignCategory("Smoke Test").assignDevice("Ubuntu");
		test.info("Checking log in Test");
		logInPage lp=new logInPage(driver);
		lp.enterUserName(userName);
		test.info("User name entered");
		lp.enterPass(password);
		test.info("password entered");
		lp.clickLogInBtn();
		test.info("Clicked on log in Button");
		
		boolean status=driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).isDisplayed();
		Assert.assertTrue(status);
	}
}

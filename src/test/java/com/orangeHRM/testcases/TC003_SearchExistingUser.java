package com.orangeHRM.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangeHRM.pageobjects.SearchAdminUser;
import com.orangeHRM.pageobjects.logInPage;

public class TC003_SearchExistingUser extends BaseClass {
	
	@Test
	public void searchExistingUser() throws InterruptedException {
	test=extent.createTest("Search existing user Test").assignAuthor("Nana").assignCategory("Smoke Test").assignDevice("Ubuntu");
	test.info("Checking existing user");
	logInPage lp=new logInPage(driver);
	lp.enterUserName(userName);
	test.info("User name entered");
	lp.enterPass(password);
	test.info("password entered");
	lp.clickLogInBtn();
	test.info("Clicked on log in Button");
	
	Thread.sleep(3000);
	
	SearchAdminUser ad=new SearchAdminUser(driver);
	ad.enterAdminTab();
	test.info("Clicked on Admin Tab");
	ad.enterUserName("adash");
	test.info("Entered user name");
	ad.roleDropDown("ESS");
	test.info("Selected role from dropdown");
	ad.enterEmpName("Ananya Dash");
	test.info("entered employee name");
	ad.statusDropDown("Enabled");
	test.info("selected status from dropdown");
	ad.clickSearch();
	test.info("clicked on search button");
	
	Thread.sleep(3000);
	
	if(driver.findElement(By.xpath("//td[contains(text(),'Ananya Dash')]")).isDisplayed()) {
		Assert.assertTrue(true);
	} else {
		Assert.assertTrue(false);
	}
		
		
	
	}

}

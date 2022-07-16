package com.orangeHRM.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangeHRM.pageobjects.logInPage;
import com.orangeHRM.utilities.XLUtilsOrangeHRM;

public class TC002_logIn_DDT extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void logInTestDDT(String uname, String pass) throws InterruptedException 
	{
		test=extent.createTest("Data Driven logIn Test").assignAuthor("Nana").assignCategory("Smoke Test").assignDevice("Ubuntu");
		test.info("Checking data driven log in Test");
		logInPage lp=new logInPage(driver);
		lp.enterUserName(uname);
		test.info("User name entered "+uname);
		lp.enterPass(pass);
		test.info("password entered "+pass);
		lp.clickLogInBtn();
		test.info("Clicked on log in Button");
		
		Thread.sleep(3000);
		
		boolean status=driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).isDisplayed();
		Assert.assertTrue(status);
		Thread.sleep(2000);
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/orangeHRM/testData/testData.xlsx";
		
		int rownum=XLUtilsOrangeHRM.getRowCount(path, "Sheet1");
		int colcount=XLUtilsOrangeHRM.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtilsOrangeHRM.getCellData(path,"Sheet1", i,j);//1 0
			}		
		}
	return logindata;
	}

}

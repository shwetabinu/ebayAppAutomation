package com.ebayAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ebayAutomation.base.BaseClass;
import com.ebayAutomation.util.ExcelUtil;

import io.appium.java_client.MobileElement;


public class LoginPage extends BaseClass {
	
	private String username_id="userid";
	private String password_id = "pass";
	private String signin_id = "sgnBt";

	/**
	 * Imports environmental setup from BaseClass.
	 * Initializes the Page objects.
	 * @throws Exception
	 */
	public LoginPage() throws Exception {
		PageFactory.initElements(driver, this);

	}
	

	/**
	 * The method clicks on the user id and password and clicks on login button
	 * @param rno Row number where the details are present
	 * @throws Exception 
	 */
	public void loginToEBay(int rno){
		try {
			MobileElement user_name = (MobileElement) driver.findElement(By.id(username_id));
			user_name.click();
			String user_name_actual = ExcelUtil.getCellData(rno, 5);
			user_name.click();
			user_name.sendKeys(user_name_actual);
			MobileElement password = (MobileElement) driver.findElement(By.id(password_id));
			String password_actual = ExcelUtil.getCellData(rno, 6);
			password.click();
			password.sendKeys(password_actual);
			MobileElement signin = (MobileElement) driver.findElement(By.id(signin_id));
			signin.click();
		} catch(NoSuchElementException e1)
		{
			e1.printStackTrace();
			Assert.fail();
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}

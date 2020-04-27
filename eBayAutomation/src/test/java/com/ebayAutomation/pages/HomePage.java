package com.ebayAutomation.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ebayAutomation.base.BaseClass;
import com.ebayAutomation.util.ExcelUtil;
import com.ebayAutomation.util.Log;
import com.ebayAutomation.util.ScreenshotCapture;

import io.appium.java_client.MobileElement;

public class HomePage extends BaseClass {

	ScreenshotCapture s;

	private String sign_in_id="com.ebay.mobile:id/button_sign_in";
	private String search_textbox_id = "com.ebay.mobile:id/search_box";
	private String search_textbox_id_after_click = "com.ebay.mobile:id/search_src_text";
	private String tv_drop_down_option_xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView";

	/**
	 * Imports environmental setup from BaseClass.
	 * Initializes the Page objects.
	 * Initializes the ScreenshotCapture class
	 * @throws Exception
	 */
	public HomePage() throws Exception {
		s=new ScreenshotCapture();
		PageFactory.initElements(driver, this);

	}
	/**
	 * The method Searches for an item through the Search tool bar and selects an option from the drop down
	 * @param rno Row number where the details are present
	 * @throws IOException 
	 */
	public void searchForItem(int rno) throws IOException {
		String search_text;
		try {
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.rotate(ScreenOrientation.LANDSCAPE);//Rotating the screen for easier typing
			MobileElement search_textbox = (MobileElement) driver.findElement(By.id(search_textbox_id));

			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOf(search_textbox));
			search_textbox.click();
			MobileElement search_textbox_afterclick = (MobileElement) driver.findElement(By.id(search_textbox_id_after_click));
			search_textbox_afterclick.click();
			search_text = ExcelUtil.getCellData(rno, 8);
			search_textbox_afterclick.sendKeys(search_text);
			
			driver.rotate(ScreenOrientation.PORTRAIT);
			MobileElement tvdropdownoption = (MobileElement) driver.findElement(By.xpath(tv_drop_down_option_xpath));
			WebDriverWait wait1=new WebDriverWait(driver,20);
			wait1.until(ExpectedConditions.visibilityOf(tvdropdownoption));

			s.takeScreenshot("Search Item");
			tvdropdownoption.click();


		}catch (Exception e) {
			s.takeScreenshot("Error_Search_item");
			Log.info("Error while searching item");
		}

	}
	
	/**
	 * Method to navigate to the SignIn page through the Sign in button.
	 * @throws IOException
	 */
	public void clickOnSignIn() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		try
		{
			MobileElement sign_in_btn = (MobileElement) driver.findElement(By.id(sign_in_id));
			sign_in_btn.click();
			s.takeScreenshot("After Sign in");

		}catch(Exception e)
		{
			s.takeScreenshot("Error_Clicking_Signin");
			e.printStackTrace();
			Assert.fail("Error while clicking sign in");
		}
	}

}

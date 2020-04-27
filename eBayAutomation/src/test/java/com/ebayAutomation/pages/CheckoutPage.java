package com.ebayAutomation.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ebayAutomation.base.BaseClass;
import com.ebayAutomation.util.Log;
import com.ebayAutomation.util.ScreenshotCapture;

import io.appium.java_client.MobileElement;

public class CheckoutPage extends BaseClass {

	ScreenshotCapture s;
	private String item_desc_id = "com.ebay.mobile:id/item_title";
	private String purchase_opt_id = "com.ebay.mobile:id/purchase_link";
	
	/**
	 * Imports environmental setup from BaseClass.
	 * Initializes the Page objects.
	 * Initializes the ScreenshotCapture class
	 * @throws Exception
	 */

	public CheckoutPage() throws Exception {
		s = new ScreenshotCapture();
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to get the item description displayed in the Checkout page
	 * 
	 * @return String: item Description
	 * @throws IOException
	 */
	public String getItemDescription() throws IOException {
		try {
			MobileElement purchase = (MobileElement) driver.findElement(By.id(purchase_opt_id));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(purchase));
			MobileElement item_desc = (MobileElement) driver.findElement(By.id(item_desc_id));
			s.takeScreenshot("Checkout page");
			return item_desc.getText();
		} catch (Exception e) {
			s.takeScreenshot("Checkout page error");
			Log.info("Exception " + e);
		}

		return null;
	}

}

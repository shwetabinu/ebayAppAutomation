package com.ebayAutomation.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.ebayAutomation.base.BaseClass;
import com.ebayAutomation.util.ScreenshotCapture;

import io.appium.java_client.MobileElement;
import com.ebayAutomation.util.Log;

public class ShoppingCartPage extends BaseClass {

	ScreenshotCapture s;
	public ShoppingCartPage() throws Exception {
		s=new ScreenshotCapture();
		PageFactory.initElements(driver, this);
	}
	private String checkout_id="com.ebay.mobile:id/shopping_cart_checkout";
	
	/**
	 * Method to continue to CheckOut screen after adding the desired item to cart.
	 * @return boolean value whether the user can navigate to checkout screen.
	 * @throws IOException
	 */
	public boolean continueToCheckout() throws IOException
	{
		boolean result=true;
		try{
			
			MobileElement checkout=(MobileElement)driver.findElement(By.id(checkout_id));
			s.takeScreenshot("Shopping Cart Page");
			checkout.click();
		}catch(Exception e)
		{
			result=false;
			s.takeScreenshot("Error continue to checkout");
			Log.info("Exception :"+e);
		}
		return result;
	}

}

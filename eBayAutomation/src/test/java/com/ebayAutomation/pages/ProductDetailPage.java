package com.ebayAutomation.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.ebayAutomation.base.BaseClass;
import com.ebayAutomation.util.Other_Util;
import com.ebayAutomation.util.ScreenshotCapture;

import io.appium.java_client.MobileElement;
import com.ebayAutomation.util.Log;


public class ProductDetailPage extends BaseClass {

	
	private String page_resource_id = "com.ebay.mobile:id/toolbar_coordinator";
	private String addtocard_text = "Add to basket";
	private String item_detail_id = "com.ebay.mobile:id/textview_item_name";
	private String go_to_cart_id = "com.ebay.mobile:id/call_to_action_button";
	private String added_tocart_id = "com.ebay.mobile:id/card_title";
	ScreenshotCapture screen;


	public ProductDetailPage() throws Exception {
		PageFactory.initElements(driver, this);
		screen = new ScreenshotCapture();

	}

	/**
	 * Scrolls down to the Add to Cart button and clicks on it to add the item to
	 * cart
	 * 
	 * @throws IOException
	 */
	public boolean addToCart() throws IOException {
		boolean result = false;
		try {
			
			result=Other_Util.scrollToElementUsingScrollIntoView(page_resource_id, addtocard_text);
			screen.takeScreenshot("Product Detail Page");
			
		} catch (Exception e) {
			screen.takeScreenshot("Add to cart Option not visible");
			Log.info("Exception" + e);
		}
		return result;
	}

	
	/**
	 * 
	 * Verifies that the Added to Cart element is present
	 * @return boolean result
	 * @throws IOException
	 */
	public boolean verifyItemAddedToCart() throws IOException
	{
		boolean result = false;
		try{MobileElement added_to_cart = (MobileElement) driver.findElement(By.id(added_tocart_id));
		if (added_to_cart.isDisplayed()) {
			result = true;
			Log.info("Item successfully added to cart");
		}
		} catch (Exception e) {
			screen.takeScreenshot("Added to cart Error");
		Log.info("Exception" + e);
		}
	return result;
	}
	
	
	/**
	 * Method to get the Product Description of the item
	 * @return String: The product description
	 * @throws IOException
	 */
	public String getProductDescription() throws IOException {
		try {
			MobileElement item_title = (MobileElement) driver.findElement(By.id(item_detail_id));
			return item_title.getText();
		} catch (Exception e) {
			screen.takeScreenshot("Product Description Validation Error");
			Log.info("Exception" + e);
		}
		return null;
	}

	
	/**
	 * Method to navigate to the cart
	 * @return boolean value whether GoToCart option is present or not.
	 * @throws IOException
	 */
	public boolean clickOnGoToCart() throws IOException {
		boolean result=true;
		try {
			MobileElement go_to_cart = (MobileElement) driver.findElement(By.id(go_to_cart_id));
			go_to_cart.click();
		} catch (Exception e) {
			screen.takeScreenshot("Proceed to cart Error");
			Log.info("Exception" + e);
			result=false;
		}
		return result;
	}
	
}

package com.ebayAutomation.util;

import org.openqa.selenium.NoSuchElementException;

import com.ebayAutomation.base.BaseClass;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;


public class Other_Util extends BaseClass {

	public Other_Util() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method to scroll to an element with a particular text and clicks on the element
	 * 
	 * @param scrollableList resource id of the scrollable element.
	 * @param text to which the scroll needs to be performed
	 */
	public static boolean scrollToElementUsingScrollIntoView(String scrollableList, String text) {
		
		boolean result=true;

		try
		{
			MobileElement element = driver
		
				.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""
						+ scrollableList + "\")).scrollIntoView(" + "new UiSelector().text(\"" + text + "\"))"));

		element.click();
		}catch(NoSuchElementException e)
		{
			result=false;
		}
		
		return result;
		
	}

}

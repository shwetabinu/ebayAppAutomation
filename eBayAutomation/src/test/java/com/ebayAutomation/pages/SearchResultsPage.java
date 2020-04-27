package com.ebayAutomation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.ebayAutomation.base.BaseClass;
import com.ebayAutomation.util.ExcelUtil;
import com.ebayAutomation.util.Log;
import com.ebayAutomation.util.Other_Util;
import com.ebayAutomation.util.ScreenshotCapture;

import io.appium.java_client.MobileElement;


public class SearchResultsPage extends BaseClass {
	
	private String page_resource_id = "com.ebay.mobile:id/recycler";
	private String popup_id = "com.ebay.mobile:id/text_slot_1";
	private String shop_by_brand_id = "com.ebay.mobile:id/textview_header_title";
	ScreenshotCapture s;


	/**
	 * Imports environmental setup from BaseClass.
	 * Initializes the Page objects.
	 * @throws Exception
	 */
	public SearchResultsPage() throws Exception {
		s = new ScreenshotCapture();	
		PageFactory.initElements(driver, this);

	}

	
	/**
	 *  Method verifies if the Search Results page is displayed correctly by the heading
	 * @param rno Row number from where the test data is fetched
	 * @return boolean: Whether Heading is displayed correctly or not
	 * @throws Exception
	 */

	public boolean verifyShopByBrandHeading(int rno) throws Exception {
		boolean result = false;

		try {
			MobileElement save_popup = (MobileElement) driver.findElement(By.id(popup_id));
			save_popup.click();
			MobileElement shop_by_brand_header = (MobileElement) driver.findElement(By.id(shop_by_brand_id));
			if (shop_by_brand_header.isDisplayed())
				result = true;
			s.takeScreenshot("The Search Results Page");
		} catch (Exception e) {
			s.takeScreenshot("Search Page Heading Error");
			Log.info("Exception" + e);
		}

		return result;
	}

	/**
	 *  Method verifies if the specified item can be selected.
	 * @param rno Row number from where the item detail is read.
	 * @return boolean value whether item is selected or not.
	 * @throws Exception
	 */
	public boolean selectItem(int rno) throws Exception {
		boolean result = false;

		String item_detail;
		try {
			item_detail = ExcelUtil.getCellData(rno, 9);
			result = Other_Util.scrollToElementUsingScrollIntoView(page_resource_id, item_detail);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {

			s.takeScreenshot("Select Item Error");
			Log.info("Exception" + e);
		}
		return result;
	}

}

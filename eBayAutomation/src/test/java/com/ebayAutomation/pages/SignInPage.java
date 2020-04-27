package com.ebayAutomation.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.ebayAutomation.base.BaseClass;
import com.ebayAutomation.util.ScreenshotCapture;

import io.appium.java_client.MobileElement;
import com.ebayAutomation.util.Log;

public class SignInPage extends BaseClass {

	ScreenshotCapture s;
	private String signin_option_id = "com.ebay.mobile:id/button_classic";
	private String signin_option3_id = "com.ebay.mobile:id/button_facebook";
	private String signin_google_id = "com.google.android.gms:id/og_apd_internal_image_view";
	private String maybe_later_id = "com.ebay.mobile:id/bt_maybe_later";


	/**
	 * Imports environmental setup from BaseClass.
	 * Initializes the Page objects.
	 * @throws Exception
	 */
	public SignInPage() throws Exception {
		super();
		s = new ScreenshotCapture();
		PageFactory.initElements(driver, this);
	}


	/**
	 * Method to sign in through eBay account
	 * @throws IOException
	 */
	public void chooseSignInOption() throws IOException {
		try {
			MobileElement use_email_username = (MobileElement) driver.findElement(By.id(signin_option_id));
			use_email_username.click();
		} catch (Exception e) {
			s.takeScreenshot("chooseSignInOption Error");
			Log.info("Exception :" + e);
		}
	}
	
	/**
	 * Method to sign in through Google account to eBay.
	 * @throws IOException
	 */
	public void signInThroughGoogle() throws IOException {
		try {
			MobileElement use_gmail = (MobileElement) driver.findElement(By.id(signin_google_id));
			use_gmail.click();
		} catch (Exception e) {
			s.takeScreenshot("signInThroughGoogle Error");
			Log.info("Exception :" + e);
		}
	}

	/**
	 * Method to sign in through FaceBook account to eBay, provided the eBay app is previously linked to FaceBook.
	 * @throws IOException
	 */
	public void signInThroughFacebook() throws IOException {
		try {
			MobileElement signin_fb = (MobileElement) driver.findElement(By.id(signin_option3_id));
			signin_fb.click();
			MobileElement maybe_later = (MobileElement) driver.findElement(By.id(maybe_later_id));
			maybe_later.click();
		} catch (Exception e) {
			s.takeScreenshot("signInThroughFacebook Error");
			Log.info("Exception :" + e);
		}

	}
}

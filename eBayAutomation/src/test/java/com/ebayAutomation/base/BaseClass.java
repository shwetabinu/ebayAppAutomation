package com.ebayAutomation.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ebayAutomation.util.ExcelUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseClass {

	protected static AppiumDriver<MobileElement> driver;
	DesiredCapabilities caps = new DesiredCapabilities();
	public static final String currentDir = System.getProperty("user.dir");
	public static final String testDataExcelFileName = "testdata.xlsx";

	public BaseClass() throws Exception {
		ExcelUtil.setExcelFileSheet("TestCases");

	}

	/**
	 * Sets the capability for a real android device
	 * 
	 * @param platformName
	 * @param deviceName
	 * @param platformVersion
	 * @throws MalformedURLException
	 */
	public void android_device_setup(String platformName, String deviceName, String platformVersion)
			throws MalformedURLException {
		caps.setCapability("platformName", platformName);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("platformVersion", platformVersion);
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.ebay.mobile");
		caps.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(url, caps);

	}

	public void android_emulator_setup(String platformName, String deviceName, String platformVersion)
			throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", platformName);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("platformVersion", platformVersion);
		caps.setCapability("automationName", "UIAutomator2");
		caps.setCapability("connectHardwareKeyboard", false);
		caps.setCapability("toggleSoftwareKeyboard", true);

		if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
			caps.setCapability("app", currentDir + "/src/test/resources/apps/ebay-new.apk");
		} else if (Platform.getCurrent().toString().contains("WIN")) {
			caps.setCapability("app", currentDir + "\\src\\test\\resources\\apps\\ebay-new.apk");
		}

		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		driver = new AndroidDriver<MobileElement>(url, caps);

	}

	/**
	 * Initializes the app with the capabilities mentioned in the Test Data file
	 * 
	 * @param i rown number
	 * @throws Exception
	 */
	public void initApp(int i) throws Exception {
		String platformName, deviceName, platformVersion, dev;
		dev = ExcelUtil.getCellData(1, 1);
		platformName = ExcelUtil.getCellData(1, 2);
		deviceName = ExcelUtil.getCellData(1, 3);
		platformVersion = ExcelUtil.getCellData(1, 4);
		if (dev.equalsIgnoreCase("android_device"))
			android_device_setup(platformName, deviceName, platformVersion);
		else if (dev.equalsIgnoreCase("emulator"))
			android_emulator_setup(platformName, deviceName, platformVersion);

	}

	/**
	 * Method to close all the open sessions
	 */
	public void closeApp() {
		driver.quit();
	}

}

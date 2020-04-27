package com.ebayAutomation.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import com.ebayAutomation.base.BaseClass;


public class ScreenshotCapture extends BaseClass{
	
	/**
	 * Initializes the drive with the Page objects
	 * @throws Exception
	 */
	public ScreenshotCapture() throws Exception {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Takes Screenshots and stores them as a file under Screenshots folder with the title and TimeStamp
	 * @param name Short description of the Screenshot.
	 * @throws IOException
	 */
	public void takeScreenshot(String name) throws IOException {
		
		
		String folder_name = "Screenshots";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa"); 
		new File(folder_name).mkdir();
		String screenShotName = name + timeStamp.format(new Date()) +".png";
		FileUtils.copyFile(scrFile, new File(folder_name + "/" + screenShotName));
		}
	
	

}

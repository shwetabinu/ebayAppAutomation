package com.ebayAutomation.util;

import org.apache.log4j.Logger;

/**
 * Logger class to provide logging capability
 * 
 * @author shwetabinu
 *
 */
public class Log {

	// Initialize Log4j logs

	private final static Logger Log = Logger.getLogger(Log.class.getName());

	/**
	 * Prints the log for the start of the test case
	 * 
	 * @param sTestCaseName
	 */
	public static void startTestCase(String sTestCaseName) {

		Log.info("****************************************************************************************");

		Log.info("****************************************************************************************");

		Log.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");

		Log.info("****************************************************************************************");

		Log.info("****************************************************************************************");

	}

	/**
	 * Prints the log for the end of the test case
	 * 
	 * @param sTestCaseName
	 */
	public static void endTestCase(String sTestCaseName) {

		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");

		Log.info("X");

		Log.info("X");

		Log.info("X");

		Log.info("X");

	}

	public static void info(String message) {

		Log.info(message);

	}

	public static void warn(String message) {

		Log.warn(message);

	}

	public static void error(String message) {

		Log.error(message);

	}

	public static void fatal(String message) {

		Log.fatal(message);

	}

	public static void debug(String message) {

		Log.debug(message);

	}

}

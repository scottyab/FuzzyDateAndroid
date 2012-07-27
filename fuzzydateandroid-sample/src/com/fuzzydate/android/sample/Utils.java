/**
 * 
 */
package com.fuzzydate.android.sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class Utils {

	/**
	 * convert String to int
	 * 
	 * @param s
	 *            - string to convert
	 * @return int - int representation of s
	 */
	public static int stringToInt(final String s) {
		try {
			return Integer.parseInt(s.trim());
		} catch (NumberFormatException nfe) {
			Log.e("mubaloo", "Utils - stringToInt() - NumberFormatException: "
					+ nfe.getMessage());
			return 0;
		}
	}

	/**
	 * convert String to double
	 * 
	 * @param s
	 *            - string to convert
	 * @return int - double representation of s
	 */
	public static double stringToDouble(final String s) {
		try {
			return Double.parseDouble(s.trim());
		} catch (NumberFormatException nfe) {
			Log.e("mubaloo",
					"Utils - stringToDouble() - NumberFormatException: "
							+ nfe.getMessage());
			return 0;
		}
	}

	public static Long stringToLong(final String s) {
		return new Long(s.trim());
	}

	private static SimpleDateFormat yearOnlyDateformat = new SimpleDateFormat(
			"yyyy");

	/**
	 * Converts total no of secs to the no of mins and secs ie converts 61 secs
	 * to 1:01
	 * 
	 * @param inputSecs
	 * @return
	 */
	public static String secsToMinsSecs(int totalSecs) {

		int mins = totalSecs / 60;
		totalSecs = totalSecs - mins * 60;
		int secs = totalSecs;
		// pad is secs less than 10
		if (secs <= 9) {
			return mins + ":0" + secs;
		} else {
			return mins + ":" + secs;
		}
	}

	/**
	 * convert a String to a Date in the format specified format e.g. yyyy-MM-dd
	 * HH:mm:ss
	 * 
	 * @param stringToConvert
	 * @param dateFormat
	 * @return
	 */
	public static Date stringToDate(final String stringToConvert,
			final String dateFormat) {
		DateFormat formatter = new SimpleDateFormat(dateFormat);
		return stringToDate(stringToConvert, formatter);
	}

	/**
	 * convert a String to a Date in the format specified format e.g.
	 * yyyy-MM-dd'T'HH:mm:ss
	 * 
	 * 
	 * @param stringToConvert
	 * @param formatter
	 *            - existing format
	 * @return
	 */
	public static Date stringToDate(final String stringToConvert,
			final DateFormat formatter) {
		try {
			return formatter.parse(stringToConvert);
		} catch (Exception e) {
			return null;
		}
	}

}

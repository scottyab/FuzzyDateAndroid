package com.fuzzydate.android.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.MissingResourceException;

import org.junit.Test;

import android.content.Context;

import com.fuzzydate.android.impl.DefaultFuzzingConfiguration;
import com.fuzzydate.android.impl.FuzzyDateFormatterImpl;

/**
 * Test class for {@link FuzzyDateFormatterImpl}. Mostly black box testing.
 * 
 * @author ma�
 */
public class FuzzyDateFormatImplTest {

	/**
     * 
     */
	private static final int SECONDS_HOUR = 60 * 60;

	/**
     * 
     */
	private static final int SECONDS_DAY = SECONDS_HOUR * 24;

	/**
     * 
     */
	private static final int SECONDS_WEEK = SECONDS_DAY * 7;

	/**
     * 
     */
	private static final int SECONDS_MONTH = SECONDS_DAY * 30;

	/**
     * 
     */
	private static final int SECONDS_YEAR = SECONDS_DAY * 256;

	private Context mContext;

	public FuzzyDateFormatImplTest(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	/**
	 * Test method for
	 * {@link net.sf.jfuzzydate.impl.FuzzyDateFormatterImpl#formatDistance(java.util.Date)}
	 * .
	 */
	@Test
	public void testFormatDistanceDate() {
		FuzzyDateFormatterImpl impl = new FuzzyDateFormatterImpl(
				DefaultFuzzingConfiguration.getInstance(), mContext);
		Date aMinuteAgo = new Date(new Date().getTime() - (30 * 1000));
		Date in35Minutes = new Date(new Date().getTime() + (60 * 35 * 1000));

		try {
			assertEquals(impl.formatDistance(aMinuteAgo),
					impl.formatDistance(aMinuteAgo));
			assertEquals(impl.formatDistance(in35Minutes),
					impl.formatDistance(in35Minutes));
		} catch (MissingResourceException e) {
			System.out
					.println("Warning: Resource for default locale not available for testing.");
		}
	}

	/**
	 * Test method for
	 * {@link net.sf.jfuzzydate.impl.FuzzyDateFormatterImpl#formatDistance(java.util.Date, java.util.Locale)}
	 * .
	 */
	@Test
	public void testFormatDistanceDateLocale() {
		FuzzyDateFormatterImpl impl = new FuzzyDateFormatterImpl(
				DefaultFuzzingConfiguration.getInstance(), mContext);

		assertEquals("a minute ago", impl.formatDistance(new Date(new Date()
				.getTime() - (30 * 1000))));
		assertEquals("in a minute", impl.formatDistance(new Date(new Date()
				.getTime() + (30 * 1000))));
		assertEquals(
				"two minutes ago",
				impl.formatDistance(new Date(new Date().getTime()
						- (60 * 2 * 1000))));
		assertEquals(
				"in two minutes",
				impl.formatDistance(new Date(new Date().getTime()
						+ (60 * 2 * 1000))));
		assertEquals(
				"in 35 minutes",
				impl.formatDistance(new Date(new Date().getTime()
						+ (60 * 35 * 1000))));
		assertEquals(
				"in 3 hours",
				impl.formatDistance(new Date(new Date().getTime()
						+ (((3 * SECONDS_HOUR) + (60 * 20)) * 1000))));
		assertEquals(
				"a day ago",
				impl.formatDistance(new Date(new Date().getTime()
						- (SECONDS_DAY * 1000))));
		assertEquals(
				"in 5 days",
				impl.formatDistance(new Date(new Date().getTime()
						+ (SECONDS_DAY * 5 * 1000))));
		assertEquals(
				"a week ago",
				impl.formatDistance(new Date(new Date().getTime()
						- (SECONDS_DAY * 7 * 1000))));

	}

	/**
	 * Test method for
	 * {@link net.sf.jfuzzydate.impl.FuzzyDateFormatterImpl#formatDuration(long, java.util.Locale)}
	 * .
	 */
	@Test
	public void testFormatDurationLongLocale() {
		FuzzyDateFormatterImpl impl = new FuzzyDateFormatterImpl(
				DefaultFuzzingConfiguration.getInstance(), mContext);
		assertEquals("a minute", impl.formatDuration(30 * 1000));
		assertEquals("two minutes", impl.formatDuration(60 * 2 * 1000));
		assertEquals("35 minutes", impl.formatDuration(60 * 35 * 1000));
		assertEquals("a day", impl.formatDuration(SECONDS_DAY * 1000));
		assertEquals("5 days", impl.formatDuration(SECONDS_DAY * 5 * 1000));
		assertEquals("a week", impl.formatDuration(SECONDS_DAY * 7 * 1000));
	}

	/**
	 * Test method for
	 * {@link net.sf.jfuzzydate.impl.FuzzyDateFormatterImpl#FuzzyDateFormatImpl(net.sf.jfuzzydate.impl.DefaultFuzzingConfiguration)}
	 * .
	 */
	@Test
	public void testFuzzyDateFormatImpl() {
		FuzzyDateFormatterImpl impl = new FuzzyDateFormatterImpl(
				DefaultFuzzingConfiguration.getInstance(), mContext);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 2);
		cal.set(Calendar.MINUTE, 2);

		assertEquals("two o'clock", impl.format(cal.getTime()));

		cal.set(Calendar.HOUR, 1);
		cal.set(Calendar.MINUTE, 20);
		assertEquals("quarter past one", cal.getTime());

		cal.set(Calendar.HOUR, 20);
		cal.set(Calendar.MINUTE, 30);
		assertEquals("quarter to ", impl.formatDuration(60 * 35 * 1000));

	}
}

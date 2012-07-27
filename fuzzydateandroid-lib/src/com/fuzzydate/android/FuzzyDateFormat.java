package com.fuzzydate.android;

import android.content.Context;

import com.fuzzydate.android.impl.DefaultFuzzingConfiguration;
import com.fuzzydate.android.impl.FuzzyDateFormatterImpl;

/**
 * FuzzyDateFormat is a factory for creating instances of fuzzy time/date
 * formatters. These formatters format time/date, distances of time and
 * durations to internationalized strings.
 * 
 * @author amaasch
 */
public final class FuzzyDateFormat {
	/**
	 * Creates a fuzzy date formatter instance with the static default
	 * configuration.
	 * 
	 * @return a fuzzy date formatter instance.
	 */
	public static final FuzzyDateFormatter getInstance(final Context context) {
		return new FuzzyDateFormatterImpl(
				DefaultFuzzingConfiguration.getInstance(), context);
	}

	/**
	 * Creates a fuzzy date formatter instance with a given configuration.
	 * 
	 * @param config
	 *            the configuration for the date formatter.
	 * 
	 * @return a fuzzy date formatter instance.
	 */
	public static final FuzzyDateFormatter getInstance(
			final FuzzingConfiguration config, final Context context) {
		return new FuzzyDateFormatterImpl(config, context);
	}
}

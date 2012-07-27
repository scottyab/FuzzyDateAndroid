package com.fuzzydate.android.impl;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;

import com.fuzzydate.android.FuzzingConfiguration;
import com.fuzzydate.android.FuzzingStrength;
import com.fuzzydate.android.FuzzyDateFormatter;
import com.fuzzydate.android.R;

/**
 * Basic implementation for fuzzy date formatting.
 * 
 * @author amaasch
 * 
 * @see FuzzyDateFormatter
 */
public class FuzzyDateFormatterImpl implements FuzzyDateFormatter {

	/**
	 * The fuzzing configuration.
	 */
	private final FuzzingConfiguration config;
	private Context context;

	/**
	 * Creates a new FuzzyDateFormatterImpl object with the specified
	 * configuration.
	 * 
	 * @param config
	 *            the given configuration.
	 */
	public FuzzyDateFormatterImpl(final FuzzingConfiguration config,
			Context context) {
		this.config = config;
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#format(java.util.Date)
	 */
	public String format(final Date date) {
		return formatDate(date.getTime(), FuzzingStrength.NORMAL);
	}

	/**
	 * Formats a point in time.
	 * 
	 * e.g 15:15 becomes quarter past three
	 * 
	 * TODO: add exceptions for midday, midnight
	 * 
	 * @param time
	 *            a time to make fuzzy
	 * 
	 * @param strength
	 *            the strength of the fuzzyfication.
	 * 
	 * @return a string representing a point in time
	 */
	private String formatDate(final long time, final FuzzingStrength strength) {

		// e.g convert long of time to fuzzy six o'clock, quarter past six,
		// quarter to six
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);

		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int mins = cal.get(Calendar.MINUTE);

		// which pattern is it?
		int pattern = 0;
		if (mins <= 14) {
			pattern = R.string.fuzzy__quarters_0_singular;
		} else if (mins >= 15 && mins < 29) {
			pattern = R.string.fuzzy__quarters_1;
		} else if (mins >= 30 && mins < 44) {
			pattern = R.string.fuzzy__quarters_2_singular;
		} else if (mins > 45) {
			pattern = R.string.fuzzy__quarters_3_singular;
			// its now quart to so roll the date, and handle quarter to 1
			cal.roll(Calendar.HOUR_OF_DAY, true);
			hour = cal.get(Calendar.HOUR_OF_DAY);
		}

		// to match the upperbound and in with the ranges of dist/duration
		final int absHour = Math.abs(hour) * 1000;

		// pick the correct range based on the hour.
		for (final Range range : config.getHourRanges(strength)) {
			if (absHour == range.getUpperBound()) {
				return config.getFuzzyString(pattern, context,
						config.getFuzzyString(range, context, absHour));
			}
		}
		return config.getFuzzyString(R.string.fuzzy__cannot_find_time, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDistance(java.util.Date)
	 */
	public String formatDistance(final Date date) {
		return formatDistance(date.getTime() - new Date().getTime(),
				FuzzingStrength.NORMAL);
	}

	/**
	 * Formats a distance.
	 * 
	 * @param distanceSec
	 *            a relative distance (number of milliseconds). Negative
	 *            distances are assumed to be past values.
	 * @param strength
	 *            the strength of the fuzzyfication.
	 * @param locale
	 *            the locale for formatting.
	 * 
	 * @return a string representing a readable distance.
	 */
	private String formatDistance(final long distanceSec,
			final FuzzingStrength strength) {
		int pattern;

		if (distanceSec <= 0) {
			pattern = R.string.fuzzy__distance_past_pattern;
		} else {
			pattern = R.string.fuzzy__distance_future_pattern;
		}

		final long absDistance = Math.abs(distanceSec);

		for (final Range range : config.getDistanceRanges(strength)) {
			if (absDistance < range.getUpperBound()) {
				if (range.hasDivisor()) {
					final int parameter = Math.round(absDistance
							/ range.getParameterDivisor());

					return config.getFuzzyString(
							pattern,
							context,
							config.getFuzzyString(range, context,
									Integer.valueOf(parameter)));
				}

				return config.getFuzzyString(pattern, context,
						config.getFuzzyString(range, context));
			}
		}

		return config.getFuzzyString(pattern, context,
				config.getFuzzyString(Range.ETERNAL, context));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDuration(java.util.Date)
	 */
	public String formatDuration(final Date relative) {
		return formatDuration(relative.getTime(), FuzzingStrength.NORMAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.jfuzzydate.FuzzyDateFormatter#formatDuration(long)
	 */
	public String formatDuration(final long milliSeconds) {
		return formatDuration(milliSeconds, FuzzingStrength.NORMAL);
	}

	/**
	 * Formats a duration.
	 * 
	 * @param milliSeconds
	 *            the duration defined by a number of milliseconds. Negative
	 *            distances are assumed to be past values.
	 * @param strength
	 *            the strength of the fuzzyfication.
	 * 
	 * @return a string representing a readable duration.
	 */
	private String formatDuration(final long milliSeconds,
			final FuzzingStrength strength) {
		for (final Range range : config.getDurationRanges(strength)) {
			if (milliSeconds < range.getUpperBound()) {
				if (range.hasDivisor()) {
					final int parameter = Math.round(milliSeconds
							/ range.getParameterDivisor());

					return config.getFuzzyString(range, context,
							Integer.valueOf(parameter));
				}

				return config.getFuzzyString(range, context);
			}
		}
		return config.getFuzzyString(Range.ETERNAL, context);
	}

}

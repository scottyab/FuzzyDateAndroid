package com.fuzzydate.android.impl;

import android.content.Context;

import com.fuzzydate.android.FuzzingConfiguration;
import com.fuzzydate.android.FuzzingStrength;
import com.fuzzydate.android.R;

/**
 * A very simple and static configuration for fuzzy date formatting_
 * 
 * 
 * 
 * @author ma�
 */
public final class DefaultFuzzingConfiguration implements FuzzingConfiguration {
	// ~ Static fields/initializers
	// ---------------------------------------------

	/**
	 * The singleton instance_
	 */
	private static DefaultFuzzingConfiguration instance;

	// ~ Instance fields
	// --------------------------------------------------------

	/**
     * 
     */
	private final Range[] dist_normal;

	/**
     * 
     */
	private final Range[] dur_normal;

	private final Range[] hour_normal;

	// ~ Constructors
	// -----------------------------------------------------------

	/**
	 * Creates a new DefaultFuzzingConfiguration object_
	 */
	private DefaultFuzzingConfiguration() {
		final int min = 60;
		final int hour = 60 * min;
		final int day = 24 * hour;
		final int week = 7 * day;
		final int month = 30 * day;

		dur_normal = new Range[] {
				new Range(80, R.string.fuzzy__duration_minute_1),
				new Range(140, R.string.fuzzy__duration_minute_2),
				new Range(40 * min, R.string.fuzzy__duration_minute_x, min),
				new Range(90 * min, R.string.fuzzy__duration_hour_1),
				new Range(150 * min, R.string.fuzzy__duration_hour_2),
				new Range(day, R.string.fuzzy__duration_hour_x, hour),
				new Range(36 * hour, R.string.fuzzy__duration_day_1),
				new Range(60 * hour, R.string.fuzzy__duration_day_2),
				new Range(week, R.string.fuzzy__duration_day_x, day),
				new Range(11 * day, R.string.fuzzy__duration_week_1),
				new Range(18 * day, R.string.fuzzy__duration_week_2),
				new Range(4 * week, R.string.fuzzy__duration_week_x, week),
				new Range(45 * day, R.string.fuzzy__duration_month_1),
				new Range(75 * day, R.string.fuzzy__duration_month_2),
				new Range(300 * day, R.string.fuzzy__duration_month_x, month),
				new Range(Long.MAX_VALUE, R.string.fuzzy__duration_eternal) };

		dist_normal = new Range[] {
				new Range(80, R.string.fuzzy__distance_minute_1),
				new Range(140, R.string.fuzzy__distance_minute_2),
				new Range(40 * min, R.string.fuzzy__distance_minute_x, min),
				new Range(90 * min, R.string.fuzzy__distance_hour_1),
				new Range(150 * min, R.string.fuzzy__distance_hour_2),
				new Range(day, R.string.fuzzy__distance_hour_x, hour),
				new Range(36 * hour, R.string.fuzzy__distance_day_1),
				new Range(60 * hour, R.string.fuzzy__distance_day_2),
				new Range(week, R.string.fuzzy__distance_day_x, day),
				new Range(11 * day, R.string.fuzzy__distance_week_1),
				new Range(18 * day, R.string.fuzzy__distance_week_2),
				new Range(4 * week, R.string.fuzzy__distance_week_x, week),
				new Range(45 * day, R.string.fuzzy__distance_month_1),
				new Range(75 * day, R.string.fuzzy__distance_month_2),
				new Range(300 * day, R.string.fuzzy__distance_month_x, month),
				new Range(Long.MAX_VALUE, R.string.fuzzy__duration_eternal) };

		hour_normal = new Range[] { new Range(0, R.string.fuzzy__hour_0),
				new Range(1, R.string.fuzzy__hour_1),
				new Range(2, R.string.fuzzy__hour_2),
				new Range(3, R.string.fuzzy__hour_3),
				new Range(4, R.string.fuzzy__hour_4),
				new Range(5, R.string.fuzzy__hour_5),
				new Range(6, R.string.fuzzy__hour_6),
				new Range(7, R.string.fuzzy__hour_7),
				new Range(8, R.string.fuzzy__hour_8),
				new Range(9, R.string.fuzzy__hour_9),
				new Range(10, R.string.fuzzy__hour_10),
				new Range(11, R.string.fuzzy__hour_11),
				new Range(12, R.string.fuzzy__hour_12),
				new Range(13, R.string.fuzzy__hour_13),
				new Range(14, R.string.fuzzy__hour_14),
				new Range(15, R.string.fuzzy__hour_15),
				new Range(16, R.string.fuzzy__hour_16),
				new Range(17, R.string.fuzzy__hour_17),
				new Range(18, R.string.fuzzy__hour_18),
				new Range(19, R.string.fuzzy__hour_19),
				new Range(20, R.string.fuzzy__hour_20),
				new Range(21, R.string.fuzzy__hour_21),
				new Range(22, R.string.fuzzy__hour_22),
				new Range(23, R.string.fuzzy__hour_23) };

	}

	// ~ Methods
	// ----------------------------------------------------------------

	/**
	 * This static method returns a shared instance of this default
	 * configuration class_
	 * 
	 * @return a DefaultFuzzingConfiguration istance_
	 */
	public static DefaultFuzzingConfiguration getInstance() {
		if (instance == null) {
			instance = new DefaultFuzzingConfiguration();
		}

		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net_sf_jfuzzydate_FuzzingConfiguration#getDistanceRanges(net_sf_jfuzzydate
	 * _FuzzingStrength)
	 */
	public Range[] getDistanceRanges(FuzzingStrength strenght) {
		final Range[] ranges;

		switch (strenght) {
		case NORMAL:
			ranges = dist_normal;

			break;

		case STRONG:
			// TODO
			ranges = dist_normal;

			break;

		case EXTREME:
			// TODO
			ranges = dist_normal;

			break;

		default:
			ranges = dist_normal;
		}

		return ranges;
	}

	public Range[] getHourRanges(FuzzingStrength strenght) {
		final Range[] ranges;

		switch (strenght) {
		case NORMAL:
			ranges = hour_normal;

			break;

		case STRONG:
			// TODO
			ranges = hour_normal;

			break;

		case EXTREME:
			// TODO
			ranges = hour_normal;

			break;

		default:
			ranges = hour_normal;
		}

		return ranges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net_sf_jfuzzydate_FuzzingConfiguration#getDurationRanges(net_sf_jfuzzydate
	 * _FuzzingStrength)
	 */
	public Range[] getDurationRanges(FuzzingStrength strenght) {
		final Range[] ranges;

		switch (strenght) {
		case NORMAL:
			ranges = dur_normal;

			break;

		case STRONG:
			// TODO
			ranges = dur_normal;

			break;

		case EXTREME:
			// TODO
			ranges = dur_normal;

			break;

		default:
			ranges = dur_normal;
		}

		return ranges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net_sf_jfuzzydate_FuzzingConfiguration#getFuzzyString(net_sf_jfuzzydate
	 * _impl_Range, java_util_Locale, java_lang_Object[])
	 */
	public String getFuzzyString(final Range range, final Context context,
			final Object... params) {
		return context.getResources().getString(range.getLabelResId(), params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net_sf_jfuzzydate_FuzzingConfiguration#getFuzzyString(java_lang_String,
	 * java_util_Locale, java_lang_Object[])
	 */
	public String getFuzzyString(final int resId, final Context context,
			final Object... params) {

		// int resId = context.getResources().getIdentifier(pattern, "string",
		// context.getPackageName());

		return context.getResources().getString(resId, params);
	}
}

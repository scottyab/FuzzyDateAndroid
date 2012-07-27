package com.fuzzydate.android;

import android.content.Context;

import com.fuzzydate.android.impl.Range;

/**
 * An object that represents a configuration for date and duration fuzzing.
 * 
 * <p>
 * A fuzzing configuration is a set of ranges which are mapped to
 * internationalized strings (via String resources). Each of these ranges
 * represents a special human understanding of a distance of time or duration.
 * 
 * @author ma�
 */
public interface FuzzingConfiguration {
	// ~ Methods
	// ----------------------------------------------------------------

	/**
	 * This method returns an array of range objects. These are used to map
	 * ranges of relative distances of time to a string resource id.
	 * 
	 * @param strenght
	 *            the fuzzing strength defining the granularity of ranges to
	 *            return.
	 * 
	 * @return an array of ranges for distances of time.
	 */
	Range[] getDistanceRanges(FuzzingStrength strenght);

	/**
	 * This method returns an array of range objects. These are used to map
	 * ranges of relative durations of time to a string resource id.
	 * 
	 * @param strenght
	 *            the fuzzing strength defining the granularity of ranges to
	 *            return.
	 * 
	 * @return an array of ranges for duration of time.
	 */
	Range[] getDurationRanges(FuzzingStrength strenght);

	/**
	 * This method returns an array of range objects. These are used to map
	 * ranges of hours to a string resource id.
	 * 
	 * @param strenght
	 *            the fuzzing strength defining the granularity of ranges to
	 *            return.
	 * 
	 * @return an array of ranges for duration of time.
	 */
	Range[] getHourRanges(FuzzingStrength strenght);

	/**
	 * Get a fuzzy string with a given range
	 * 
	 * @param range
	 * @param context
	 *            android context used to lookup resources
	 * @param params
	 * 
	 * @return
	 */
	String getFuzzyString(Range range, Context context, Object... params);

	/**
	 * Get a fuzzy string with a given resource Id
	 * 
	 * @param resId
	 *            Resource Id of the string to use
	 * @param context
	 *            android context used to lookup resources
	 * @param params
	 * 
	 * @return
	 */
	String getFuzzyString(int resId, Context context, Object... params);
}

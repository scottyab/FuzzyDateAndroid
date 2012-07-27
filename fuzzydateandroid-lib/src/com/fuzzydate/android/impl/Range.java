package com.fuzzydate.android.impl;

import com.fuzzydate.android.R;

/**
 * A date/time range mapping time values up to an upper bound to an externalized
 * string.
 * 
 * @author amaasch (updated by scottyab)
 * 
 */
public class Range {

	/**
     * 
     */
	public static final Range ETERNAL = new Range(Long.MAX_VALUE,
			R.string.fuzzy__distance_eternal);

	/**
     * 
     */
	private Long parameterDivisor;

	/**
	 * Res id of the string label
	 */
	private final int labelResId;

	/**
     * 
     */
	private final long upperBound;

	/**
	 * Creates a new Range object with an upper binding and a reference to an
	 * externalized string.
	 * 
	 * @param upperBound
	 * @param bundleKey
	 */
	public Range(final long upperBound, final int bundleKey) {
		this.upperBound = upperBound * 1000;
		this.labelResId = bundleKey;
	}

	/**
	 * Creates a new Range object with an upper binding and a reference to an
	 * externalized string. This constructor method also sets a divisor, used to
	 * define the calculation of number values included in fuzzy strings.
	 * 
	 * @param upperBound
	 * @param bundleKey
	 * @param parameterDivisor
	 */
	public Range(final long upperBound, final int bundleKey,
			final long parameterDivisor) {
		this.upperBound = upperBound * 1000;
		this.labelResId = bundleKey;
		this.parameterDivisor = parameterDivisor * 1000;
	}

	/**
	 * 
	 * 
	 * @return the bundleKey
	 */
	public int getLabelResId() {
		return labelResId;
	}

	/**
	 * 
	 * 
	 * @return the parameterDivisor
	 */
	public long getParameterDivisor() {
		return parameterDivisor;
	}

	/**
	 * 
	 * 
	 * @return the upperBound
	 */
	public long getUpperBound() {
		return upperBound;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean hasDivisor() {
		return parameterDivisor != null;
	}
}

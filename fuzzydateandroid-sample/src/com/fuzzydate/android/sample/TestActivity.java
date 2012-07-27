package com.fuzzydate.android.sample;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fuzzydate.android.FuzzyDateFormatter;
import com.fuzzydate.android.impl.DefaultFuzzingConfiguration;
import com.fuzzydate.android.impl.FuzzyDateFormatterImpl;

/**
 * Allows simple testing of the Android Fuzzy date library
 * 
 * @author Scott Bown
 * 
 */
public class TestActivity extends FragmentActivity {

	protected static final String TAG = null;
	private TextView mResultsTextView;
	private Button mMakeFuzzyButton;
	private TextView mInputTime;
	private TextView mInputDate;
	private FuzzyDateFormatter mFuzzyDateFormatter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		mFuzzyDateFormatter = new FuzzyDateFormatterImpl(
				DefaultFuzzingConfiguration.getInstance(),
				getApplicationContext());

		initViews();
	}

	private void initViews() {
		mMakeFuzzyButton = (Button) findViewById(R.id.make_fuzzy_button);
		mResultsTextView = (TextView) findViewById(R.id.fuzzy_result);
		mInputTime = (TextView) findViewById(R.id.input_time);
		mInputDate = (TextView) findViewById(R.id.input_date);

		Date today = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm");

		mInputDate.setText(formatDate.format(today));
		mInputTime.setText(formatTime.format(today));

		mResultsTextView.setText(getString(
				R.string.activity_test_result_distance,
				calcFuzzyDateOfPhoneUptime()));

	}

	public void makeFuzzy(View v) {

		String dateText = mInputDate.getText().toString();
		String timeText = mInputTime.getText().toString();

		if (TextUtils.isEmpty(timeText)) {
			timeText = "0:00";
		}
		Date inputedDate = Utils.stringToDate(dateText + " " + timeText,
				"dd/MM/yyyy hh:mm");

		String fuzzyDistance = mFuzzyDateFormatter.formatDistance(inputedDate);

		String fuzzyDuration = mFuzzyDateFormatter.formatDuration(inputedDate
				.getTime() - System.currentTimeMillis());

		String fuzzyDate = mFuzzyDateFormatter.format(inputedDate);

		StringBuilder build = new StringBuilder();
		build.append("fuzzyDistance:" + fuzzyDistance);
		build.append("\nfuzzyDate:" + fuzzyDate);
		build.append("\nfuzzyDuration:" + fuzzyDuration);

		mResultsTextView.setText(build.toString());

	}

	/**
	 * simple method to calc the device uptime
	 * 
	 * @return
	 */
	private String calcFuzzyDateOfPhoneUptime() {
		String fuzzyDurationTimeText = mFuzzyDateFormatter
				.formatDuration(SystemClock.uptimeMillis());
		return "phone uptime: " + fuzzyDurationTimeText;
	}

	public void testFormatDistanceDate() {
		FuzzyDateFormatterImpl impl = new FuzzyDateFormatterImpl(
				DefaultFuzzingConfiguration.getInstance(),
				getApplicationContext());

		Date aMinuteAgo = new Date(new Date().getTime() - (30 * 1000));
		Date in35Minutes = new Date(new Date().getTime() + (60 * 35 * 1000));

		impl.formatDistance(aMinuteAgo);
		impl.formatDistance(in35Minutes);
	}

	TimePickerDialog.OnTimeSetListener mSetTimeCallBack = new OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mInputTime.setText(hourOfDay + ":" + minute);
		}
	};

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment(mSetTimeCallBack);
		newFragment.show(getSupportFragmentManager(), "timePicker");
	}

	public static class TimePickerFragment extends DialogFragment {

		private TimePickerDialog.OnTimeSetListener callback;

		// normally you'd use set args rather than pass arg in constructor but
		// this is just sample
		public TimePickerFragment(
				TimePickerDialog.OnTimeSetListener callListener) {
			callback = callListener;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), callback, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

	}

	DatePickerDialog.OnDateSetListener mSetDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			// adding 1 to monthOfYear as it starts from 0
			monthOfYear += 1;
			mInputDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
		}
	};

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment(mSetDateSetListener);
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}

	public static class DatePickerFragment extends DialogFragment {

		private DatePickerDialog.OnDateSetListener callback;

		// normally you'd use set args rather than pass arg in constructor but
		// this is just sample
		public DatePickerFragment(
				DatePickerDialog.OnDateSetListener dateSetListener) {
			callback = dateSetListener;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), callback, year, month,
					day);
		}
	}

}

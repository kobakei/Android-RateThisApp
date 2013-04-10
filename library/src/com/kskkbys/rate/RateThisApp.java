package com.kskkbys.rate;

import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.util.Log;

/**
 * RateThisApp<br>
 * A library to show the app rate dialog
 * @author Keisuke Kobayashi <k.kobayashi.122@gmail.com>
 *
 */
public class RateThisApp {
	
	private static final String TAG = RateThisApp.class.getSimpleName();
	
	private static final String PREF_NAME = "RateThisApp";
	private static final String KEY_INSTALL_DATE = "rta_install_date";
	private static final String KEY_LAUNCH_TIMES = "rta_launch_times";
	private static final String KEY_OPT_OUT = "rta_opt_out";
	
	private static Date mInstallDate = new Date();
	private static int mLaunchTimes = 0;
	private static boolean mOptOut = false;
	
	/**
	 * Days after installation until showing rate dialog
	 */
	public static final int INSTALL_DAYS = 7;
	/**
	 * App launching times until showing rate dialog
	 */
	public static final int LAUNCH_TIMES = 5;
	
	/**
	 * Call this API when the app is launched.<br>
	 * It is better to call this API in onStart() of the launcher activity.
	 */
	public static void onLaunchApp(Context context) {
		Log.v(TAG, "onLaunchApp");
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		// If it is the first launch, save the date in shared preference.
		if (pref.getLong(KEY_INSTALL_DATE, 0) == 0L) {
			Date now = new Date();
			editor.putLong(KEY_INSTALL_DATE, now.getTime());
			Log.v(TAG, "First install: " + now.toString());
		}
		// Increment launch times
		int launchTimes = pref.getInt(KEY_LAUNCH_TIMES, 0);
		launchTimes++;
		editor.putInt(KEY_LAUNCH_TIMES, launchTimes);
		Log.v(TAG, "Launch times; " + launchTimes);
		
		editor.commit();
		
		mInstallDate = new Date(pref.getLong(KEY_INSTALL_DATE, 0));
		mLaunchTimes = pref.getInt(KEY_LAUNCH_TIMES, 0);
		mOptOut = pref.getBoolean(KEY_OPT_OUT, false);
		
		printStatus(context);
	}
	
	/**
	 * Show the rate dialog if the criteria is satisfied
	 * @param context
	 */
	public static void showRateDialogIfNeeded(final Context context) {
		if (shouldShowRateDialog()) {
			showRateDialog(context);
		}
	}
	
	/**
	 * Check whether the rate dialog shoule be shown or not
	 * @return
	 */
	private static boolean shouldShowRateDialog() {
		if (mOptOut) {
			return false;
		} else {
			if (mLaunchTimes >= LAUNCH_TIMES) {
				return true;
			}
			int threshold = INSTALL_DAYS * 24 * 60 * 60 * 1000;	// msec
			if (new Date().getTime() - mInstallDate.getTime() >= threshold) {
				return true;
			}
			return false;
		}
	}
	
	/**
	 * Show the rate dialog
	 * @param context
	 */
	public static void showRateDialog(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.rta_dialog_title);
		builder.setMessage(R.string.rta_dialog_message);
		builder.setPositiveButton(R.string.rta_dialog_ok, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String appPackage = context.getPackageName();
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackage));
				context.startActivity(intent);
				setOptOut(context, true);
			}
		});
		builder.setNeutralButton(R.string.rta_dialog_cancel, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				clearSharedPreferences(context);
			}
		});
		builder.setNegativeButton(R.string.rta_dialog_no, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setOptOut(context, true);
			}
		});
		builder.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				clearSharedPreferences(context);
			}
		});
		builder.create().show();
	}
	
	/**
	 * Clear data in shared preferences.<br>
	 * This API is called when the rate dialog is approved or canceled.
	 * @param context
	 */
	private static void clearSharedPreferences(Context context) {
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.remove(KEY_INSTALL_DATE);
		editor.remove(KEY_LAUNCH_TIMES);
		editor.commit();
	}
	
	/**
	 * Set opt out flag. If it is true, the rate dialog will never shown unless app data is cleared.
	 * @param context
	 * @param optOut
	 */
	private static void setOptOut(final Context context, boolean optOut) {
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean(KEY_OPT_OUT, optOut);
		editor.commit();
	}
	
	/**
	 * Print values in SharedPreferences (used for debug)
	 * @param context
	 */
	private static void printStatus(final Context context) {
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Log.v(TAG, "*** Status ***");
		Log.v(TAG, "Install Date: " + new Date(pref.getLong(KEY_INSTALL_DATE, 0)));
		Log.v(TAG, "Launch Times: " + pref.getInt(KEY_LAUNCH_TIMES, 0));
		Log.v(TAG, "Opt out: " + pref.getBoolean(KEY_OPT_OUT, false));
	}
}

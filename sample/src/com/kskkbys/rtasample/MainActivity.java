package com.kskkbys.rtasample;

import com.kskkbys.rate.RateThisApp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * Sample application of RateThisApp
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		// Monitor launch times and interval from installation
		RateThisApp.onStart(this);
		// Show a dialog if criteria is satisfied
		RateThisApp.showRateDialogIfNeeded(this);
	}

}

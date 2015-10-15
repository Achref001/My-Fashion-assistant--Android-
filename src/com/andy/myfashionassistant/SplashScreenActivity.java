package com.andy.myfashionassistant;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.andy.myfashionassistant.fragments.MainFragment;
import com.andy.myfashionassistant.utils.Utils;
import com.facebook.AppEventsLogger;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.fashion.R;

public class SplashScreenActivity extends FragmentActivity {
	private MainFragment mainFragment;

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();

		Log.i("TAG", "HANIHOUNI");
		Session s = Session.getActiveSession();

		Log.i("TAG", "HANIHOUNI 2");
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				if (isNetworkAvailable() == false) {
					Intent i = new Intent(getApplicationContext(),
							NoNetwork.class);
					// Intent i = new Intent(getApplicationContext(),
					// NavigationMain.class);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_left);
					finish();
				} else {
					Session ses = Session.getActiveSession();
					if (ses.getActiveSession() != null) {
						Intent j = new Intent(getApplicationContext(),
								NavigationMain.class);
						overridePendingTransition(R.anim.slide_in_right,
								R.anim.slide_out_left);
						startActivity(j);
						
						finish();
					} else {
						Intent i = new Intent(getApplicationContext(),
								LoginFacebookActivity.class);
						overridePendingTransition(R.anim.slide_in_right,
								R.anim.slide_out_left);
						startActivity(i);
						
						finish();
					}
				}
			}
		}, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

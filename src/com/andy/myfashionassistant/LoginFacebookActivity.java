package com.andy.myfashionassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.andy.myfashionassistant.fragments.MainFragment;
import com.facebook.Session;
import com.fashion.R;

public class LoginFacebookActivity extends FragmentActivity{
	private MainFragment mainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new MainFragment();
	        getSupportFragmentManager()
	        .beginTransaction()
	        .replace(android.R.id.content, mainFragment)
	        .commit();
	    } else {
	        // Or set the fragment from restored state info
	        mainFragment = (MainFragment) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
		
	}
	@Override
	public void onResume() {
	    super.onResume();
	    // Set title
	    getActionBar()
	        .setTitle(R.string.app_name);
	}

}

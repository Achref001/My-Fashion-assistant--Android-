package com.andy.myfashionassistant;

import com.fashion.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class NoNetwork extends Activity {
	Button Refresh ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		
		setContentView(R.layout.no_network_connetion);
		Refresh = (Button) findViewById(R.id.buttonRefresh);
		Refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isNetworkAvailable()== false)
				{
					
				}
				else 
				{
					Intent i = new Intent(getApplicationContext(),LoginFacebookActivity.class);
					startActivity(i);
					finish();
				}
				
}
		});
	
	}
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}

package com.andy.myfashionassistant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.myfashionassistant.LoginFacebookActivity;
import com.andy.myfashionassistant.NavigationMain;
import com.andy.myfashionassistant.utils.Utils;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.fashion.R;

public class MainFragment extends Fragment {
	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;
	 View view;
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	   view = inflater.inflate(R.layout.facebook_activity, container, false);
	   LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	   authButton.setFragment(this);

	    return view;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	//	makeMeRequest(session);
		if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	        
	        Intent i = new Intent(getActivity(),NavigationMain.class);
	        i.addCategory(Intent.CATEGORY_HOME);
	        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			
			
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	        Intent i = new Intent(getActivity(),LoginFacebookActivity.class);
			startActivity(i);
	    }
	}
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	    

	
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	

}
  
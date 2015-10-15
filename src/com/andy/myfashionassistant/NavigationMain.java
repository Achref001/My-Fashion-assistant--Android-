package com.andy.myfashionassistant;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.myfashionassistant.adapter.NavigationAdapter;
import com.andy.myfashionassistant.fragments.CalendarFragment;
import com.andy.myfashionassistant.fragments.ClosetFragment;
import com.andy.myfashionassistant.fragments.MagazineFragment;
import com.andy.myfashionassistant.fragments.ShoppingFragment;
import com.andy.myfashionassistant.fragments.ViewPagerFragment;
import com.andy.myfashionassistant.utils.Constant;
import com.andy.myfashionassistant.utils.Menus;
import com.andy.myfashionassistant.utils.Utils;
import com.andy.myfashionassistant.wizard.WizActivity;
import com.facebook.AppEventsLogger;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;
import com.fashion.R;
public class NavigationMain extends ActionBarActivity{
		
    private int lastPosition = 0;
	private ListView listDrawer;    
		
	private int counterItemDownloads;
	private TextView txname;
	private ProfilePictureView imImage;
	private DrawerLayout layoutDrawer;		
	private LinearLayout linearDrawer;
	private RelativeLayout userDrawer;
	private NavigationAdapter navigationAdapter;
	private ActionBarDrawerToggleCompat drawerToggle;
	LoginButton authButton ;
	////
	private ProfilePictureView profilePictureView;
	private TextView userNameView;
	
	
	/////

		Session ses = Session.getActiveSession();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		getSupportActionBar().setIcon(R.drawable.logomini);
		setContentView(R.layout.navigation_main);		
		
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);		
        Log.i("TAG",Utils.userid);
        
        listDrawer = (ListView) findViewById(R.id.listDrawer);        
		linearDrawer = (LinearLayout) findViewById(R.id.linearDrawer);		
		layoutDrawer = (DrawerLayout) findViewById(R.id.layoutDrawer);	
		txname=(TextView) findViewById(R.id.tituloDrawer);
		txname.setText(Utils.username);
		imImage=(ProfilePictureView) findViewById(R.id.ImgDrawer);
		imImage.setProfileId(Utils.userid);
		//
		uiHelper = new UiLifecycleHelper(this, callback);
	    uiHelper.onCreate(savedInstanceState);
		profilePictureView = (ProfilePictureView) findViewById(R.id.ImgDrawer);
		profilePictureView.setCropped(true);
		// Find the user's name view
		userNameView = (TextView) findViewById(R.id.tituloDrawer);
		 Session session = Session.getActiveSession();
		    if (session != null && session.isOpened()) {
		        // Get the user's data
		        makeMeRequest(session);
		    }
		
		
		//
		userDrawer = (RelativeLayout) findViewById(R.id.userDrawer);
		userDrawer.setOnClickListener(userOnClick);
		
		if (listDrawer != null) {
			navigationAdapter = NavigationList.getNavigationAdapter(this);
		}
		
		listDrawer.setAdapter(navigationAdapter);
		listDrawer.setOnItemClickListener(new DrawerItemClickListener());
		drawerToggle = new ActionBarDrawerToggleCompat(this, layoutDrawer);		
		layoutDrawer.setDrawerListener(drawerToggle);
       		
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		if (savedInstanceState != null) { 			
			setLastPosition(savedInstanceState.getInt(Constant.LAST_POSITION)); 				
			
			if (lastPosition < 5){
				navigationAdapter.resetarCheck();			
				navigationAdapter.setChecked(lastPosition, true);
			}    	
			 
	    }else{
	    	
	    	setLastPosition(lastPosition); 
	    	setFragmentList(lastPosition);
	    	
/*	    	getSupportFragmentManager().beginTransaction()
            .replace(R.id.content_frame, new WeatherFragment())
            .commit();*/
	    	
	    }	
		
		
	}
	@Override
	protected void onPause() {
	  super.onPause();
	  uiHelper.onPause();
	  // Logs 'app deactivate' App Event.
	  AppEventsLogger.deactivateApp(this);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub		
		super.onSaveInstanceState(outState);		
		outState.putInt(Constant.LAST_POSITION, lastPosition);					
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {  
		/*if(item.getItemId() == R.id.change_city){
	       // showInputDialog();
	    }*/
	    //return false;
		
        if (drawerToggle.onOptionsItemSelected(item)) {
              return true;
        }
        
        /*
		switch (item.getItemId()) {		
		case Menus.HOME:
			if (layoutDrawer.isDrawerOpen(linearDrawer)) {
				layoutDrawer.closeDrawer(linearDrawer);
			} else {
				layoutDrawer.openDrawer(linearDrawer);
			}
			return true;
		
		case menu.weather:
			if(item.getItemId() == R.id.change_city){
		        showInputDialog();
		    }
			return true;
		default:*/
			return super.onOptionsItemSelected(item);			
		//}	
			
    }
		
    
	
	public void setTitleActionBar(CharSequence informacao) {    	
    	getSupportActionBar().setTitle(informacao);
    }	
	
	public void setSubtitleActionBar(CharSequence informacao) {    	
    	getSupportActionBar().setSubtitle(informacao);
    }	
	public void setIconActionBar(int icon) {    	
    	getSupportActionBar().setIcon(icon);
    }	
	
	public void setLastPosition(int posicao){		
		this.lastPosition = posicao;
	}	
		
	private class ActionBarDrawerToggleCompat extends ActionBarDrawerToggle {
		public ActionBarDrawerToggleCompat(Activity mActivity, DrawerLayout mDrawerLayout){
			super(
			    mActivity,
			    mDrawerLayout, 
  			    R.drawable.ic_action_navigation_drawer, 
				R.string.drawer_open,
				R.string.drawer_close);
		}
		
		@Override
		public void onDrawerClosed(View view) {			
			supportInvalidateOptionsMenu();				
		}
		@Override
		public void onDrawerOpened(View drawerView) {	
			navigationAdapter.notifyDataSetChanged();			
			supportInvalidateOptionsMenu();			
		}		
	}
		  
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);		
	}
	
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {  
        	FragmentManager fragmentManager = getSupportFragmentManager();
	    	setLastPosition(posicao);     
	    	
	    	setFragmentList(lastPosition);	    	
	    	layoutDrawer.closeDrawer(linearDrawer);	    	
        }
    }	
    
	
    private OnClickListener userOnClick = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			layoutDrawer.closeDrawer(linearDrawer);
		}
	};	
	
	private void setFragmentList(int position){
		
		FragmentManager fragmentManager = getSupportFragmentManager();							
		
		switch (position) {
		case 0:			
			fragmentManager.beginTransaction().replace(R.id.content_frame, new ViewPagerFragment()).commit();
			break;					
		case 1:			
			//fragmentManager.beginTransaction().replace(R.id.content_frame, new AssitanceFragment()).commit();
			//startActivity(new Intent(this, WizActivity.class));
			Intent i = new Intent(NavigationMain.this , WizActivity.class);
			startActivity(i);
			break;
			
		case 2:			
			fragmentManager.beginTransaction().replace(R.id.content_frame, new ClosetFragment()).commit();
			break;	
		case 3:			
			fragmentManager.beginTransaction().replace(R.id.content_frame, new CalendarFragment()).commit();						
			break;
		case 4:			
			fragmentManager.beginTransaction().replace(R.id.content_frame, new ShoppingFragment()).commit();						
			break;
		case 5:
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy); 
			fragmentManager.beginTransaction().replace(R.id.content_frame, new MagazineFragment() ).commit();						
			break;
		case 6:
			/*fragmentManager.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();*/
			ses.closeAndClearTokenInformation();
			break;
		
		
		
		default:
			Toast.makeText(getApplicationContext(), "implement other fragments here", Toast.LENGTH_SHORT).show();
			break;	
		}			
	
		if (position < 5){
			navigationAdapter.resetarCheck();			
			navigationAdapter.setChecked(position, true);
		}
	}
   private void hideMenus(Menu menu, int posicao) {
    	    	
        boolean drawerOpen = layoutDrawer.isDrawerOpen(linearDrawer);    	
    	
        switch (posicao) {
        case 0:        
	        menu.findItem(Menus.CITY).setVisible(!drawerOpen);
	         
			break;
        case 1:        
	        menu.findItem(Menus.CITY).setVisible(!drawerOpen);
   
			break;
			
		case 2:
			menu.findItem(Menus.CITY).setVisible(!drawerOpen);
     			
			break;				
		case 3:        
	        menu.findItem(Menus.CITY).setVisible(!drawerOpen);
 
			break;
		case 4:        
	        menu.findItem(Menus.CITY).setVisible(!drawerOpen);
   
			break;
		case 5:        
	        menu.findItem(Menus.CITY).setVisible(!drawerOpen);
  
			break;
		}          
    }	
	public void setTitleFragments(int position){	
		setIconActionBar(Utils.iconNavigation[position]);
		setSubtitleActionBar(Utils.getTitleItem(NavigationMain.this, position));				
	}
	public int getCounterItemDownloads() {
		return counterItemDownloads;
	}
	public void setCounterItemDownloads(int value) {
		this.counterItemDownloads = value;
	}
	
	////////////////
	private void makeMeRequest(final Session session) {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
	    Request request = Request.newMeRequest(session, 
	            new Request.GraphUserCallback() {
	        @Override
	        public void onCompleted(GraphUser user, Response response) {
	            // If the response is successful
	            if (session == Session.getActiveSession()) {
	                if (user != null) {
	                    // Set the id for the ProfilePictureView
	                    // view that in turn displays the profile picture.
	                    profilePictureView.setProfileId(user.getId());
	                    Utils.userid = user.getId();
	                    // Set the Textview's text to the user's name.
	                    userNameView.setText(user.getName());
	                    Utils.username = user.getName();
	                    
	                }
	            }
	            if (response.getError() != null) {
	                // Handle errors, will do so later.
	            }
	        }
	    });
	    request.executeAsync();
	}
	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
	    if (session != null && session.isOpened()) {
	        // Get the user's data.
	        makeMeRequest(session);
	    }
	    if (state.isClosed()) {
	        Log.i("", "Logged out...");
	        Intent i = new Intent(NavigationMain.this,LoginFacebookActivity.class);
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
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(final Session session, final SessionState state, final Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	
} 
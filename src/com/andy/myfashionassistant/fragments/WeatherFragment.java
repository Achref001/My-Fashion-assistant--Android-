package com.andy.myfashionassistant.fragments;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.myfashionassistant.utils.CityPreference;
import com.andy.myfashionassistant.utils.RemoteFetch;
import com.andy.myfashionassistant.wizard.WizActivity;
import com.capricorn.ArcMenu;
import com.capricorn.RayMenu;
import com.fashion.R;


public class WeatherFragment extends Fragment {
	Typeface weatherFont;
    
    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
    FragmentManager fragmentManager ;
    Handler handler;
    private static final int[] ITEM_DRAWABLES = { R.drawable.share,
		R.drawable.info, R.drawable.aboutus };

    ArcMenu rayMenu;
    public WeatherFragment(){   
        handler = new Handler();
    }
    
    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }
 
    public boolean onOptionsItemSelected(MenuItem item) {  
		if(item.getItemId() == R.id.change_city){
	       showInputDialog();
	    }
		return false;
    }
    @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);	
		menu.clear();
		inflater.inflate(R.menu.menu, menu);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.one_fragment, container, false);
       
        /*SharedPreferences prefs = getActivity().getSharedPreferences("weather", getActivity().MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("mweather", currentTemperatureField.getText().toString());*/
        
		cityField = (TextView)rootView.findViewById(R.id.city_field);
        
        updatedField = (TextView)rootView.findViewById(R.id.updated_field);
        detailsField = (TextView)rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)rootView.findViewById(R.id.weather_icon);
        rayMenu = (ArcMenu) rootView.findViewById(R.id.ray_menu);
        drawRayMenu();
        fragmentManager = getFragmentManager();
        weatherIcon.setTypeface(weatherFont);
        return rootView; 
    }
    
    
    private void drawRayMenu() {
		// TODO Auto-generated method stub
    	final int itemCount = ITEM_DRAWABLES.length;
		for (int i = 0; i < itemCount; i++) {
			ImageView item = new ImageView(getActivity());
			item.setImageResource(ITEM_DRAWABLES[i]);

			final int position = i;
			rayMenu.addItem(item, new OnClickListener() {

				@Override
				public void onClick(View v) {
					clicked(position);
				}
			});// Add a menu itemS
		}
		
	}
    	public void clicked(int i){
		
		Bundle args = new Bundle();
		
		 if(i==0)
		{
			
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "I started using my fashion assistant and it's a very cool application");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			
			
			
		}
		else if(i==1)
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

			alert.setTitle("Information");
			alert.setMessage("My fashion assistant is an application that helps you to dress well");

			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			  }
			});

			alert.show();
			
		}
		else if(i==2)
		{
			/*AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
			 LayoutInflater inflater = getActivity().getLayoutInflater();
			 builder.setView(inflater.inflate(R.layout.camera_gallery, null));
		//	dialog.setContentView(R.layout.camera_gallery);
			 bui.setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // sign in the user ...
	               }
	           });
			.setTitle("Title...");

			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.text_gallery);
			text.setText("Achref Gassoumi");
			ImageView image = (ImageView) dialog.findViewById(R.id.image_gallery);
			image.setImageResource(R.drawable.gass);
			
			TextView text2 = (TextView) dialog.findViewById(R.id.text_camera);
			text.setText("Yassine Kharraz");
			ImageView image2 = (ImageView) dialog.findViewById(R.id.image_camera);
			image.setImageResource(R.drawable.yass);

			Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			dialog.show();
		  }*/
			
			
			
			
			AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

			alert.setTitle("About us");
			alert.setMessage("this application was created by yagass");

			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			  }
			});

			alert.show();
			
		}
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");     
        updateWeatherData(new CityPreference(getActivity()).getCity());
    }
    
    
    
    public void updateWeatherData(final String city){
        new Thread(){
            public void run(){
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getActivity(), 
                                    getActivity().getString(R.string.place_not_found), 
                                    Toast.LENGTH_LONG).show(); 
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                        }
                    });
                }               
            }
        }.start();
        setHasOptionsMenu(true);
    }
    
 
	public void renderWeather(JSONObject json){
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) + 
                    ", " + 
                    json.getJSONObject("sys").getString("country"));
             
            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            detailsField.setText(
                    details.getString("description").toUpperCase(Locale.US) +
                    "\n" + "Humidity: " + main.getString("humidity") + "%" +
                    "\n" + "Pressure: " + main.getString("pressure") + " hPa");
             
            currentTemperatureField.setText(
                        String.format("%.2f", main.getDouble("temp"))+ " °C");
            Log.e("tmpttttttttttttttttttttttttttt",main.getDouble("temp")+"");
            SharedPreferences prefs = getActivity().getSharedPreferences("weather", getActivity().MODE_PRIVATE);
    		SharedPreferences.Editor editor = prefs.edit();
    		editor.putFloat("mweather", (float) main.getDouble("temp"));
            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            updatedField.setText("Last update: " + updatedOn);
     
            setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);
             
        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }
    
    public void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
            case 2 : icon = getActivity().getString(R.string.weather_thunder);
                     break;         
            case 3 : icon = getActivity().getString(R.string.weather_drizzle);
                     break;     
            case 7 : icon = getActivity().getString(R.string.weather_foggy);
                     break;
            case 8 : icon = getActivity().getString(R.string.weather_cloudy);
                     break;
            case 6 : icon = getActivity().getString(R.string.weather_snowy);
                     break;
            case 5 : icon = getActivity().getString(R.string.weather_rainy);
                     break;
            }
        }
        weatherIcon.setText(icon);
    }
    
    public void showInputDialog(){
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle("Change your city");
	    final EditText input = new EditText(getActivity());
	    input.setInputType(InputType.TYPE_CLASS_TEXT);
	    builder.setView(input);
	    builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	updatechangeCity(input.getText().toString());
	        }
	    });
	    builder.show();
	}
	 
	public void changeCity(String city){
	    WeatherFragment wf = (WeatherFragment)getActivity().getSupportFragmentManager()
	                            .findFragmentById(R.id.container);
	    wf.changeCity(city);
	    new CityPreference(getActivity()).setCity(city);
	}
    
    public void updatechangeCity(String city){
        updateWeatherData(city);
    }
    
    
    

}

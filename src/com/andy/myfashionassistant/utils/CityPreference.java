package com.andy.myfashionassistant.utils;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {
	SharedPreferences prefs;
    
    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }
     
    // If the user has not chosen a city yet, return
    // Sydney as the default city
    public String getCity(){
        return prefs.getString("city", "Tunis, Tn");        
    }
     
    public void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }

}

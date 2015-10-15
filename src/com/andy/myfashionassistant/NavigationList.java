package com.andy.myfashionassistant;

import android.content.Context;

import com.andy.myfashionassistant.adapter.NavigationAdapter;
import com.andy.myfashionassistant.adapter.NavigationItemAdapter;
import com.andy.myfashionassistant.utils.Utils;
import com.fashion.R;

public class NavigationList {
	
	public static NavigationAdapter getNavigationAdapter(Context context){
		
		NavigationAdapter navigationAdapter = new NavigationAdapter(context);		
		String[] menuItems = context.getResources().getStringArray(R.array.nav_menu_items);
		
		for (int i = 0; i < menuItems.length; i++) {
			
			String title = menuItems[i];				
			NavigationItemAdapter itemNavigation;				
			itemNavigation = new NavigationItemAdapter(title, Utils.iconNavigation[i]);									
			navigationAdapter.addItem(itemNavigation);
		}		
		return navigationAdapter;			
	}	
}

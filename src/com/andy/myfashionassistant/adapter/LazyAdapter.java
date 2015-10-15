package com.andy.myfashionassistant.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.myfashionassistant.fragments.MagazineFragment;
import com.andy.myfashionassistant.utils.ImageLoader;
import com.fashion.R;


public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.rss_list_row, parent,false);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); //  name
        TextView category = (TextView)vi.findViewById(R.id.category); 
        TextView datepub = (TextView)vi.findViewById(R.id.datepub); 

        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        
        HashMap<String, String> event = new HashMap<String, String>();
        event = data.get(position);
        
        // Setting all values in listview
        
       title.setText(event.get(MagazineFragment.title));
     //  artist.setText(event.get(EventList.description));
    //   category.setText("Category :"+event.get(EventList.category));
       datepub.setText("PubDate :"+event.get(MagazineFragment.pubDate));

      imageLoader.DisplayImage(event.get(MagazineFragment.imgpath), thumb_image);

        return vi;
        }

	
}
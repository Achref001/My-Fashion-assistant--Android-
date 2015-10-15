package com.andy.myfashionassistant.adapter;

import java.io.ByteArrayInputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.myfashionassistant.entities.Calendrier;
import com.fashion.R;


public class calendrierAdapter extends ArrayAdapter<Calendrier>{

	Context context; 
	int layoutResourceId;    
	List<Calendrier> calendriers = null;
	private LayoutInflater inflater;


	public calendrierAdapter(Context context, int layoutResourceId, List<Calendrier> calendriers) {
		super(context, layoutResourceId, calendriers);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.calendriers = calendriers;
	
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView = convertView;
		if (itemView == null)
		  {
			  itemView = inflater.inflate(layoutResourceId,parent, false);
		  }

		// find "Shopping" to work with
		Calendrier calendrier = calendriers.get(position);

		// fill image1
		
		ImageView imageView1 = (ImageView)itemView.findViewById(R.id.imagecalendar);
		byte[] outImage=calendrier.getPhoto();
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		imageView1.setImageBitmap(theImage);

		// fill the textView
		TextView txtShopping = (TextView)itemView.findViewById(R.id.textViewCalendrier);
		txtShopping.setText(calendrier.getTexte());

		

		return itemView;
	}
}




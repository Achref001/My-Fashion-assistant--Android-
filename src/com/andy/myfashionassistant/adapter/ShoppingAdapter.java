package com.andy.myfashionassistant.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.myfashionassistant.entities.shopping;
import com.fashion.R;



public class ShoppingAdapter extends ArrayAdapter<shopping> {
	Context context;
	int layoutResourceId=0;
	List<shopping> Shop = null;
	private LayoutInflater inflater;

	public  ShoppingAdapter(Context context, int layoutResourceId,
			List<shopping> Shop) {
		super(context, layoutResourceId, Shop);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.Shop = Shop;
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
		shopping currentShopping = Shop.get(position);

		// fill image1
		ImageView imageView1 = (ImageView) itemView
				.findViewById(R.id.imageViewShopping);
		imageView1.setImageResource(currentShopping.getIconId());

		// fill the textView
		TextView txtShopping = (TextView) itemView.findViewById(R.id.textShopping);
		txtShopping.setText(currentShopping.getClothesType());

		

		return itemView;
	}
}




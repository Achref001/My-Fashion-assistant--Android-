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

import com.andy.myfashionassistant.entities.Vetement;
import com.fashion.R;




public class GassAdapter extends ArrayAdapter<Vetement> {
	Context context;
	int layoutResourceId=0;
	List<Vetement> Shop = null;
	private LayoutInflater inflater;

	public  GassAdapter(Context context, int layoutResourceId,
			List<Vetement> Shop) {
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
		Vetement currentShopping = Shop.get(position);

		// fill image1
		Vetement vetement = Shop.get(position);
		byte[] outImage=vetement.getLogo();
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		
		ImageView imageView1 = (ImageView) itemView
				.findViewById(R.id.mlist_image);
		imageView1.setImageBitmap(theImage);

		// fill the textView
		TextView txtShopping = (TextView) itemView.findViewById(R.id.mlist_title);
		txtShopping.setText(currentShopping.getTitle());

		// fill the textView
				TextView txtColor = (TextView) itemView.findViewById(R.id.msimilar_color);
				txtColor.setText(currentShopping.getType());

		return itemView;
	}
}




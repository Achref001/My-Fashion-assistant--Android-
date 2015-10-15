package com.andy.myfashionassistant.adapter;

import java.io.ByteArrayInputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.andy.myfashionassistant.entities.Vetement;
import com.fashion.R;

public class TodayAdapter extends ArrayAdapter<Vetement>{

	Context context; 
	int layoutResourceId;    
	List<Vetement> vetements = null;
	

	public TodayAdapter(Context context, int layoutResourceId, List<Vetement> vetements) {
		super(context, layoutResourceId, vetements);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.vetements = vetements;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		VetementHolder holder = null;

		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new VetementHolder();
			holder.image = (ImageView)row.findViewById(R.id.image);
			holder.titre = (TextView)row.findViewById(R.id.titre);
//			holder.flag = (RadioButton)row.findViewById(R.id.flag);
			row.setTag(holder);
		}
		else
		{
			holder = (VetementHolder)row.getTag();
		}


		Vetement vetement = vetements.get(position);
		byte[] outImage=vetement.getLogo();
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		holder.image.setImageBitmap(theImage);

		//holder.image.setImageResource(vetement.getResImage());
		holder.titre.setText(vetement.getTitle());
//		holder.flag.setSelected(false);
		

		return row;
	}

	class VetementHolder
	{
		ImageView image;
		TextView titre;
//		RadioButton flag;

	}
}
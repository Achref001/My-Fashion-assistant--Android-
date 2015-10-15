package com.andy.myfashionassistant.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.myfashionassistant.entities.Vetement;
import com.andy.myfashionassistant.utils.Utilities;
import com.fashion.R;



public class VetementAdapter extends ArrayAdapter<Vetement> {
	
	public final static String TAG = "Vetement";
	private int resourceId = 0;
	private LayoutInflater inflater;

	public VetementAdapter(Context context, int resourceId,
			List<Vetement> vetements) {
		super(context, 0, vetements);
		this.resourceId = resourceId;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;
		ImageView imgLogo;
		TextView textName, textType;

		view = inflater.inflate(resourceId, parent, false);

		try {
			textName = (TextView) view.findViewById(R.id.titleItem);
			textType = (TextView) view.findViewById(R.id.typeItem);
			imgLogo = (ImageView) view.findViewById(R.id.imgItem);
		} catch (ClassCastException e) {
			throw e;
		}

		Vetement item = getItem(position);

		textName.setText(item.getTitle());
		textType.setText("TYPE : " + item.getType());
		imgLogo.setImageBitmap(Utilities.getImage(item.getLogo()));

		return view;
	}

}

package com.andy.myfashionassistant.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.myfashionassistant.entities.Vetement;
import com.andy.myfashionassistant.sqlLite.VetementBDD;
import com.andy.myfashionassistant.utils.MyApp;
import com.andy.myfashionassistant.utils.Utilities;
import com.fashion.R;

public class ClosetFragment extends Fragment{

	static Bitmap bmp = null;
	String type = null;
	String titre = null;

	ImageView imageView;
	TextView titreView;
	Spinner typeView;
	Button btnAdd = null;
	VetementBDD vetementBDD;
	ImageButton img;
	List<Vetement> vetementList;
	
	
	View rootView;
	MyApp app;
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		 rootView = inflater.inflate(R.layout.add_vetementfragment, container, false);
		
		imageView = (ImageView)rootView. findViewById(R.id.imageView);
		titreView = (EditText)rootView. findViewById(R.id.titreVetement);
		typeView = (Spinner)rootView. findViewById(R.id.sp);
		titre = titreView.getText().toString();
		img =(ImageButton)rootView. findViewById(R.id.image);
		getActivity().getActionBar().setTitle("");
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(t,37);
			}
		});
		//app = (MyApp) getActivity().getApplication();
		

		return rootView;
	}

	

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 37 && resultCode == Activity.RESULT_OK) {
			bmp = (Bitmap) data.getExtras().get("data");
			
			ImageButton im = (ImageButton)rootView.findViewById(R.id.image);
			im.setScaleType(ScaleType.CENTER_CROP);
			im.setImageBitmap(bmp);
		}
	}

	@Override
	public void onResume() {
	    super.onResume();
	    // Set title
	    getActivity().getActionBar()
	        .setTitle(R.string.app_name);
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			
		inflater.inflate(R.menu.vetement, menu);
		
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_add:
			
			type = typeView.getSelectedItem().toString();
			vetementBDD = new VetementBDD(getActivity());
			vetementBDD.open();
			vetementList = vetementBDD.selectAll();
			vetementBDD.close();
			int i= 0 ;
			titre = titreView.getText().toString()+vetementList.size();
			if (bmp != null && titre!=null) {
			//else{
				// TODO ADD new Artcile
				Vetement vetement = new Vetement(type, titre,
						Utilities.getBytes(bmp));
				vetementBDD = new VetementBDD(getActivity());
				vetementBDD.open();
				vetementBDD.insertTop(vetement);
				Toast.makeText(getActivity(), "Clothes Saved",
						Toast.LENGTH_LONG).show();
				vetementBDD.close();
				i++;
			}
			else 
			{
				AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

				alert.setTitle("My Fashion Assistance");
				alert.setMessage("you miss some required fields");

				alert.setNegativeButton("ok", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				  }
				});

				alert.show();
				i++;
			}
			return true;
		case R.id.action_list:
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
            .replace(R.id.content_frame, new ListClosetFragment())
            .commit();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
		
}

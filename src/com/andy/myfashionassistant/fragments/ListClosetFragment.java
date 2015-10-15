package com.andy.myfashionassistant.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.andy.myfashionassistant.adapter.VetementAdapter;
import com.andy.myfashionassistant.entities.Vetement;
import com.andy.myfashionassistant.sqlLite.VetementBDD;
import com.andy.myfashionassistant.utils.MyApp;
import com.fashion.R;

public class ListClosetFragment  extends  Fragment{
	
	VetementBDD vetementBDD;
	List<Vetement> vetementList;
	ListView listViewVetement;
	VetementAdapter adapter;

	MyApp app;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		 rootView = inflater.inflate(R.layout.list_vetementfragment, container, false);
		 
		 setHasOptionsMenu(true);
		 vetementList = new ArrayList<Vetement>();
			vetementBDD = new VetementBDD(getActivity());
			vetementBDD.open();
			vetementList = vetementBDD.selectAll();
			vetementBDD.close();
			listViewVetement = (ListView)rootView. findViewById(R.id.listVetement);

			listViewVetement
					.setOnItemLongClickListener(new OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> arg0,
								View arg1, final int arg2, long arg3) {

							AlertDialog.Builder alertDialog = new AlertDialog.Builder(
									getActivity());

							alertDialog.setTitle("Confirm Delete...");
							alertDialog
									.setMessage("Are you sure you want delete this?");
							alertDialog.setPositiveButton("YES",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int which) {
											// TODO Remove one Artcile
											vetementBDD.open();
											vetementBDD.removeVetement(arg2);
											vetementBDD.close();
											vetementList.remove(arg2);
											listViewVetement
													.setAdapter(new VetementAdapter(
															getActivity().getBaseContext(),
															R.layout.item_vetement,
															vetementList));
										}
									});
							alertDialog.setNegativeButton("NO",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int which) {
											dialog.cancel();
										}
									});
							alertDialog.show();

							return false;
						}
					});
			listViewVetement.setAdapter(new VetementAdapter(getActivity().getBaseContext(),
					R.layout.item_vetement, vetementList));
		 
		 return rootView;
	}
	

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			
		inflater.inflate(R.menu.list, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(getActivity());
			return true;
		case R.id.action_remove_all:
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					getActivity());

			alertDialog.setTitle("Confirm Delete...");
			alertDialog
					.setMessage("Are you sure you want delete all the list?");
			alertDialog.setPositiveButton("YES",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							// TODO Remove All Article
							vetementBDD.open();
							vetementBDD.removeAllVetements();
							vetementBDD.close();
							vetementList.clear();
							listViewVetement.setAdapter(new VetementAdapter(
									getActivity(), R.layout.item_vetement,
									vetementList));

						}
					});
			alertDialog.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			alertDialog.show();
			return true;

		}
		return super.onOptionsItemSelected(item);
	}


}

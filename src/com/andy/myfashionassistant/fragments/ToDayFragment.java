package com.andy.myfashionassistant.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.andy.myfashionassistant.adapter.TodayAdapter;
import com.andy.myfashionassistant.entities.Vetement;
import com.andy.myfashionassistant.sqlLite.VetementBDD;
import com.fashion.R;

public class ToDayFragment extends Fragment {

	View rootView;
	GridView grid;
	VetementBDD vetementBDD;
	List<Vetement> vetementToday, vetementTodayFiltre;
	Intent it;

	public static Fragment newInstance() {
		ToDayFragment fragment = new ToDayFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.two_fragment, container, false);

		grid = (GridView) rootView.findViewById(R.id.gridViewLook);

		getToday();

		return rootView;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getToday();
	}

	public void getToday(){
		vetementBDD = new VetementBDD(getActivity());
		vetementBDD.open();

		vetementToday = vetementBDD.selectAll();
		vetementBDD.close();
		vetementTodayFiltre = new ArrayList<Vetement>();

		SharedPreferences prefs = getActivity().getSharedPreferences("today",
				getActivity().MODE_PRIVATE);

		for (int i = 0; i < vetementToday.size(); i++) {
			if (prefs.getString("coat", "").equals(vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("upper", "").equals(vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("lower", "").equals(vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("shoes", "").equals(vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("accessories", "").equals(vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("dress", "").equals(vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}

		}

		grid.setAdapter(new TodayAdapter(getActivity(),
				R.layout.assistance_item, vetementTodayFiltre));
	}
}

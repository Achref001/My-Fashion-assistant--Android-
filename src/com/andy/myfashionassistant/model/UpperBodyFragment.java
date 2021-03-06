package com.andy.myfashionassistant.model;

import java.util.ArrayList;
import java.util.List;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.andy.myfashionassistant.adapter.AssistanceAdapter;
import com.andy.myfashionassistant.entities.Vetement;
import com.andy.myfashionassistant.sqlLite.VetementBDD;
import com.fashion.R;

public class UpperBodyFragment extends WizardStep {


	@ContextVariable
	private String todo;
	@ContextVariable
	private String upper;
	@ContextVariable
	private String lower;
	@ContextVariable
	private String shoes;
	@ContextVariable
	private String access;
	@ContextVariable
	private String coat;
	@ContextVariable
	private String dress;
	
	public UpperBodyFragment() {
	}

	VetementBDD vetementBDD;
	List<Vetement> vetementList,vetementListfiltre;
	View rootView;
	GridView grid;
	RadioGroup groupeUpper;
	RadioButton rbUpper;
	String text;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		rootView =inflater.inflate(R.layout.fragment_upper, container,false);

		grid = (GridView) rootView.findViewById(R.id.gridViewUpper);
		//groupeUpper = (RadioGroup) rootView.findViewById(R.id.itemGrRd);

		vetementBDD = new VetementBDD(getActivity());
		vetementBDD.open();

		vetementList = vetementBDD.selectAll();
		vetementBDD.close();
		vetementListfiltre = new ArrayList<Vetement>();
		//Log.e("yass",vetementList.get(0).getType());
		Log.e("yass2",""+vetementList.size());
		for (int i = 0; i < vetementList.size(); i++) {

			if(vetementList.get(i).getType().equals("Upper body"))
			{

				vetementListfiltre.add(vetementList.get(i));
			}
		}


		grid.setAdapter(new AssistanceAdapter(getActivity(),
				R.layout.assistance_item, vetementListfiltre));


		//grid.setAdapter(new GridAdapter(getActivity()));
		
		grid.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
		grid.setSelector(getResources().getDrawable(R.drawable.grid_selection));
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Toast.makeText(getActivity(), "position = "+position, Toast.LENGTH_LONG).show();
//				grid.setItemChecked(position, true);
				grid.requestFocusFromTouch();
				grid.setSelection(position);
				for (int i = 0; i < vetementListfiltre.size(); i++) {
					if (position==i) {
						text = vetementListfiltre.get(i).getTitle().toString();
						//Toast.makeText(getActivity(), "text = "+vetementListfiltre.get(i).getTitle().toString(), Toast.LENGTH_LONG).show();
					}
				}
				//mPage.getData().putString(UpperBodyPager.Upper_KEY, String.valueOf(position));
			}
		});


		return rootView;
	}


	@Override
	public void onExit(int exitCode) {
		switch (exitCode) {
		case WizardStep.EXIT_NEXT:
			bindDataFields();
			break;
		case WizardStep.EXIT_PREVIOUS:
			//Do nothing...
			break;
		}
	}

	private void bindDataFields() {
		//Do some work
		//...
		//The values of these fields will be automatically stored in the wizard context
		//and will be populated in the next steps only if the same field names are used.
		/* todo = rbJob.getText().toString();
        upper = 
        lower = rbMeeting.getText().toString();
        shoes = rbMeeting.getText().toString();
        access = rbMeeting.getText().toString();
        dress = rbMeeting.getText().toString();
        coat = rbMeeting.getText().toString();*/
		//    	int selected = groupeUpper.getCheckedRadioButtonId();
		//    	rbUpper = (RadioButton) rootView.findViewById(selected);
		//    	todo = rbUpper.getText().toString();
		//    	grid.setOnItemClickListener(new OnItemClickListener() {
		//
		//			@Override
		//			public void onItemClick(AdapterView<?> parent, View view,
		//int position, long id) {
//			int selected = grid.getSelectedItemPosition();
//			Log.e("yass position",""+selected);
			//upper = String.valueOf(selected);
//			TextView tt=(TextView) rootView.findViewById(R.id.titre);
//			tt.setText(text);
			upper=text;
			//mPage.getData().putString(UpperBodyPager.Upper_KEY, String.valueOf(position));
			//			}
			//		});
		}
	}

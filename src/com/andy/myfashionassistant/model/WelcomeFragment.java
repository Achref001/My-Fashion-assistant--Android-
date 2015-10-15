package com.andy.myfashionassistant.model;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fashion.R;

public class WelcomeFragment extends WizardStep {
	
	@ContextVariable
    private String welcomeCoat;
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
    
	
		View rootView;
		RadioButton rbJob;
		RadioButton rbMeeting;
		RadioButton rbParty;
		RadioButton rbCasual;
		RadioButton rbSport;
		RadioButton rbEvent;
		RadioGroup groupeWelcome;
		

		public WelcomeFragment(){
	    }
		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			
			rootView =inflater.inflate(R.layout.fragment_welcome, container,false);
			
			groupeWelcome = (RadioGroup) rootView.findViewById(R.id.GroupWelcome);
			
			/*rbJob=(RadioButton)rootView.findViewById(R.id.radioJob);
			rbMeeting=(RadioButton)rootView.findViewById(R.id.radioMeeting);
			rbParty=(RadioButton)rootView.findViewById(R.id.radioParty);
			rbCasual=(RadioButton)rootView.findViewById(R.id.radioCasual);
			rbSport=(RadioButton)rootView.findViewById(R.id.radioSport);
			rbEvent=(RadioButton)rootView.findViewById(R.id.radioEvent);*/
			
			
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
	    	int selected = groupeWelcome.getCheckedRadioButtonId();
	    	rbJob = (RadioButton) rootView.findViewById(selected);
	    	todo = rbJob.getText().toString(); 
	    	//todo="klewi";
	    	
	    	/*
	        upper = rbMeeting.getText().toString();
	        lower = rbMeeting.getText().toString();
	        shoes = rbMeeting.getText().toString();
	        access = rbMeeting.getText().toString();
	        dress = rbMeeting.getText().toString();
	        coat = rbMeeting.getText().toString();
	        */
	    }


}

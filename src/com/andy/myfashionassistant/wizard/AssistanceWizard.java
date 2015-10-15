package com.andy.myfashionassistant.wizard;

import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.layouts.BasicWizardLayout;

import com.andy.myfashionassistant.model.AccessoiriesFragment;
import com.andy.myfashionassistant.model.CoatFragment;
import com.andy.myfashionassistant.model.DressFragment;
import com.andy.myfashionassistant.model.FinishFragment;
import com.andy.myfashionassistant.model.LowerBodyFragment;
import com.andy.myfashionassistant.model.ShoesFragment;
import com.andy.myfashionassistant.model.UpperBodyFragment;
import com.andy.myfashionassistant.model.WelcomeFragment;



public class AssistanceWizard extends BasicWizardLayout {
	  
    public AssistanceWizard() {
        super();
    }

    
    @Override
    public WizardFlow onSetup() {
        return new WizardFlow.Builder()
        		.addStep(WelcomeFragment.class)
        		//.addStep(CoatWelcomeFragment.class) 
        		.addStep(CoatFragment.class)  
                .addStep(UpperBodyFragment.class)           
                .addStep(LowerBodyFragment.class)
                .addStep(ShoesFragment.class)
                .addStep(AccessoiriesFragment.class)
                .addStep(DressFragment.class)
                .addStep(FinishFragment.class)
                .create();                              
    }

    
    @Override
    public void onWizardComplete() {
        super.onWizardComplete();   
        getActivity().finish();     
    }

}

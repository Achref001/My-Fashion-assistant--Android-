package com.andy.myfashionassistant.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.andy.myfashionassistant.adapter.calendrierAdapter;
import com.andy.myfashionassistant.entities.Assistance;
import com.andy.myfashionassistant.entities.Calendrier;
import com.andy.myfashionassistant.sqlLite.CalandarBDD;
import com.fashion.R;
import com.wt.calendarcard.CalendarCard;
import com.wt.calendarcard.CardGridItem;
import com.wt.calendarcard.OnCellItemClick;


public class CalendarFragment extends Fragment {
	private CalendarCard mCalendarCard;
	
	View rootView;
	GridView grid;
	CalandarBDD calandarBDD ;
	List<Calendrier>calendrierList;
	List<Assistance>whatsMyLook ,whatsMyLookfilter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.calendar_fragment, container, false);
		calendrierList = new ArrayList<Calendrier>();
		calandarBDD = new CalandarBDD(getActivity());
		calandarBDD.open();
		
		whatsMyLook = calandarBDD.selectAll();
		calandarBDD.close();
		whatsMyLookfilter = new ArrayList<Assistance>();
		
		grid=(GridView) rootView.findViewById(R.id.grid_viewCalander);
		mCalendarCard = (CalendarCard)rootView. findViewById(R.id.calendarCard1);
		
		mCalendarCard.setOnCellItemClick(new OnCellItemClick() {
			@Override
			public void onCellClick(View v, CardGridItem item) {
				
				String clickedDate = getResources().getString(R.string.sel_date, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(item.getDate().getTime())).toString();
				

				for (int i = 0; i < whatsMyLook.size(); i++) {
					
					if(clickedDate.equals(whatsMyLook.get(i).getDataAssistance()))
					{
						
						
						whatsMyLookfilter.add(whatsMyLook.get(i));
						
						//grid.setVisibility(0);
						
						//Calendrier cal = new Calendrier();
						
						/*
						
						cal.setPhoto(whatsMyLookfilter.get(i).getLogoAccessories());
						Log.e("ok", "oklogo");
						cal.setTexte("your Accessories");
						calendrierList.add(cal);
						cal.setPhoto(whatsMyLookfilter.get(i).getLogoCoat());
						cal.setTexte("your coat picture");
						calendrierList.add(cal);
						cal.setPhoto(whatsMyLookfilter.get(i).getLogoDress());
						cal.setTexte("your dress picture");
						calendrierList.add(cal);
						
						cal.setPhoto(whatsMyLookfilter.get(i).getLogoLowerBody());
						cal.setTexte("your lower body picture");
						calendrierList.add(cal);
						cal.setPhoto(whatsMyLookfilter.get(i).getLogoShoes());
						cal.setTexte("your shoes picture");
						calendrierList.add(cal);
						cal.setPhoto(whatsMyLookfilter.get(i).getLogoUpperBody());
						cal.setTexte("your upper body picture !!");
						calendrierList.add(cal);
						Log.e("!!!!size of the list", "!!!calend.size"+calendrierList.size());
						grid.setAdapter(new calendrierAdapter(getActivity(), R.layout.calendar_item, calendrierList));*/
						
					
					}
				}
			int	size = whatsMyLookfilter.size();
				if(size!=0)
				{
					//Toast.makeText(getActivity(), "here we will set the new view "+ whatsMyLookfilter.size(), 2000).show();
					if(whatsMyLookfilter.get(size-1).getLogoAccessories()!=null)
					{
					Calendrier cal = new Calendrier();
					cal.setPhoto(whatsMyLookfilter.get(size-1).getLogoAccessories());
					cal.setTexte("your Accessories");
					calendrierList.add(cal);
					}
					if(whatsMyLookfilter.get(size-1).getLogoCoat()!=null)
					{
					Calendrier cal= new Calendrier();
					cal.setPhoto(whatsMyLookfilter.get(size-1).getLogoCoat());
					cal.setTexte("your coat picture");
					calendrierList.add(cal);
					}
					if(whatsMyLookfilter.get(size-1).getLogoDress()!=null)
					{
					Calendrier cal= new Calendrier();
					cal.setPhoto(whatsMyLookfilter.get(size-1).getLogoDress());
					cal.setTexte("your dress picture");
					calendrierList.add(cal);
					}
					if(whatsMyLookfilter.get(size-1).getLogoLowerBody()!=null)
					{
				    Calendrier cal= new Calendrier();
					cal.setPhoto(whatsMyLookfilter.get(size-1).getLogoLowerBody());
					cal.setTexte("your lower body picture");
					calendrierList.add(cal);
					}
					if(whatsMyLookfilter.get(size-1).getLogoShoes()!=null)
					{
				    Calendrier cal= new Calendrier();
					cal.setPhoto(whatsMyLookfilter.get(size-1).getLogoShoes());
					cal.setTexte("your shoes picture");
					calendrierList.add(cal);
					}
					if(whatsMyLookfilter.get(size-1).getLogoUpperBody()!=null)
					{
					Calendrier cal= new Calendrier();
					cal.setPhoto(whatsMyLookfilter.get(size-1).getLogoUpperBody());
					cal.setTexte("your upper body picture !!");
					calendrierList.add(cal);
					}
					if(calendrierList.size()!=0)
					{
					grid.setVisibility(0);
					grid.setAdapter(new calendrierAdapter(getActivity(), R.layout.calendar_item, calendrierList));
					}
					
				}
				
				
			}
		});
		
		
		
		return rootView;
	}
	


	
		
	


}


package com.andy.myfashionassistant.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.andy.myfashionassistant.adapter.ShoppingAdapter;
import com.andy.myfashionassistant.entities.shopping;
import com.fashion.R;

public class ShoppingFragment extends Fragment{
	private List<shopping> myShops = new ArrayList<shopping>();
	TextView typeShopping ;
	View rootView;
	ListView list;
	FragmentManager fragmentManager ;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.shopping_list, container, false);
		fragmentManager = getFragmentManager();
		addShopping();
		
		registerCallBack();
		
		return rootView;
	}

	

	private void addShopping() {
		myShops.add(new shopping(R.drawable.otherwear, "Outerwear"));
		myShops.add(new shopping(R.drawable.blazers, "Blazers"));
		myShops.add(new shopping(R.drawable.dresses, "Dresses"));
		myShops.add(new shopping(R.drawable.jumpsuits, "Jumpsuits"));
		myShops.add(new shopping(R.drawable.tops, "Tops"));
		myShops.add(new shopping(R.drawable.trousers, "Trousers"));
		myShops.add(new shopping(R.drawable.jeans, "Jeans"));
		myShops.add(new shopping(R.drawable.skirts, "Skirts"));
		myShops.add(new shopping(R.drawable.t_shirt, "t-shirts"));
		
		
	}
	private void registerCallBack() {
		list= (ListView) rootView.findViewById(R.id.listView1);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {
				Bundle args = new Bundle();
				WebFragment wf = new WebFragment();
				
				switch (position) {
				case 0:
					
			        args.putString("link", "http://global.rakuten.com/en/category/100371/?f=0&fs=0&vm=2&sm=0&sclid=a_sem_cp1000301948&lsid=soMR38bJU_dc|pcrid|58781878143|pkw|%2Bonline%20%2Bclothing|pmt|b");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;	


				case 1:
					
			        args.putString("link", "http://www.jabong.com/men/clothing/suits-blazers/jackets-blazers/");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;
				case 2:
					
			        args.putString("link", "http://www.myntra.com/women-dresses");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;
				case 3:
					
			        args.putString("link", "http://www.jabong.com/women/clothing/dresses-jumpsuits/dungarees-jumpsuits/");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;
				case 4:
					
			        args.putString("link", "http://www.flipkart.com/womens-clothing/polos-t-shirts/pr?sid=2oq%2Cc1r%2Cfpt");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;
				case 5:
					
			        args.putString("link", "http://www.jcpenney.com/men/levis/cat.jump?id=cat100420006");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;
				case 6:
					
			        args.putString("link", "http://www.jabong.com/women/clothing/capris-shorts-skirts/skirts/");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;
				case 7:
					
			        args.putString("link", "http://www.jabong.com/men/clothing/polos-tshirts/");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;
				case 8:
					
			        args.putString("link", "http://www.jabong.com/men/clothing/polos-tshirts/");
			        wf.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frameShopping, wf).commit();
					break;
				
				
				
				
				
			}
			
			}
				

			
		});
		list.setAdapter(new ShoppingAdapter(getActivity().getBaseContext(), R.layout.item_shopping, myShops));
	}
		
		
		

	

}

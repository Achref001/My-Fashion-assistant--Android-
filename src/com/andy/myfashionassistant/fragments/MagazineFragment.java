package com.andy.myfashionassistant.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.andy.myfashionassistant.NavigationMain;
import com.andy.myfashionassistant.adapter.LazyAdapter;
import com.andy.myfashionassistant.utils.XMLParser;
import com.fashion.R;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;



public class MagazineFragment extends Fragment{
	// All static variables
	static final String URL = "http://www.hintmag.com/rss.xml";
	// XML node keys
	static final String item = "item"; // parent node
	static final String link = "link";
	public static final String title = "title";
	public static final String pubDate = "pubDate";
	//static final String category = "category";
	static final String description = "description";
	public static final String imgpath = "";
	FragmentManager fragmentManager ;
	private JazzyListView mList;
	private int mCurrentTransitionEffect = JazzyHelper.HELIX;
	LazyAdapter adapter;
public String imgValue(String description) {
	String img = null;


	if (description.contains("<img ")){
		String im  = description.substring(description.indexOf("<img "));
		String cleanUp = im.substring(0, im.indexOf(">")+1);
		im = im.substring(im.indexOf("src=") + 4);
		int indexOf = im.indexOf("'");
		if (indexOf==-1){
			indexOf = im.indexOf("\"");
		}
		img = im.substring(0, indexOf);
	}
	Log.e("IMAGE"+img, "");
	return img;
	}
	public String encode(String texte) {
		String t = null;
		String t1 = null;

		if (texte.indexOf("Ã©") > -1) {
			texte = texte.replaceAll("Ã©", "é");
		}
		if (texte.indexOf("Ã´") > -1) {
			texte = texte.replaceAll("Ã´", "ô");
		}
		
		if (texte.indexOf("Ã¢") > -1) {
			texte = texte.replaceAll("Ã¢", "â");
		}

		if (texte.indexOf("Ã¨") > -1) {
			texte = texte.replaceAll("Ã¨", "è");
		}
		if (texte.indexOf("Ã§") > -1) {
			texte = texte.replace("Ã§", "ç");
		}
		
		if (texte.indexOf("àª") > -1) {
			texte = texte.replace("àª", "ê");
		}

		if (texte.indexOf("Ã") > -1) {
			texte = texte.replaceAll("Ã", "à");
		}
		

		if (texte.indexOf("â¦") > -1) {
			texte = texte.replaceAll("â¦", "...");
		}

		if (texte.indexOf("â") > -1) {
			texte = texte.replace("â", "'");
		}
		
		if (texte.indexOf("Â»") > -1) {
			texte = texte.replace("Â»", "»");
		}
		if (texte.indexOf("à®") > -1) {
			texte = texte.replaceAll("à®", "î");
		}
		
		
		if (texte.indexOf("Â«") > -1) {
			texte = texte.replace("Â«", "«");
		}
		
		if (texte.indexOf("Â") > -1) {
			texte = texte.replace("Â ", "œ");
		}
		if (texte.indexOf("à¯") > -1) {
			texte = texte.replace("à¯", "ï");
			
		}
		if (texte.indexOf("Å") > -1) {
			texte = texte.replace("Å", "œ");
			
		}
		if (texte.indexOf("à³") > -1) {
			texte = texte.replace("à³", "ó");
			
		}
		if (texte.indexOf("àª") > -1) {
			texte = texte.replace("àª", "ê");
			
		}
		if (texte.indexOf("Mà»sîq't") > -1) {
			texte = texte.replace(" Mà»sîq't", "Mûsîqât");
			
		}
		if (texte.indexOf("éÌ") > -1) {
			texte = texte.replace("éÌ", "é");
			
		}
		if (texte.indexOf("é'") > -1) {
			texte = texte.replace("é'", "éâ");
			
		}
		
		
		
		Log.d("" + texte, "ok");
		return texte;

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.rss_main, container, false);
		

		final ArrayList<HashMap<String, String>> eventslist = new ArrayList<HashMap<String, String>>();
		mList = (JazzyListView) rootView.findViewById(android.R.id.list);
		Document doc = null;
		XMLParser parser= null;
		String xml;
		try
		{
		 parser = new XMLParser();
		 xml = parser.getXmlFromUrl(URL); // getting XML from URL
		 doc = parser.getDomElement(xml); // getting DOM element
		}
		catch(Exception e)
		{
			Toast.makeText(getActivity(), "Please try to connect to internet", 2000).show();
			Intent ex = new Intent(getActivity(),NavigationMain.class);
			startActivity(ex);
		}
		NodeList nl = doc.getElementsByTagName(item);

		// looping through all song nodes <song>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);

			// adding each child node to HashMap key => value

			map.put(title, encode(parser.getValue(e, title)));
			map.put(description, encode(parser.getValue(e, description)));
			Log.d("desc", encode(parser.getValue(e, description)));
			map.put(link, parser.getValue(e, link));
			//map.put(category, parser.getValue(e, category));
			map.put(pubDate, parser.getValue(e, pubDate));
			map.put(imgpath,imgValue( parser.getValue(e, description)));

			// adding HashList to ArrayList
			eventslist.add(map);
		}

		fragmentManager = getFragmentManager();


		// Getting adapter by passing xml data ArrayList
		adapter = new LazyAdapter(getActivity(), eventslist);
		mList.setAdapter(adapter);

		// Click event for single list row
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle args = new Bundle();
				WebFragment wf = new WebFragment();
				HashMap<String, String> map = eventslist.get(position);
				// Open the URL in a browser
//				Intent intent = new Intent(Intent.ACTION_VIEW);
//				intent.setData(Uri.parse(map.get(link)));
//				startActivity(intent);
				args.putString("link", map.get(link));
		        wf.setArguments(args);
				fragmentManager.beginTransaction().replace(R.id.content_frameMagazine, wf).commit();
				
			}

		});
		return rootView ;

	}
}

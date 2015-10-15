package com.andy.myfashionassistant.model;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.myfashionassistant.adapter.GassAdapter;
import com.andy.myfashionassistant.adapter.TodayAdapter;
import com.andy.myfashionassistant.entities.Assistance;
import com.andy.myfashionassistant.entities.Vetement;
import com.andy.myfashionassistant.sqlLite.CalandarBDD;
import com.andy.myfashionassistant.sqlLite.VetementBDD;
import com.andy.myfashionassistant.utils.Point3D;
import com.andy.myfashionassistant.wizard.WizActivity;
import com.fashion.R;
import com.gc.materialdesign.widgets.Dialog;
import com.gc.materialdesign.widgets.SnackBar;

public class FinishFragment extends WizardStep {
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
	
	
	
	VetementBDD vetementBDD;
	List<Vetement> vetementToday, vetementTodayFiltre;
	List<Vetement> vetementList;
	List<String> similarColor = new ArrayList<String>();

	Vector<String> shoosenColor = new Vector<String>();
	Vector<String> sensiveColor = new Vector<String>();
	// addtodayslook to database
	CalandarBDD calendarBDD;
	//Assistance assistances = new Assistance();
	private byte[] logoCoat = null;
	private byte[] logoUpperBody = null;
	private byte[] logoLowerBody = null;
	private byte[] logoShoes = null;
	private byte[] logoAccessories = null;
	private byte[] logoDress = null;
	String lyoum = null;
	// end
	int score = 0;

	Intent it;
	View rootView;
	TableLayout tableLayout;
	ListView mlist ;
	public FinishFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		rootView = inflater.inflate(R.layout.list_finish, container, false);
		//grid = (GridView) rootView.findViewById(R.id.gridFinish);
		ListView mlist = (ListView) rootView.findViewById(R.id.mlistViewfinish);

		SharedPreferences prefs = getActivity().getSharedPreferences("today", getActivity().MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("coat", coat);
		editor.putString("upper", upper);
		editor.putString("lower", lower);
		editor.putString("shoes", shoes);
		editor.putString("accessories", access);
		editor.putString("dress", dress);
		editor.commit();
		
		getToday();
		addTodaysLook();
		resultatAssistance();
		
		mlist.setAdapter(new GassAdapter(getActivity(),R.layout.item_finish, vetementTodayFiltre));
		final int ss =calculScore();
		TextView assistantResult = (TextView) rootView.findViewById(R.id.textViewAssistantResult);
		if(ss<5)
		{
			assistantResult.setText(getString(R.string.notwelldressed));
			
		}
		else if(ss>6)
		{
			assistantResult.setText(getString(R.string.congratulation));
			
		}
		
		

/*			@Override
			public void onClick(final View flatButton) {
		
				new SnackBar(getActivity(),
						"Your score is :"+ss).show();
			
			}
		});
*/

		return rootView;
	}
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	
	super.onActivityResult(requestCode, resultCode, data);
}
	
	public void addTodaysLook() {
		Date d = new Date();
	    String s  = DateFormat.format("yyyy-MM-dd", d.getTime()).toString();
	    Log.e(s, s);
		lyoum = s;
		// connection with vetDBB database
		vetementBDD = new VetementBDD(getActivity());
		vetementBDD.open();
		vetementList = vetementBDD.selectAll();
		vetementBDD.close();
		// end connection
		//assistances.setDataAssistance(lyoum);
		SharedPreferences prefs = getActivity().getSharedPreferences("today",
				getActivity().MODE_PRIVATE);
		for (int i = 0; i < vetementList.size(); i++) {

			if (prefs.getString("coat", "").equals(
					vetementList.get(i).getTitle())) {
				logoCoat = vetementList.get(i).getLogo();
				
				//assistances.setLogoCoat(logoCoat);
				// vetementAssistance.add(vetementList.get(i).getLogo());

			}
			if (prefs.getString("upper", "").equals(
					vetementList.get(i).getTitle())) {
				logoUpperBody = vetementList.get(i).getLogo();
				//assistances.setLogoUpperBody(logoUpperBody);
			}
			if (prefs.getString("lower", "").equals(
					vetementList.get(i).getTitle())) {
				logoLowerBody = vetementList.get(i).getLogo();
				//assistances.setLogoLowerBody(logoLowerBody);
				// vetementListfiltre.add(vetementList.get(i));
			}
			if (prefs.getString("shoes", "").equals(
					vetementList.get(i).getTitle())) {
				logoShoes = vetementList.get(i).getLogo();
				//assistances.setLogoShoes(logoShoes);
				// vetementListfiltre.add(vetementList.get(i));
			}
			if (prefs.getString("accessories", "").equals(
					vetementList.get(i).getTitle())) {
				logoAccessories = vetementList.get(i).getLogo();
				//assistances.setLogoAccessories(logoAccessories);
				// vetementListfiltre.add(vetementList.get(i));
			}
			if (prefs.getString("coat", "").equals(
					vetementList.get(i).getTitle())) {
				logoCoat = vetementList.get(i).getLogo();
				//assistances.setLogoCoat(logoCoat);
				// vetementListfiltre.add(vetementList.get(i));
			}
			if (prefs.getString("dress", "").equals(
					vetementList.get(i).getTitle())) {
				logoDress = vetementList.get(i).getLogo();
				//assistances.setLogoDress(logoDress);
				// vetementListfiltre.add(vetementList.get(i));
			}
		}
		Assistance assistances = new Assistance(logoCoat, logoUpperBody, logoLowerBody, logoShoes, logoAccessories, logoDress, lyoum);
		calendarBDD = new CalandarBDD(getActivity());
		calendarBDD.open();
		calendarBDD.insertTop(assistances);
		
		Log.e("clothes saved into calendar", "clothes saved into calendar");

	}
	public void getToday() {
		vetementBDD = new VetementBDD(getActivity());
		vetementBDD.open();

		vetementToday = vetementBDD.selectAll();
		vetementBDD.close();
		vetementTodayFiltre = new ArrayList<Vetement>();

		SharedPreferences prefs = getActivity().getSharedPreferences("today",
				getActivity().MODE_PRIVATE);

		for (int i = 0; i < vetementToday.size(); i++) {
			if (prefs.getString("coat", "").equals(
					vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("upper", "").equals(
					vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("lower", "").equals(
					vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("shoes", "").equals(
					vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("accessories", "").equals(
					vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}
			if (prefs.getString("dress", "").equals(
					vetementToday.get(i).getTitle())) {
				vetementTodayFiltre.add(vetementToday.get(i));
			}

		}

		//grid.setAdapter(new TodayAdapter(getActivity(),R.layout.assistance_item, vetementTodayFiltre));
	}
	public void resultatAssistance() {
		for (int i = 0; i < vetementTodayFiltre.size(); i++) {
			byte[] outImage = vetementTodayFiltre.get(i).getLogo();
			ByteArrayInputStream imageStream = new ByteArrayInputStream(
					outImage);
			Bitmap theImage = BitmapFactory.decodeStream(imageStream);
			long rouge = 0L;
			long vert = 0L;
			long bleu = 0L;
			int[] pixeles = new int[theImage.getWidth() * theImage.getHeight()];
			theImage.getPixels(pixeles, 0, theImage.getWidth(), 0, 0,
					theImage.getWidth(), theImage.getHeight());
			for (int cursor = 0; cursor < pixeles.length; cursor++) {
				rouge += Color.red(pixeles[cursor]);
				vert += Color.green(pixeles[cursor]);
				bleu += Color.blue(pixeles[cursor]);
			}
			long numPixels = theImage.getWidth() * theImage.getHeight();
			rouge /= numPixels;
			vert /= numPixels;
			bleu /= numPixels;
			/*
			 * Log.e("Rouge ="+rouge, "rouge"+rouge); Log.e("Vert ="+vert,
			 * "vert"+vert); Log.e("Bleu  ="+bleu, "Bleu Bleu"+bleu);
			 */

			// now we will find the similar color :

			Point3D[] pixelesRef = { new Point3D(255, 0, 0),
					new Point3D(0, 255, 0), new Point3D(0, 0, 255),
					new Point3D(255, 255, 0), new Point3D(0, 255, 255),
					new Point3D(255, 0, 255), new Point3D(0, 0, 0),
					new Point3D(255, 255, 255) };
			Point3D pixelActuel = new Point3D(rouge, vert, bleu);

			double[] distance = { pixelActuel.distance(pixelesRef[0]),
					pixelActuel.distance(pixelesRef[1]),
					pixelActuel.distance(pixelesRef[2]),
					pixelActuel.distance(pixelesRef[3]),
					pixelActuel.distance(pixelesRef[4]),
					pixelActuel.distance(pixelesRef[5]),
					pixelActuel.distance(pixelesRef[6]),
					pixelActuel.distance(pixelesRef[7]) };
			String[] couleurs = { "rouge", "vert", "bleu", "jaune", "turcoise",
					"magenta", "noir", "blanc" };

			double dist_minimum = 255;
			int indice_minimum = 0;
			for (int index = 0; index < distance.length; index++) {
				if (distance[index] <= dist_minimum) {
					indice_minimum = index;
					dist_minimum = distance[index];
				}
				Log.i("Distance", "Distance vers " + index + ": "
						+ distance[index]);
			}

			Log.e("simlar color is" + (couleurs[indice_minimum]),
					"this is the similar color" + couleurs[indice_minimum]);
			// similarColor.add(couleurs[indice_minimum]);
			// Toast.makeText(getActivity(),
			// "the similar color is "+similarColor.get(0), 2000).show();
			shoosenColor.add(couleurs[indice_minimum]);
			
			
		}
		for(int j =0;j< vetementTodayFiltre.size();j++)
		{
			vetementTodayFiltre.get(j).setType(shoosenColor.get(j));
			Log.e("this is the gassssssssssss",vetementTodayFiltre.get(j).getType());
		}
		

	}

	public int calculScore() {
		score = 0;
		for (int i = 0; i < shoosenColor.size(); i++) {
			for (int j = i + 1; j < shoosenColor.size(); j++) {
				if (shoosenColor.get(i).equals("rouge")
						&& shoosenColor.get(j).equals("bleu")) {

					score++;
					Log.e("1 combunaison rouge bleu", "combunaisoon rouge bleu"
							+ score);
				} else if (shoosenColor.get(i).equals("bleu")
						&& shoosenColor.get(j).equals("rouge")) {

					score++;
					Log.e("1 ' combunaison rouge bleu",
							"combunaisoon rouge bleu" + score);
				} else if (shoosenColor.get(i).equals("vert")
						&& shoosenColor.get(j).equals("bleu")) {

					score++;
					Log.e("2 combunaison vert bleu", "combunaisoon vert bleu"
							+ score);
				} else if (shoosenColor.get(i).equals("bleu")
						&& shoosenColor.get(j).equals("vert")) {

					score++;
					Log.e("2' combunaison vert bleu", "combunaisoon vert bleu"
							+ score);
				} else if (shoosenColor.get(i).equals("turcoise")
						&& shoosenColor.get(j).equals("blanc")
						|| shoosenColor.get(j).equals("noir")) {

					score++;
					Log.e("3 combunaison turcoise blanc ou noir",
							"combunaisoon turcoise blanc ou noir" + score);
				} else if (shoosenColor.get(i).equals("blanc")
						|| shoosenColor.get(i).equals("noir")
						&& shoosenColor.get(j).equals("turcoise")) {

					score++;
					Log.e("3 ' combunaison turcoise blanc ou noir",
							"combunaisoon turcoise blanc ou noir" + score);
				} else if (shoosenColor.get(i).equals("magenta")
						&& shoosenColor.get(j).equals("blanc")) {

					score++;
					Log.e("4 combunaison magenta  blanc",
							"combunaisoon magenta blanc" + score);
				} else if (shoosenColor.get(i).equals("blanc")
						&& shoosenColor.get(j).equals("magenta")) {

					score++;
					Log.e("4' combunaison magenta  blanc",
							"combunaisoon magenta blanc" + score);
				} else if (shoosenColor.get(i).equals("jaune")
						&& shoosenColor.get(j).equals("vert")) {

					score++;
					Log.e("5 combunaison jaune  vert",
							"combunaisoon jaune vert" + score);
				} else if (shoosenColor.get(i).equals("vert")
						&& shoosenColor.get(j).equals("jaune")) {

					score++;
					Log.e("5' combunaison jaune  vert",
							"combunaisoon jaune vert" + score);
				}
				// now we will deal with the score --
				else if (shoosenColor.get(i).equals("rouge")
						&& shoosenColor.get(j).equals("vert")) {

					score--;
					Log.e("6 combunaison rouge  vert",
							"combunaisoon rouge vert" + score);
				} else if (shoosenColor.get(i).equals("vert")
						&& shoosenColor.get(j).equals("rouge")) {

					score--;
					Log.e("6' combunaison rouge  vert",
							"combunaisoon rouge vert" + score);
				} else if (shoosenColor.get(i).equals("jaune")
						&& shoosenColor.get(j).equals("turcoise")) {

					score++;
					Log.e("7 combunaison jaune  turcoise",
							"combunaisoon jaune vert" + score);
				} else if (shoosenColor.get(i).equals("turcoise")
						&& shoosenColor.get(j).equals("jaune")) {

					score--;
					Log.e("7' combunaison jaune  turcoise",
							"combunaisoon jaune turcoise" + score);
				} else if (shoosenColor.get(i).equals("jaune")
						&& shoosenColor.get(j).equals("magenta")) {

					score--;
					Log.e("8 combunaison jaune  magenta",
							"combunaisoon jaune turcoise" + score);
				} else if (shoosenColor.get(i).equals("magenta")
						&& shoosenColor.get(j).equals("jaune")) {

					score--;
					Log.e("8 combunaison jaune  magenta",
							"combunaisoon jaune turcoise" + score);
				} else if (shoosenColor.get(i).equals(shoosenColor.get(j))) {
					Log.e("you have shoose the same color", "same color"
							+ shoosenColor.get(i));
					Toast.makeText(getActivity(), shoosenColor.get(i), 2000)
							.show();
				}

				Log.e("score fin if", "score" + score);
			}
		}
		Log.e("score fin pour", "score end for" + score);
		
		return score ;
	}


}

package com.andy.myfashionassistant.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.myfashionassistant.utils.Point3D;
import com.fashion.R;
import com.gc.materialdesign.views.ButtonRectangle;

@SuppressLint("NewApi")
public class AssitanceFragment extends Fragment {
	static Bitmap bmp = null;
	private static final int RESULT_OK = 0;
	private static int CODE_IMAGE_RECHERCHE = 1;
	private ButtonRectangle rechercher_imageButton;
	private ImageView imageView;
	private TextView rouge_textView;
	private TextView vert_textView;
	private TextView bleu_textView;
	private TextView cadre_color;
	private TextView signecouleursimilaires;
	private TextView nomscouleursimilaires;
	View rootView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		 rootView = inflater.inflate(R.layout.activity_assistance, container, false);
		
        rechercher_imageButton = (ButtonRectangle)rootView. findViewById(R.id.rechercher_button);
        
        imageView = (ImageView)rootView. findViewById(R.id.imageView);
        rouge_textView = (TextView)rootView. findViewById(R.id.rouge_textView);
        vert_textView = (TextView)rootView. findViewById(R.id.vert_textView);
        bleu_textView = (TextView)rootView. findViewById(R.id.bleu_textView);
        cadre_color = (TextView)rootView. findViewById(R.id.signe_textView);
        signecouleursimilaires = (TextView)rootView. findViewById(R.id.signecouleursimilaires_textView);
        nomscouleursimilaires = (TextView)rootView. findViewById(R.id.nomscouleursimilaires_textView);
        
        rechercher_imageButton.setOnClickListener(rechercherImage);
   return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);		
		inflater.inflate(R.menu.menu, menu);
    }
    
    OnClickListener rechercherImage = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_PICK);
			getActivity().
			startActivityForResult(Intent.createChooser(intent, "Selectionner une image"),
					CODE_IMAGE_RECHERCHE);
		}
	};
	
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == CODE_IMAGE_RECHERCHE) {
			if (resultCode == RESULT_OK) {
				imageView.setImageURI(data.getData());
				traitementImage();
			}
		/*if (requestCode == 37 && resultCode == Activity.RESULT_OK) {
			bmp = (Bitmap) data.getExtras().get("data");
			((ImageButton)rootView. findViewById(R.id.image)).setImageBitmap(bmp);
			traitementImage();*/
		}
			
		}
	
	
	private void traitementImage() {
		Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
		long rouge = 0L;
		long vert = 0L;
		long bleu = 0L;
		int[] pixeles = new int[image.getWidth() * image.getHeight()];
		image.getPixels(pixeles, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
		for (int cursor = 0; cursor < pixeles.length; cursor++) {
			rouge += Color.red(pixeles[cursor]);
			vert += Color.green(pixeles[cursor]);
			bleu += Color.blue(pixeles[cursor]);
		}
		long numPixels = image.getWidth() * image.getHeight();
		rouge /= numPixels;
		vert /= numPixels;
		bleu /= numPixels;
		
		rouge_textView.setText("Moyenne du rouge: " + rouge);
		vert_textView.setText("Moyenne du vert: " + vert);
		bleu_textView.setText("Moyenne du bleu: " + bleu);
		cadre_color.setBackgroundColor(Color.rgb((int)rouge, (int)vert, (int)bleu));
		
		Point3D[] pixelesRef = {
				new Point3D(255, 0, 0),
				new Point3D(0, 255, 0),
				new Point3D(0, 0, 255),
				new Point3D(255, 255, 0),
				new Point3D(0, 255, 255),
				new Point3D(255, 0, 255),
				new Point3D(0, 0, 0),
				new Point3D(255, 255, 255)
		};
		
		Point3D pixelActuel = new Point3D(rouge, vert, bleu);
		
		double[] distance = {
				pixelActuel.distance(pixelesRef[0]),
				pixelActuel.distance(pixelesRef[1]),
				pixelActuel.distance(pixelesRef[2]),
				pixelActuel.distance(pixelesRef[3]),
				pixelActuel.distance(pixelesRef[4]),
				pixelActuel.distance(pixelesRef[5]),
				pixelActuel.distance(pixelesRef[6]),
				pixelActuel.distance(pixelesRef[7])
		};
		String[] couleurs = {"rouge", "vert", "bleu", 
							"jaune", "turcoise", "magenta",
							"noir", "blanc"};
		
		double dist_minimum = 255;
		int indice_minimum = 0;
		for (int index = 0; index < distance.length; index++) {
			if (distance[index] <= dist_minimum) {
				indice_minimum = index;
				dist_minimum = distance[index];
			}
			Log.i("Distance", "Distance vers " + index + ": " + distance[index]);
		}
		
		signecouleursimilaires.setBackgroundColor(Color.rgb((int)pixelesRef[indice_minimum].getX(),
				(int)pixelesRef[indice_minimum].getY(),
				(int)pixelesRef[indice_minimum].getZ()));
		nomscouleursimilaires.setText(couleurs[indice_minimum]);
	}

}

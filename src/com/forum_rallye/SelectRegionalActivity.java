package com.forum_rallye;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import www.forum_rallye.data.Rallye;
import www.forum_rallye.data.StaticReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;


public class SelectRegionalActivity extends Activity {

	private ListView maListViewPerso;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_regional);
		// Show the Up button in the action bar.
		setupActionBar(); 
	    	
	    	Intent intent = getIntent();
	    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_select_regional);
	        
	        //Récupération de la listview créée dans le fichier main.xml
	        maListViewPerso = (ListView) findViewById(R.id.listviewregional);
	 
	        //Création de la ArrayList qui nous permettra de remplire la listView
	        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
	 
	        //On déclare la HashMap qui contiendra les informations pour un item
	        HashMap<String, Object> map;
	        
	        HttpPost httppost = new HttpPost(".json");
			
				try {
					// Envoi de la requete
					HttpClient httpclient = new DefaultHttpClient();
					
					// Réponse du serveur
					HttpResponse response = httpclient.execute(httppost);				
					JsonReader reader = new JsonReader(
							new InputStreamReader(response.getEntity()
									.getContent()));
					
					Vector<Rallye>  rallyes= StaticReader.readRallyes(reader);
					//users.get(0).getName()
					rallyes.size();
					
					for (Rallye r : rallyes) { 

			            map = new HashMap<String, Object>();
			            map.put("nom", r.getNom());           
			            map.put("dateDeb", String.valueOf(r.getDateDeb()));
			            map.put("dateFin", String.valueOf(r.getDateDeb()));
			            map.put("id", String.valueOf(r.getId()));
			            listItem.add(map);}
					
				} catch (UnsupportedEncodingException e) {
					
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

	 
	        
	        
	 
	        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
	        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
	               new String[] {"nom", "dateDeb", "dateFin"}, new int[] {R.id.nom, R.id.dateDeb, R.id.dateFin});
	        mSchedule.setViewBinder(new MyViewBinder());
	 
	        //On attribut à notre listView l'adapter que l'on vient de créer
	        maListViewPerso.setAdapter(mSchedule);
	 
	        //Enfin on met un écouteur d'évènement sur notre listView
	        maListViewPerso.setOnItemClickListener(new OnItemClickListener() {
				@Override
	        	@SuppressWarnings("unchecked")
				
				
				//TODO CHANGER LE ALERTDIALOG PAR UN INTENT, TU A L'ID DU USER PAR map.get("id") POUR PASSER SUR
				// L'ACTIVITER DU PROFIL D'UN AMI
							
				
	         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					//on récupère la HashMap contenant les infos de notre item (titre, description, img)
	        		HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
	        		//on créer une boite de dialogue
	        		AlertDialog.Builder adb = new AlertDialog.Builder(SelectRegionalActivity.this);
	        		//on attribut un titre à notre boite de dialogue
	        		adb.setTitle("Sélection Item");
	        		//on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
	        		adb.setMessage("Votre choix : "+map.get("id"));
	        		//on indique que l'on veut le bouton ok à notre boite de dialogue
	        		adb.setPositiveButton("Ok", null);
	        		//on affiche la boite de dialogue
	        		adb.show();
	        	}
	         });
	        

	 
	    }

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_regional, menu);
		return true;
	}
	
	public class MyViewBinder implements ViewBinder {
		@Override
		public boolean setViewValue(View view, Object data,String textRepresentation) {
			if( (view instanceof ImageView) & (data instanceof Bitmap) ) {
				ImageView iv = (ImageView) view;
				Bitmap bm = (Bitmap) data;	
				iv.setImageBitmap(bm);	
				return true;
			}
	 
			return false;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

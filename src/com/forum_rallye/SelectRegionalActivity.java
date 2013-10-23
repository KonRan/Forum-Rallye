package com.forum_rallye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import www.forum_rallye.data.JSONParser;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class SelectRegionalActivity extends Activity {

	
	
	
	private ListView maListViewPerso;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_regional);
		// Show the Up button in the action bar.
		setupActionBar(); 


	        
		 //Récupération de la listview créée dans le fichier main.xml
        maListViewPerso = (ListView) findViewById(R.id.listviewregional);
 
        
        
        
        

        JSONObject maPageJson = null;
		try {
			maPageJson = new RetreiveFeedTask().execute().get();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
        
        
         
        

        JSONArray listeCourses ;
       final ArrayList < HashMap < String, Object> > maListeDesCourses = new ArrayList < HashMap < String, Object > > ();

        try {
            listeCourses = maPageJson.getJSONArray("Courses");

            for(int i = 0; i < listeCourses.length(); i++){
	        	
                JSONObject course = listeCourses.getJSONObject(i);
                HashMap<String, Object> map = new HashMap<String, Object>();
	            map.put("nom", course.get("nom"));
	            map.put("jourDepart", course.get("jourDepart"));
	            map.put("jourArrive", course.get("jourArrive"));
	            map.put("id_course", course.get("id_course"));
	            
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        ListAdapter adapter = new SimpleAdapter(getApplicationContext(), maListeDesCourses , R.layout.activity_select_regional, new String[] { "Nom course : ", "Jour de départ : ", "Jour d'arrivée : "}, new int[] { R.id.nom, R.id.dateDeb, R.id.dateFin});
        maListViewPerso.setAdapter(adapter);
	 
	        //Enfin on met un écouteur d'évènement sur notre listView
	        maListViewPerso.setOnItemClickListener(new OnItemClickListener() {
				@Override
	        	@SuppressWarnings("unchecked")
				
				
				
							
				
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
	
	class RetreiveFeedTask extends AsyncTask<Void, Void, JSONObject> {

        private Exception exception;

		@Override
		protected JSONObject doInBackground(Void... arg0) {
			try {
            	JSONParser jParser = new JSONParser();
            	JSONObject maPageJson = jParser.getJSONFromUrl("http://projetfr.zz.mu/selectRegio.php");
                return maPageJson;               	
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
		}
	}

}

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
import android.content.Intent;
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
import android.widget.TextView;


public class SelectRegionalActivity extends Activity {
	
	// url to make request
	private static String url = "http://projetfr.zz.mu/selectRegio.php";
	 
	// JSON Node names
	private static final String TAG_COURSES = "courses";
	private static final String TAG_NOM = "nom";
	private static final String TAG_DEBUT = "dateDebut";
	private static final String TAG_FIN = "dateFin";
	private static final String TAG_ID = "id_course";
	 
	// contacts JSONArray
	JSONArray courses = null;

	
	 public void onCreate1(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_select_regional);
	        ListView maListViewPerso = (ListView) findViewById(R.id.listviewregional);
	         
	        // Hashmap for ListView
	        ArrayList<HashMap<String, String>> coursesList = new ArrayList<HashMap<String, String>>();
	 
	        // Creating JSON Parser instance
	        JSONParser jParser = new JSONParser();
	 
	        // getting JSON string from URL
	        JSONObject json = jParser.getJSONFromUrl(url);
	 
	        try {
	            // Getting Array of Contacts
	            courses = json.getJSONArray(TAG_COURSES);
	             
	            // looping through All Contacts
	            for(int i = 0; i < courses.length(); i++){
	                JSONObject c = courses.getJSONObject(i);
	                 
	                // Storing each json item in variable
	                String nom = c.getString(TAG_NOM);
	                String dateDebut = c.getString(TAG_DEBUT);
	                String dateFin = c.getString(TAG_FIN);
	                String id_course = c.getString(TAG_ID);
	               
	                 
	                // creating new HashMap
	                HashMap<String, String> map = new HashMap<String, String>();
	                 
	                // adding each child node to HashMap key => value
	                map.put(TAG_NOM, nom);
	                map.put(TAG_DEBUT, dateDebut);
	                map.put(TAG_FIN, dateFin);
	                map.put(TAG_ID, id_course);
	 
	                // adding HashList to ArrayList
	                coursesList.add(map);
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	         
	         
	        /**
	         * Updating parsed JSON data into ListView
	         * */
	        ListAdapter adapter = new SimpleAdapter(this, coursesList,
	                R.layout.affichageitem,
	                new String[] { TAG_NOM, TAG_DEBUT, TAG_FIN }, new int[] {
	                        R.id.nom, R.id.dateDeb, R.id.dateFin});
	 
	        maListViewPerso.setAdapter(adapter);
	 
	        // selecting single ListView item
	        ListView lv = maListViewPerso;
	 
	        // Launching new screen on Selecting Single ListItem
	        lv.setOnItemClickListener(new OnItemClickListener() {
	 
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	                // getting values from selected ListItem
	                String nom = ((TextView) view.findViewById(R.id.nom)).getText().toString();
	                String debut = ((TextView) view.findViewById(R.id.dateDeb)).getText().toString();
	                String fin= ((TextView) view.findViewById(R.id.dateFin)).getText().toString();
	                 
	                // Starting new intent
	                Intent in = new Intent(getApplicationContext(), SelectRegionalActivity.class);
	                in.putExtra(TAG_NOM, nom);
	                in.putExtra(TAG_DEBUT, debut);
	                in.putExtra(TAG_FIN, fin);
	                startActivity(in);
	            }
	        });
	    }
	 
	

}

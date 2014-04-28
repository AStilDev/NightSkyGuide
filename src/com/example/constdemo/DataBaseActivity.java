package com.example.constdemo;

import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Nick Wilson, Alisha Hayman
 * 
 * Class works in conjunction with the appropriate xml file to 
 * generate a view for displaying more detailed information about
 * a specified Constellation it received as an intent.  It grabs data
 * through a query to the database and puts this data in the proper 
 * place for the xml file to display it on the phone's screen.
 */

public class DataBaseActivity extends Activity {

	/**
	 * Method is called when this activity is created, it makes the call to copy
	 * the database in to the proper location in the Android file system. It then
	 * performs a simple query to get the basic information about the selected
	 * constellation.
	 * 
	 * @param
	 * 	savedInstanceState - Bundle, which is like a wrapper that is used to
	 * 						 pass data between activities
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_base);
		
		Intent intent = getIntent();
		String value = intent.getStringExtra("selection"); 
		
		SQLiteDatabase db = copyDatabase();
		 
		final String[] data = {"1", "2", "3", "4", "5", "6", "7"};
		String simpleQuery = "SELECT * FROM constellations WHERE name='" + value + "'";
		Cursor cursor = db.rawQuery(simpleQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();					 	
			data[0] = cursor.getString(0); // _id
			data[1] = cursor.getString(1); // name
			data[2] = cursor.getString(2); // abbreviation
			data[3] = cursor.getString(3); // symbol
			data[4] = cursor.getString(4); // family
			data[5] = cursor.getString(5); // closest star
			data[6] = cursor.getString(6); // wiki_link
			setDataToText(data);
			
			Button wikiButton   = (Button) findViewById(R.id.WebLink);
				  
		    wikiButton.setOnClickListener(new OnClickListener() {
		    	@Override
		    	public void onClick(View v) {    		
		    		Intent intent2 = new Intent(DataBaseActivity.this, WebViewActivity.class);
				    intent2.putExtra("url", data[6]); //Optional parameters
					startActivity(intent2);
				}
		    });
		}		
	}
	
	/**
	 * Method copies begins the action of copying the external database
	 * over to the correct location in the mobile devices file system,
	 * so that it can be used in this application.
	 * @return
	 * 	SQLiteDatabase - a database that can used in the application
	 */
	private SQLiteDatabase copyDatabase() {
		DataBaseHelper myDbHelper;
		myDbHelper = new DataBaseHelper(this);
		  
		 try {
			 myDbHelper.createDataBase();
		 } catch (IOException ioe) {
			 throw new Error("Unable to create database");
		 }
		  
		 try {
			 myDbHelper.openDataBase();
		 }catch(SQLException sqle){
			 throw sqle;
		 }
		 SQLiteDatabase db = myDbHelper.getDB();
		 return db;
	}
	
	/**
	 * Method puts elements of data into the proper textviews, so that
	 * they may be displayed in phone application.
	 * @param data - an array of strings containing the fields that make up a
	 * 				 Constellation object, for example closest star, name, and
	 * 				 family
	 */
	protected void setDataToText(String[] data) {
		TextView text = (TextView) findViewById(R.id.constName);
	    text.setText("Constellation name: " + data[1]);
	    TextView text1 = (TextView) findViewById(R.id.constLink);
	    text1.setText("Wikipedia Link: " + data[6]);
	    TextView text2 = (TextView) findViewById(R.id.constStar);
	    text2.setText("Closest star and distance(ly): " +data[5]);
	    TextView text3 = (TextView) findViewById(R.id.constFamily);
	    text3.setText("Family: " + data[4]);
	    TextView text4 = (TextView) findViewById(R.id.constSymbol);
	    text4.setText("Symbol: " + data[3]);
	    TextView text5 = (TextView) findViewById(R.id.constAbb);
	    text5.setText("Abbreviation: " + data[2]);  
	}
}
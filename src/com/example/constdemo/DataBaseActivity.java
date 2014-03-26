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

public class DataBaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_base);
		
		/********** copy database to proper location *********/
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
		 myDbHelper.close();
		 Intent intent = getIntent();
		 String value = intent.getStringExtra("selection");
		 
		
			    ////////////////////////////////////////////////////////////////////
		 myDbHelper.openDataBase();
		 SQLiteDatabase db = myDbHelper.getDB();
		 		 
		 final String[] data = {"1", "2", "3", "4", "5", "6", "7"};//myDbHelper.getConstInfo(value);
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

package com.example.constdemo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class LatLongLocation extends Activity implements LocationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// The minimum distance to change Updates in meters
	    final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

	    // The minimum time between updates in milliseconds
	    final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
		
		
		LocationManager locationManager;
		Location location;
		double longitude;
		double latitude;
		String[] data;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lat_long_location);
		
		Time now = new Time();
		now.setToNow();
		
		TextView lat = (TextView)findViewById(R.id.lat);
		TextView lon = (TextView)findViewById(R.id.lon);
		TextView time = (TextView)findViewById(R.id.time);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        //Log.d("GPS Enabled", "GPS Enabled");
        if (locationManager != null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                lat.setText("Latitude: " + latitude);
                lon.setText("Longitude: " + longitude);
                time.setText("Time: " + now.toString());
                //Toast.makeText(LatLongLocation.this, "latitude: " + latitude, Toast.LENGTH_LONG).show();
            }
        }
		//longitude = locationmanage.getLongitude();
		//latitude  = locationmanager.getLatitude();
		
		//Intent intent = getIntent();
		//String[] myStrings = intent.getStringArrayExtra("time");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}

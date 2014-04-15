package com.example.constdemo;

import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.widget.TextView;


public class GPSTracker implements LocationListener {

    // The minimum distance to change Updates in meters
    final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute


    LocationManager locationManager;
    android.location.Location location;
    double longitude;
    double latitude;

    public GPSTracker(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager != null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                //Toast.makeText(LatLongLocation.this, "latitude: " + latitude, Toast.LENGTH_LONG).show();
            }
        }
    }
    //longitude = locationmanage.getLongitude();
    //latitude  = locationmanager.getLatitude();

    //Intent intent = getIntent();
    //String[] myStrings = intent.getStringArrayExtra("time");}

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public void onLocationChanged(android.location.Location arg0) {
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

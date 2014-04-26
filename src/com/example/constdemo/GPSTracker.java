package com.example.constdemo;

import android.content.Context;
import android.location.*;
import android.location.Location;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.widget.TextView;


public class GPSTracker implements LocationListener {

    LocationManager locationManager;
    android.location.Location location;
    double longitude;
    double latitude;

    public GPSTracker(Context context) {
        // TODO: Trying to get this to work
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L,
        //        500.0f, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null)
        {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }


        /*if (locationManager != null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                //Toast.makeText(LatLongLocation.this, "latitude: " + latitude, Toast.LENGTH_LONG).show();
            }
        }*/
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
        /*if (location != null)
        {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }*/

    }

    @Override
    public void onProviderDisabled(String arg0) {

    }

    @Override
    public void onProviderEnabled(String arg0) {

    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

    }
}

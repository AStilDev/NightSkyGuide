package com.example.constdemo;

import android.content.Context;
import android.location.*;
import android.location.Location;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;


import java.util.List;
import java.util.Locale;

/**
 * @author Alisha Hayman, Nick Wilson
 * @version 4.24.14
 * 
 * Class helps provide functionallity for gps location, and determine the
 * device's current location in latitude longitude coordinates.
 */

public class GPSTracker implements LocationListener {

    LocationManager locationManager;
    android.location.Location location;
    double longitude;
    double latitude;

    /**
     * Constructor gets the current lat and long from GPS.
     * (Be sure to load GPS for a few minutes before using.)
     * @param context The current context.
     */
    public GPSTracker(Context context) {
        // TODO: Trying to get this to work
        // TODO: TAKES FOREVER
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L,
        //        500.0f, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null)
        {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

    }

    /**
     * Constructor that gets the lat and long from an address.
     * Address must be "street, city, state.
     * @param context Current context.
     * @param address A given address as a String.
     */
    public GPSTracker(Context context, String address)
    {
        Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
        try
        {
            List<Address> addresses = geoCoder.getFromLocationName(address , 1);
            if (addresses.size() > 0)
            {
                latitude = addresses.get(0).getLatitude();
                longitude = addresses.get(0).getLongitude();
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

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

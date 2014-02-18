package edu.wcu.NightSkyGuide_v1_0;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * TODO: Make this splash screen. Move code to other class.
 * Main activity that creates the Google map.
 *
 * @author Alisha Hayman
 * @author Nick Wilson
 * @version 15 February, 2014
 */
public class MyActivity extends Activity {

    /** GoogleMap object */
    private GoogleMap googleMap;

    /** Coordinates of map's northeastern corner */
    private final LatLng NORTHEAST_BOUNDS = new LatLng(90, 180);

    /** Coordinates of map's southwestern corner */
    private final LatLng SOUTHWEST_BOUNDS = new LatLng(-90, -180);

    /**
     * Called when the activity is first created.
     * @param savedInstanceState The last saved state as a Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try{
            // Loading map
            initializeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to load map. If the map is not created, it will be created now.
     */
    private void initializeMap()
    {
        if(googleMap == null)
        {
            googleMap = ((MapFragment)
                    getFragmentManager().findFragmentById(
                            R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null)
            {
                Toast.makeText(getApplicationContext(), "Unable to create map",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Called when activity starts after creation.
     * Creates a map overlay for entire map.
     */
    public void onStart()
    {
        super.onStart();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Image to overlay on map
        BitmapDescriptor black_bg = (BitmapDescriptorFactory.
                                    fromResource(R.drawable.blk_bg_small2));

        // Get the bounds for image
        LatLngBounds bounds = new LatLngBounds(SOUTHWEST_BOUNDS,
                              NORTHEAST_BOUNDS);

        // Add ground overlay with 50% transparency
        GroundOverlay groundOverlay = googleMap.addGroundOverlay(
                new GroundOverlayOptions()
                .image(black_bg)
                .positionFromBounds(bounds)
                .transparency(1f));

        /*
        // Specify WGS 84 coordinates for WCU
        stillwell = new LatLng(35.31209, -83.18027);

        // WGS 84 coordinates for catafount
        LatLng catafount = new LatLng(35.309776, -83.182514);

        // Create gound overlay options to define a ground overlay
        GroundOverlayOptions goo = new GroundOverlayOptions();

        // Customize options
        goo.image(BitmapDescriptorFactory.fromResource(R.drawable.fountain_overlay));
        // position, width, and height
        goo.position(catafount, 70f, 70f);
        // rotation of image
        //goo.bearing(215.0f);
        GroundOverlay govr = googleMap.addGroundOverlay(goo);

        // Pass coordinates to new CameraUpdate
        CameraUpdate center = CameraUpdateFactory.newLatLng(stillwell);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        // Position and zoom camera
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMyLocationEnabled(true);

        // Add markers
        addMarkers();

        // Make semi-transparent circle around WCU up to Sylva
        makeCircle();

        // Make a polygon around new Health Sciences buildling
        makePolygon();
        */
    }

    /**
     * On resume, reinitialize the map.
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        initializeMap();
    }
}

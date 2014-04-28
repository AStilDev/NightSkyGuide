package com.example.constdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Nick Wilson, Alisha Hayman
 * @version 4.10.14
 * 
 * Class provides entry point into application, it displays the first page
 * including a list of constellations and image of the currently selected
 * one.
 */
public class MainActivity extends Activity {
    ImageView img;
    EditText address;
    static String newConst = "Andromeda";
    String realaddress = "default";
    String targetConst = "Andromeda";
    ListView list;
    String[] constList = {
            "Andromeda", "Aquarius", "Aquila", "Aries", "Bootes",
            "Camelopardalis", "Cancer", "Canes Venatici", "Capricornius",
            "Cassiopeia", "Cygnus", "Delphinius", "Draco", "Gemini",
            "Hercules", "Leo Minor", "Leo", "Libra", "Lynx", "Orion",
            "Pegasus", "Perseus", "Pisces", "Sagittarius", "Scorpius",
            "Taurus"
    };

    // will add more of these later
    Integer[] imageId = {
            R.drawable.andromeda, R.drawable.aquarius, R.drawable.aquila,
            R.drawable.aries, R.drawable.bootes, R.drawable.camelopardalis,
            R.drawable.cancer, R.drawable.canes_venatici, 
            R.drawable.capricornius, R.drawable.cassiopeia, R.drawable.cygnus,
            R.drawable.delphinius, R.drawable.draco, R.drawable.gemini,
            R.drawable.hercules, R.drawable.leo_minor, R.drawable.leo,
            R.drawable.libra, R.drawable.lynx, R.drawable.orion,
            R.drawable.pegasus, R.drawable.perseus, R.drawable.pisces,
            R.drawable.sagittarius, R.drawable.scorpius, R.drawable.taurus
    };

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get previous intent
        Intent intent = getIntent();
        String addr = intent.getStringExtra("realaddress");
        if (addr != null) {
            // get previous intents
            realaddress = addr;
            address = (EditText) findViewById(R.id.editText);
            address.setHint(realaddress);
        }

        /******** button section ********************/
        img = (ImageView) findViewById(R.id.imageView1);
        //Button locationButton = (Button) findViewById(R.id.WebLink);
        ImageButton searchButton = (ImageButton) findViewById(R.id.button2);
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the image is clicked, go to details page
                Intent intent = new Intent(MainActivity.this, DataBaseActivity.class);
                intent.putExtra("selection", targetConst); //Optional parameters
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // relaunch page where address is from edittext
                address = (EditText) findViewById(R.id.editText);
                String realaddress = address.getText().toString();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("realaddress", realaddress);
                startActivity(intent);
            }
        });
        /********** end of button section ***********/

        /********** begin database section **********/
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);

        myDbHelper.openDataBase();
        SQLiteDatabase db = myDbHelper.getDB();

        final String[] data = {"1", "2", "3", "4"};//myDbHelper.getConstInfo(value);

        // Query for
        String simpleQuery = "SELECT * FROM location WHERE minLat=" + getMin(realaddress)
                + " AND vis_period LIKE " + getVisPer();
        Cursor cursor = db.rawQuery(simpleQuery, null);

        // array of constellations to display in list
        ArrayList<String> constsAtLoc = new ArrayList<String>();
        // array of Integers representing drawable ids for given constellations
        ArrayList<Integer> imagesAtLoc = new ArrayList<Integer>();

        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                data[0] = cursor.getString(0); // minLat
                data[1] = cursor.getString(1); // maxLat
                data[2] = cursor.getString(2); // constellation
                data[3] = cursor.getString(3); // visibility period
                //data[4] = cursor.getString(4); // visibility_period

                constsAtLoc.add(data[2]); // add constellation name at cursor

                // get image drawable of this constellation by finding id
                int i = 0;
                while (!constsAtLoc.get(count).equals(constList[i]) && i < constList.length - 1) {
                    i++;
                }

                // get drawable at index i, put in new image array
                imagesAtLoc.add(imageId[i]);

                count++;
            } while (cursor.moveToNext());
        }
        /********** end database section ***********/

        if (constsAtLoc.size() > 0) {
            final String[] constsAtLocArray = constsAtLoc.toArray(new String[constsAtLoc.size()]);
            final Integer[] imagesAtLocArray = imagesAtLoc.toArray(new Integer[imagesAtLoc.size()]);

            img = (ImageView) findViewById(R.id.imageView1);

            // update targetConst and newConst
            targetConst = constsAtLocArray[0];
            newConst = constsAtLocArray[0].replaceAll(" ", "_").toLowerCase();

            int imageNum = getResources().getIdentifier(newConst, "drawable", getPackageName());

            img.setAlpha(170); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque
            img.setImageResource(imageNum);

            // inserting constellations and images at a given location
            //final String[] constsAtLocArray = constsAtLoc.toArray(new String[constsAtLoc.size()]);
            //final Integer[] imagesAtLocArray = imagesAtLoc.toArray(new Integer[imagesAtLoc.size()]);

            CustomList adapter = new CustomList(MainActivity.this,
                    constsAtLocArray, imagesAtLocArray);
            list = (ListView) findViewById(R.id.listView1);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @SuppressWarnings("deprecation")
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // set up things to refresh with new information(selected image displayed
                    targetConst = constsAtLocArray[+position];
                    newConst = constsAtLocArray[+position].replaceAll(" ", "_").toLowerCase();

                    // redisplay image
                    img = (ImageView) findViewById(R.id.imageView1);
                    int imageId = getResources().getIdentifier(newConst, "drawable", getPackageName());
                    img.setAlpha(170); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque
                    img.setImageResource(imageId);
                }
            });
        }
    }

    /**
     * Helper method returns the minimum latitude representation of a given
     * latitude.
     *
     * @param
     * 	address - String, location
     * 
     * @return The new minimum latitude.
     */
    private int getMin(String address) {
        // get current location
        int min = 99;
        GPSTracker gps = null;
        if (address.equals("default")) {
            gps = new GPSTracker(this);
        } else {
            gps = new GPSTracker(this, address);
        }
        int currentLat = (int) gps.getLatitude();

        if (currentLat >= 0) {
            // positive latitude
            min = ((currentLat % 100) - (currentLat % 10));
        } else {
            // negative latitude
            min = -1 * ((Math.abs(currentLat) % 100) - (Math.abs(currentLat) % 10));
        }
        return min;
    }

    /**
     * Helper method gets the current visibility period as a month from the
     * calendar.
     *
     * @return A String representation of the current month to be used in an
     * SQL query.
     */
    private String getVisPer() {
        // find current date (month, precisely)
        // return '%,12,%' String format

        // get the current month (numeric)
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        month++;
        if (month > 12)
            month = 1;

        // format to be used as LIKE action in SQL query
        return "\'%," + month + ",%\'"; //",%\'";
    }
}

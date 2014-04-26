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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity {

    ImageView img;
    String[] dateTime = {};
    static String newConst = "Andromeda";
    String targetConst = "Andromeda";
    // will add more of these later
    ListView list;
    String[] constList = {
            "Andromeda",
            "Aquarius",
            "Aquila",
            "Aries",
            "Bootes",
            "Camelopardalis",
            "Cancer",
            "Canes Venatici",
            "Capricornius",
            "Cassiopeia",
            "Cygnus",
            "Delphinius",
            "Draco",
            "Gemini",
            "Hercules",
            "Leo Minor",
            "Leo",
            "Libra",
            "Lynx",
            "Orion",
            "Pegasus",
            "Perseus",
            "Pisces",
            "Sagittarius",
            "Scorpius",
            "Taurus"
    };

    // will add more of these later
    Integer[] imageId = {
            R.drawable.andromeda,
            R.drawable.aquarius,
            R.drawable.aquila,
            R.drawable.aries,
            R.drawable.bootes,
            R.drawable.camelopardalis,
            R.drawable.cancer,
            R.drawable.canes_venatici,
            R.drawable.capricornius,
            R.drawable.cassiopeia,
            R.drawable.cygnus,
            R.drawable.delphinius,
            R.drawable.draco,
            R.drawable.gemini,
            R.drawable.hercules,
            R.drawable.leo_minor,
            R.drawable.leo,
            R.drawable.libra,
            R.drawable.lynx,
            R.drawable.orion,
            R.drawable.pegasus,
            R.drawable.perseus,
            R.drawable.pisces,
            R.drawable.sagittarius,
            R.drawable.scorpius,
            R.drawable.taurus
    };

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /******** button section ********************/
        Button locationButton = (Button) findViewById(R.id.WebLink);
        Button detailButton = (Button) findViewById(R.id.button2);

        locationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////// run gps and date functions store in array //////////

                Intent intent = new Intent(MainActivity.this, LatLongLocation.class);
                startActivity(intent);
            }
        });

        detailButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // goto database activity with extra
                Intent intent = new Intent(MainActivity.this, DataBaseActivity.class);
                intent.putExtra("selection", targetConst); //Optional parameters
                startActivity(intent);
            }
        });

        /********** end of button section ***********/

        /********** begin database section **********/

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);

        myDbHelper.openDataBase();
        SQLiteDatabase db = myDbHelper.getDB();

        final String[] data = {"1", "2", "3", "4", "5"};//myDbHelper.getConstInfo(value);

        // Query for
        String simpleQuery = "SELECT * FROM location WHERE minLat=" + getMin()
                + " AND vis_period LIKE " + getVisPer();
        Cursor cursor = db.rawQuery(simpleQuery, null);

        // TODO: array of constellations to display in list
        ArrayList<String> constsAtLoc = new ArrayList<String>();
        // array of Integers representing drawable ids for given constellations
        ArrayList<Integer> imagesAtLoc = new ArrayList<Integer>();

        int count = 0;
        while (cursor != null) {
            cursor.moveToFirst();

            data[0] = cursor.getString(0); // minLat
            data[1] = cursor.getString(1); // maxLat
            data[2] = cursor.getString(2); // constellation
            data[3] = cursor.getString(3); // visibility period
            //data[4] = cursor.getString(4); // visibility_period

            constsAtLoc.add(data[3]); // add constellation name at cursor

            // get image drawable of this constellation by finding id
            int i = 0;
            while (!constsAtLoc.get(count).equals(constList[i])) {
                i++;
            }

            // get drawable at index i, put in new image array
            imagesAtLoc.set(count, imageId[i]);

            count++;

        }

        /********** end database section ***********/

        img = (ImageView) findViewById(R.id.imageView1);

        // TODO: update targetConst and newConst
        targetConst = constsAtLoc.get(0);
        newConst = constsAtLoc.get(0);

        int imageNum = getResources().getIdentifier(newConst, "drawable", getPackageName());

        img.setAlpha(170); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque
        img.setImageResource(imageNum);


        //CustomList adapter = new CustomList(MainActivity.this, constList, imageId);
        // TODO: inserting constellations and images at a given location
        final String[] constsAtLocArray = (String[]) constsAtLoc.toArray();
        final Integer[] imagesAtLocArray = (Integer[]) imagesAtLoc.toArray();

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
                // TODO: targetConst = constList[+position]; // update this for call to database in view
                targetConst = constsAtLocArray[+position];
                // TODO: newConst = constList[+position].replaceAll(" ", "_").toLowerCase();
                newConst = constsAtLocArray[+position].replaceAll(" ", "_").toLowerCase();

                // redisplay image
                img = (ImageView) findViewById(R.id.imageView1);
                int imageId = getResources().getIdentifier(newConst, "drawable", getPackageName());
                img.setAlpha(170); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque
                img.setImageResource(imageId);
            }
        });
    }

    private int getMin() {
        // 60, 70, etc?
        // get current location
        int min = 99;
        GPSTracker gps = new GPSTracker(this);
        int currentLat = (int) gps.getLatitude();

        if (currentLat >= 0) {
            min = (currentLat % 10) * 10;
            /*switch (currentLat % 10) {
                case 6:
                    min = 60;
                    break;
                case 5:
                    min = 50;
                    break;
                case 4:
                    min = 40;
                    break;
                case 3:
                    min = 30;
                    break;
                case 2:
                    min = 20;
                    break;
                case 1:
                    min = 10;
                    break;
                case 0:
                    min = 0;
                    break;
            }*/
        } else {
            min = (Math.abs(currentLat) % 10) * -10;
            /*switch (Math.abs(currentLat) % 10) {
                case 6:
                    min = -60;
                    break;
                case 5:
                    min = -50;
                    break;
                case 4:
                    min = -40;
                    break;
                case 3:
                    min = -30;
                    break;
                case 2:
                    min = -20;
                    break;
                case 1:
                    min = -10;
                    break;
                default:
                    break;
            }*/
        }

        return min;
    }

    private String getVisPer() {
        // find current date (month, precisely)
        // return '%,12,%' String format

        // get the current month (numeric)
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        month++;

        return "\'%," + month + "%\'"; //",%\'";
    }
}

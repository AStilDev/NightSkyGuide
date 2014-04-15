package com.example.constdemo;

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
/**
 * 
 * @author Nick Wilson, Alisha Hayman
 * 
 * Class is beginning of application for now it works with the appropriate
 * xml file to display a list of constellations, a picture of the currently
 * selected constellation, and buttons that will help navigate through the
 * application.
 *
 */
public class MainActivity extends Activity {
	/* used to display picture of constellation */
	ImageView img;
    String[] dateTime = {};
	static String newConst = "Andromeda";
	String targetConst = "Andromeda";
	// will add more of these later
	ListView list;
	  String[] constList = {
	    "Andromeda", "Aquarius", "Aquila", "Aries", "Bootes", "Camelopardalis",
	      "Cancer", "Canes Venatici", "Capricornius", "Cassiopeia", "Cygnus",
	      "Delphinius", "Draco", "Gemini", "Hercules", "Leo Minor", "Leo",
	      "Libra", "Lynx", "Orion", "Pegasus", "Perseus", "Pisces", 
	      "Sagittarius", "Scorpius", "Taurus"
	  };
	  
	  // will add more of these later
	  Integer[] imageId = {
	      R.drawable.andromeda, R.drawable.aquarius, R.drawable.aquila, 
	      R.drawable.aries,   R.drawable.bootes, R.drawable.camelopardalis,
	      R.drawable.cancer, R.drawable.canes_venatici,
	      R.drawable.capricornius, R.drawable.cassiopeia, R.drawable.cygnus,
	      R.drawable.delphinius, R.drawable.draco, R.drawable.gemini,
	      R.drawable.hercules, R.drawable.leo_minor, R.drawable.leo,
	      R.drawable.libra, R.drawable.lynx, R.drawable.orion,
	      R.drawable.pegasus, R.drawable.perseus, R.drawable.pisces,
	      R.drawable.sagittarius, R.drawable.scorpius, R.drawable.taurus    
	  };
	  
	/**
	 * Method is called when this application view is loaded.
	 * This method also accomplishes most of the work done on this
	 * particular application view.
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  
		Button locationButton = (Button) findViewById(R.id.WebLink);
		Button detailButton   = (Button) findViewById(R.id.button2);  
		locationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {   	
				Intent intent = new Intent(MainActivity.this, LatLongLocation.class);
				startActivity(intent);
			}    
		});
		detailButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			    Intent intent = new Intent(MainActivity.this, DataBaseActivity.class);
		    	intent.putExtra("selection", targetConst); //Optional parameters
				startActivity(intent);
			}
		});  
		img = (ImageView) findViewById(R.id.imageView1);
		int imageNum = getResources().getIdentifier(newConst, "drawable", getPackageName());
   	    img.setAlpha(130); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque
		img.setImageResource(imageNum); 
		CustomList adapter = new CustomList(MainActivity.this, constList, imageId);
		list=(ListView)findViewById(R.id.listView1);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
	        public void onItemClick(AdapterView<?> parent, View view,
	                                        int position, long id) {
				// set up things to refresh with new information(selected image displayed
	            targetConst = constList[+ position]; // update this for call to database in view  	
	            newConst = constList[+ position].replaceAll(" ", "_").toLowerCase();
	            // redisplay image
	            img = (ImageView) findViewById(R.id.imageView1);
	            int imageId = getResources().getIdentifier(newConst, "drawable", getPackageName());
	            img.setImageResource(imageId);                 
	        }
	    });
	  }
}
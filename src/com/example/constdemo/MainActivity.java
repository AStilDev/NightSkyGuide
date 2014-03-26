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
	      "Canes Venatici",  //cant add without having more pics as well or it crashes
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
		  Button detailButton   = (Button) findViewById(R.id.button2);
		  
		  locationButton.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View v) {
			    	////////////// run gps and date functions store in array //////////
				    	
			    	Intent intent = new Intent(MainActivity.this, LatLongLocation.class);
		    		//intent.putExtra("time", dateTime ); //Optional parameters
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
		  
		  img = (ImageView) findViewById(R.id.imageView1);
		  int imageNum = getResources().getIdentifier(newConst, "drawable", getPackageName());
        
		  img.setAlpha(130); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque
		  img.setImageResource(imageNum);
	    
	    
		  CustomList adapter = new CustomList(MainActivity.this, constList, imageId);
		  list=(ListView)findViewById(R.id.listView1);
		  list.setAdapter(adapter);
		  list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                @SuppressWarnings("deprecation")
					@Override
	                public void onItemClick(AdapterView<?> parent, View view,
	                                        int position, long id) {
	                    	                    
	                    // set up things to refresh with new information(selected image displayed
	                	targetConst = constList[+ position]; // update this for call to database in view
	                	
	                    newConst = constList[+ position].replaceAll(" ", "_").toLowerCase();
	                    //Toast.makeText(MainActivity.this, "New constelation displayed will be " + newConst, Toast.LENGTH_SHORT).show();
	                    
	                    // redisplay image
	                    img = (ImageView) findViewById(R.id.imageView1);
	                    int imageId = getResources().getIdentifier(newConst, "drawable", getPackageName());
	                    
	                    img.setAlpha(130); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque
	                    img.setImageResource(imageId);
	                    
	                }
	            });
	  }
	
}

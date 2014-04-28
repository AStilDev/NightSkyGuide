package com.example.constdemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author Nick Wilson, Alisha Hayman
 * @version 4.3.14
 * 
 * Class enables the use of custom lists with images embedded inside
 * of them. 
 *
 */
public class CustomList extends ArrayAdapter<String>{
	/* Current activity */
	private final Activity context;
	
	/* Names of constellations in an array */
	private final String[] web;
	
	/* id numbers for images */
	private final Integer[] imageId;

	/**
	 * Constructor it initializes the fields of the class.
	 * 
	 * @param
	 * 	context - Activity type, the current activity
	 * 	web		- an array of names for the constellations
	 *  imageId - an array of Integers representing the ids of the images
	 */
	public CustomList(Activity context,	String[] web, Integer[] imageId) {
		super(context, R.layout.list_single, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
	}
	
	/**
	 * Method gets and returns a View object.
	 * @param
	 * 	position  - int that is position on the list
	 * 	view	  - View, current view
	 *  parent	  - ViewGroup
	 *  
	 * @return
	 * 	View - the view that app will go to
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.list_single, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(web[position]);
		imageView.setImageResource(imageId[position]);
		return rowView;
	}
}

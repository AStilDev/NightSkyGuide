package com.example.constdemo;

/**
 * 
 * @author Nick Wilson, Alisha Hayman
 * @version 4.1.14
 * 
 * Class models a Constellation
 *
 */
public class Constellation {
	
	/* Constellation's id in the sqlite database */
	private int id;
	
	/* Name of constellation */
	private String name;
	
	/* Common used abbreviation */
	private String abbreviation;
	
	/* Symbolic representation */
	private String symbol;
	
	/* Family that constellation belongs to */
	private String family;
	
	/* Closest star to earth (in light years) */
	private String closest_star;
	
	/* Address to wikipedia page */
	private String wiki_link;
	
	/**
	 * Default constructor
	 */
	public Constellation() {
	}
	
	/**
	 * Constructor that initializes the fields of the class with
	 * the specified parameters.
	 * 
	 * @param name - name of star
	 * @param abbreviation - commonly used abbreviation
	 * @param symbol - commonly used symbol
	 * @param family - family of constellations that this 
	 * 				   constellation belongs to
	 * @param closest_star - the closest star to earth
	 * @param wiki_link - link to wikipedias page for more info,
	 * 					  may be used eventually in webview
	 */
	public Constellation(String name, String abbreviation, String symbol, 
				String family, String closest_star, String wiki_link) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.symbol = symbol;
		this.family = family;
		this.closest_star = closest_star;
		this.wiki_link = wiki_link;
	}
	
	/**
	 * Method returns a string that lists the fields
	 * of the constellation object.
	 * 
	 * @return
	 * 		String - listing the the fields of the object
	 */
	public String toString() {
		return name + " " + abbreviation + " " + symbol +
				" " + family + " " + closest_star + " " +
				wiki_link;
	}
	
	/************* Getters ************/
	
	/**
	 * Method returns the id field of the object.
	 * 
	 * @return
	 * 		int - the id of the object in the database
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Method returns name of constellation.
	 * 
	 * @return 
	 * 		name - name of constellation
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method returns abbreviation of constellation.
	 * 
	 * @return
	 * 		String - abbreviation of constellation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}
	
	/**
	 * Method returns symbol of constellation.
	 * 
	 * @return
	 * 		String - symbol of constellation
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Method returns family of constellation.
	 * 
	 * @return
	 * 		String - family of constellation
	 */
	public String getFamily() {
		return family;
	}
	
	/**
	 * Method returns name of closest star in the constellation.
	 * 
	 * @return
	 * 		String - name of closest star
	 */
	public String getClosestStar() {
		return closest_star;
	}
	
	/**
	 * Method returns wikipedia address for the constellation.
	 * 
	 * @return
	 * 		String - wikipedia address for the constellation
	 */
	public String getWikiLink() {
		return wiki_link;
	}
	
	/**************** Setters *****************/
	
	/**
	 * Method sets the id field of the object.
	 * 
	 * @param data - int, id of object
	 */
	public void setId(int data) {
		this.id = data;
	}
	
	/**
	 * Method sets the name field for the object.
	 * 
	 * @param data - String containing name for the object
	 */
	public void setName(String data) {
		this.name = data;
	}
	
	/**
	 * Method sets the abbreviation field for the object.
	 * 
	 * @param data - String containing abbreviation for the object
	 */
	public void setAbbreviation(String data) {
		this.abbreviation = data;
	}
	
	/**
	 * Method sets the symbol field for the object.
	 * 
	 * @param data - String containing symbol for constellation
	 */
	public void setSymbol(String data) {
		this.symbol = data;
	}
	
	/**
	 * Method sets the family field for the object.
	 * 
	 * @param data - String containing family for constellation
	 */
	public void setFamily(String data) {
		this.family = data;
	}
	
	/**
	 * Method sets closest star field for the object.
	 * 
	 * @param data - String, name of closest star to earth in the 
	 * 			     constellation
	 */
	public void setClosestStar(String data) {
		this.closest_star = data;
	}
	
	/**
	 * Method sets wikipedia address for constellation.
	 * @param data - String containing address for wikipedia page
	 */
	public void setWikiLink(String data) {
		this.wiki_link = data;
	}
}

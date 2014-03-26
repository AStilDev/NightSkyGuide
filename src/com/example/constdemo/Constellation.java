package com.example.constdemo;

public class Constellation {
	private int id;
	private String name;
	private String abbreviation;
	private String symbol;
	private String family;
	private String closest_star; // this will be name of star and approximate distance in light years
	private String wiki_link; // address to wikipedia page
	
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
	
	public String toString() {
		return name + " " + abbreviation + " " + symbol +
				" " + family + " " + closest_star + " " +
				wiki_link;
	}
	
	/************* Getters ************/
	
	public int getId() {
		return id;
	}
	
	/**
	 * Method returns name of constellation.
	 * 
	 * @return name - name of constellation
	 */
	public String getName() {
		return name;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getFamily() {
		return family;
	}
	
	public String getClosestStar() {
		return closest_star;
	}
	
	public String getWikiLink() {
		return wiki_link;
	}
	
	/**************** Setters *****************/
	
	public void setId(int data) {
		this.id = data;
	}
	public void setName(String data) {
		this.name = data;
	}
	
	/**
	 * 
	 * @param data
	 */
	public void setAbbreviation(String data) {
		this.abbreviation = data;
	}
	
	public void setSymbol(String data) {
		this.symbol = data;
	}
	
	public void setFamily(String data) {
		this.family = data;
	}
	
	public void setClosestStar(String data) {
		this.closest_star = data;
	}
	
	public void setWikiLink(String data) {
		this.wiki_link = data;
	}

}

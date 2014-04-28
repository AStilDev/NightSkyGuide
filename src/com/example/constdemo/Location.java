package com.example.constdemo;

import java.util.ArrayList;

/* Use for location queries. */
public class Location {

    private int id;
    private int minLat;
    private int maxLat;
    private String constellation;
    private String vis_period;

    public Location (int min, int max, String constel, String vis)
    {
        minLat = min;
        maxLat = max;
        constellation = constel;
        vis_period = vis;
    }

    public int getId()
    {
        return id;
    }

    public int getMinLat()
    {
        return minLat;
    }

    public int getMaxLat()
    {
        return maxLat;
    }

    public String getConstellation()
    {
        return constellation;
    }

    public String getVis_period() { return vis_period; }

    /**
     * Method sets the id field.
     * 
     * @param
     * 	data - int, the new value for the id
     */ 
    public void setId(int data)
    {
        id = data;
    }

    /**
     * Method sets the minimum latitude.
     * 
     * @param
     * 	min - int, the new value for the minimum latitude
     */
    public void setMinLat(int min)
    {
        minLat = min;
    }

    /**
     * Method sets the maximum latitude.
     * 
     * @param
     * 	max - int, the new value for the maximum latitude
     */
    public void setMaxLat(int max)
    {
        maxLat = max;
    }

    /**
     * Method sets the constellation field.
     * 
     * @param
     * 	constel - String, constellation that the constellation field will be
     * 			  set too
     */
    public void setConstellation(String constel)
    {
        constellation = constel;
    }

    /**
     * Method
     * 
     * @param
     * 	vis - String,
     */
    public void setVis_period(String vis)
    {
        vis_period = vis;
    }
}
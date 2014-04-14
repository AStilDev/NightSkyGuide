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

    public void setId(int data)
    {
        id = data;
    }

    public void setMinLat(int min)
    {
        minLat = min;
    }

    public void setMaxLat(int max)
    {
        maxLat = max;
    }

    public void setConstellations(ArrayList<Constellation> list)
    {
        constellations = list;
    }
}

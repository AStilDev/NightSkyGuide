package com.example.constdemo;

import java.util.ArrayList;

/* Use for location queries. */
public class Location {

    private int id;
    private int minLat;
    private int maxLat;
    private ArrayList<Constellation> constellations;

    public Location (int min, int max, ArrayList<Constellation> list)
    {
        minLat = min;
        maxLat = max;
        constellations = list;
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

    public ArrayList<Constellation> getConstellations()
    {
        return constellations;
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

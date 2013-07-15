package com.example.mobicanavigate.model;

/**
 * @author Miłosz Skalski
 */

public class Office {
    private String mName;
    private double mLatitude;
    private double mLongitude;

    public Office(String name, double latitude, double longitute) {
        mName = name;
        mLatitude = latitude;
        mLongitude = longitute;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public String getmName() {
        return mName;
    }

}

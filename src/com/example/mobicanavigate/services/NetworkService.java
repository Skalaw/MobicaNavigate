package com.example.mobicanavigate.services;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;

/**
 * @author Miłosz Skalski
 */

public class NetworkService {

    public static Boolean isInternetOn(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting() == true) {
            return true;
        }
        return false;
    }

    public static Boolean isGpsOn(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return true;
        }
        return false;
    }
}

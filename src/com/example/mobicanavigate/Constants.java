package com.example.mobicanavigate;

import com.example.mobicanavigate.model.Office;

/**
 * @author Miłosz Skalski
 */

public class Constants {
    public static final int SPLASHSCREEN_DELAY = 2000;
    public static final int REFRESH_TIME_GPS = 2000;
    public static final String EMPTY_STRING = "";
    public static final String NEW_LINE = "\n";
    public static final String TAG = "MobicaNavigate";
    public static final String SHARED_PREFERENCES_FILE = "MobicaNavigate";
    public static final String SHARED_PREFERENCES_FIRST_RUN = "first_run";
    public static final String SHARED_PREFERENCES_LANGUAGE = "language";
    public static final String SHARED_PREFERENCES_OFFICE = "office";
    public static final String SHARED_PREFERENCES_CAMERA_MAP = "camera_map";
    public static final Office[] MOBICA_OFFICE = {
            new Office("Szczecin", 53.429264, 14.556242),
            new Office("Warszawa", 52.172119, 21.025520),
            new Office("Łódź", 51.753592, 19.455640),
            new Office("Bydgoszcz", 53.137551, 18.127196)};
}

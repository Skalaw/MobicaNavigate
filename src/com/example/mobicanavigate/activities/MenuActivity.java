package com.example.mobicanavigate.activities;

import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobicanavigate.Constants;
import com.example.mobicanavigate.R;
import com.example.mobicanavigate.dialogs.ChoiceDialog;
import com.example.mobicanavigate.services.NetworkService;

import java.util.Locale;

/**
 * @author Miłosz Skalski
 */

public class MenuActivity extends FragmentActivity implements OnClickListener {
    private Boolean mIsEnglish;
    private Button mBtnMap;
    private Button mBtnGpsSettings;
    private Button mBtnInternetSettings;
    private Button mBtnChangeLanguage;
    private Button mBtnAuthor;
    private Button mBtnExit;
    private ImageView mArrowLeft;
    private ImageView mArrowRight;
    private TextView mTextOffice;
    private int mControlOffice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        mBtnMap = (Button) findViewById(R.id.btnMap);
        mBtnMap.setOnClickListener(this);
        mBtnGpsSettings = (Button) findViewById(R.id.btnGpsSettings);
        mBtnGpsSettings.setOnClickListener(this);
        mBtnInternetSettings = (Button) findViewById(R.id.btnInternetSettings);
        mBtnInternetSettings.setOnClickListener(this);
        mBtnChangeLanguage = (Button) findViewById(R.id.btnChangeLanguage);
        mBtnChangeLanguage.setOnClickListener(this);
        mBtnAuthor = (Button) findViewById(R.id.btnAuthor);
        mBtnAuthor.setOnClickListener(this);
        mBtnExit = (Button) findViewById(R.id.btnExit);
        mBtnExit.setOnClickListener(this);
        mArrowLeft = (ImageView) findViewById(R.id.imageArrowLeft);
        mArrowLeft.setOnClickListener(this);
        mArrowRight = (ImageView) findViewById(R.id.imageArrowRight);
        mArrowRight.setOnClickListener(this);
        mTextOffice = (TextView) findViewById(R.id.textOffice);

        mControlOffice = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).getInt(Constants.SHARED_PREFERENCES_OFFICE, 0);
        changeTextOffice();
        boolean firstRun = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).getBoolean(Constants.SHARED_PREFERENCES_FIRST_RUN, true);
        if (firstRun) {
            firstRun();
        } else {
            mIsEnglish = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).getBoolean(Constants.SHARED_PREFERENCES_LANGUAGE, true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMap:
                runMap();
                break;

            case R.id.btnGpsSettings:
                Intent gpsOptionsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(gpsOptionsIntent);
                break;

            case R.id.btnInternetSettings:
                Intent internetOptionsIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(internetOptionsIntent);
                break;

            case R.id.btnChangeLanguage:
                mIsEnglish = !mIsEnglish;
                getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).edit().putBoolean(Constants.SHARED_PREFERENCES_LANGUAGE, mIsEnglish).commit();
                changeLanguage();
                break;

            case R.id.btnAuthor:
                Intent author = new Intent(this, AuthorActivity.class);
                startActivity(author);
                overridePendingTransition(R.layout.fadein, R.layout.fadeout);
                break;

            case R.id.btnExit:
                getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).edit().putInt(Constants.SHARED_PREFERENCES_OFFICE, mControlOffice).commit();
                finish();
                break;

            case R.id.imageArrowLeft:
                mControlOffice--;
                if (mControlOffice < 0) {
                    mControlOffice = Constants.MOBICA_OFFICE.length - 1;
                }
                changeTextOffice();
                break;

            case R.id.imageArrowRight:
                mControlOffice++;
                if (mControlOffice > Constants.MOBICA_OFFICE.length - 1) {
                    mControlOffice = 0;
                }
                changeTextOffice();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).edit().putInt(Constants.SHARED_PREFERENCES_OFFICE, mControlOffice).commit();
        finish();
    }

    private void runMap() {
        String info = Constants.EMPTY_STRING;
        if (!NetworkService.isInternetOn(this)) {
            info += getResources().getString(R.string.no_internet_connection) + Constants.NEW_LINE;
        }
        if (!NetworkService.isGpsOn(this)) {
            info += getResources().getString(R.string.no_gps_connection) + Constants.NEW_LINE;
        }
        if (info.isEmpty()) {
            Intent intent = new Intent(this, MapActivity.class);
            intent.putExtra("latitude", Constants.MOBICA_OFFICE[mControlOffice].getmLatitude());
            intent.putExtra("longitude", Constants.MOBICA_OFFICE[mControlOffice].getmLongitude());
            startActivity(intent);
            overridePendingTransition(R.layout.fadein, R.layout.fadeout);
        } else {
            info += getResources().getString(R.string.warning);
            final ChoiceDialog fragment = new ChoiceDialog(info);
            OnClickListener positiveListener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    intent.putExtra("latitude", Constants.MOBICA_OFFICE[mControlOffice].getmLatitude());
                    intent.putExtra("longitude", Constants.MOBICA_OFFICE[mControlOffice].getmLongitude());
                    startActivity(intent);
                    overridePendingTransition(R.layout.fadein, R.layout.fadeout);
                    fragment.dismiss();
                }
            };
            fragment.setmListenerPositive(positiveListener);
            fragment.show(getSupportFragmentManager(), null);
        }
    }

    private void firstRun() {
        getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).edit().putBoolean(Constants.SHARED_PREFERENCES_FIRST_RUN, false).commit();
        final ChoiceDialog fragment = new ChoiceDialog(getResources().getString(R.string.first_run_main_text));
        OnClickListener positiveListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsEnglish = true;
                fragment.dismiss();
                getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).edit().putBoolean(Constants.SHARED_PREFERENCES_LANGUAGE, mIsEnglish).commit();
                changeLanguage();
            }
        };
        fragment.setmListenerPositive(positiveListener);
        OnClickListener negativeListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsEnglish = false;
                fragment.dismiss();
                getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, MODE_PRIVATE).edit().putBoolean(Constants.SHARED_PREFERENCES_LANGUAGE, mIsEnglish).commit();
                changeLanguage();
            }
        };
        fragment.setmListenerNegative(negativeListener);
        fragment.setmNegativeText(getResources().getString(R.string.first_run_negative_button));
        fragment.setmPositiveText(getResources().getString(R.string.first_run_positive_button));
        fragment.show(getSupportFragmentManager(), null);
    }

    private void changeLanguage() {
        String languageToLoad;
        if (mIsEnglish) {
            languageToLoad = "en";
        } else {
            languageToLoad = "pl";
        }
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        finish();
        startActivity(getIntent());
    }

    private void changeTextOffice() {
        mTextOffice.setText(Constants.MOBICA_OFFICE[mControlOffice].getmName());
    }

}

package com.example.mobicanavigate.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.mobicanavigate.Constants;
import com.example.mobicanavigate.R;

/**
 * @author Miłosz Skalski
 */

public class SplashScreenActivity extends Activity {
    private Handler mHandler;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        mHandler = new Handler();
        mHandler.postDelayed(mThread = new Thread() {
            @Override
            public void run() {
                Intent menu = new Intent(SplashScreenActivity.this, MenuActivity.class);
                SplashScreenActivity.this.startActivity(menu);
                SplashScreenActivity.this.finish();
                overridePendingTransition(R.layout.fadein, R.layout.fadeout);
            }
        }, Constants.SPLASHSCREEN_DELAY);
    }

    @Override
    public void onBackPressed() {
        mHandler.removeCallbacks(mThread);
        finish();
    }
}

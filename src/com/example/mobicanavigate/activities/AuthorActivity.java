package com.example.mobicanavigate.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mobicanavigate.R;

/**
 * @author Miłosz Skalski
 */

public class AuthorActivity extends Activity {
    private TextView text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        setContentView(R.layout.activity_author);
        text = (TextView) findViewById(R.id.textOffice);
        text.setSelected(true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
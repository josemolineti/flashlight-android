package com.example.flashlight;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton main_button_flashlight;
    //obsolete SeekBar intensity_flash_progress;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch theme_mode_switcher;
    boolean darkMODE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        FlashlightOperator flashlightOperator = new FlashlightOperator(MainActivity.this);
        main_button_flashlight = findViewById(R.id.flashlight_button);
        //obsolete intensity_flash_progress = findViewById(R.id.intensity_progress);
        theme_mode_switcher = findViewById(R.id.theme_mode_switcher);

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        darkMODE = sharedPreferences.getBoolean("dark", false);
        if (!darkMODE) {
            theme_mode_switcher.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            theme_mode_switcher.setChecked(true);
        }

        //create svgs for the switcher
        theme_mode_switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashlightOperator.executeClickSound(MainActivity.this);
                if (flashlightOperator.isTorchOn()) {
                    main_button_flashlight.setActivated(false);
                } else {
                    main_button_flashlight.setActivated(true);
                }
                if(darkMODE){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("dark", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("dark", true);

                }
                editor.apply();
                darkMODE = !darkMODE;
            }
        });


        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){

            } else {
                Toast.makeText(MainActivity.this, "This device has no flash support.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "This device has no camera.", Toast.LENGTH_LONG).show();
        }

        main_button_flashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flashlightOperator.isTorchOn()) {
                    flashlightOperator.turnLightOff(view, MainActivity.this);

                } else {
                    flashlightOperator.turnLightOn(view, MainActivity.this);

                }

            }
        });

        //obsolete
//        intensity_flash_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                flashlightOperator.changeFlashlightIntensity(i, MainActivity.this);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
    }
}
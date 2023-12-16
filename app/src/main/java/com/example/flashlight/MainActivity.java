package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton main_button_flashlight;
    SeekBar intensity_flash_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlashlightOperator flashlightOperator = new FlashlightOperator(MainActivity.this);
        main_button_flashlight = findViewById(R.id.flashlight_button);
        intensity_flash_progress = findViewById(R.id.intensity_progress);


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

                if (view.isActivated()) {
                    flashlightOperator.turnLightOff(view, MainActivity.this);

                } else {
                    flashlightOperator.turnLightOn(view, MainActivity.this);

                }

            }
        });

        intensity_flash_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                flashlightOperator.changeFlashlightIntensity(i, MainActivity.this);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
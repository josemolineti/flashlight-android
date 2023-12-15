package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton main_button_flashlight = findViewById(R.id.flashlight_button);
    CameraManager camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // exec. doesn't working
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            camera = (CameraManager) getSystemService(CAMERA_SERVICE);
        }

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
                Flashlight flashlight = new Flashlight();
                if (view.isActivated()) {
                    //flashlight.turnLightOff(view);

                } else {
                    //flashlight.turnLightOn(view);

                }

            }
        });
    }
}
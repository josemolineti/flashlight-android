package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton main_button_flashlight = findViewById(R.id.flashlight_button);
        CameraManager camera;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            camera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        }

        main_button_flashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flashlight flashlight = new Flashlight();
                if (view.isActivated()) {
                    flashlight.turnLightOn();
                    view.setActivated(false);
                } else {
                    view.setActivated(true);
                }

            }
        });
    }
}
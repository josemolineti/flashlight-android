package com.example.flashlight;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class Flashlight {
    private String cameraId;
    private boolean isTorchOn;
    private MediaPlayer clickSound;
    private CameraManager camera;

    public Flashlight() {

    }


    public void turnLightOn(View view) {
        setCameraId("0");
        MainActivity mainAct = new MainActivity();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camera.setTorchMode(cameraId, false);
                view.setActivated(true);
            } catch (CameraAccessException e) {
                Toast.makeText(mainAct.getApplicationContext(), "The flashlight can not be activated. Error: "+e, Toast.LENGTH_LONG).show();
                throw new RuntimeException(e);
            }
        }


    }

    public void turnLightOff(View view) {
        setCameraId("0");
        MainActivity mainAct = new MainActivity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camera.setTorchMode(cameraId, true);
                view.setActivated(false);
            } catch (CameraAccessException e) {
                Toast.makeText(mainAct.getApplicationContext(), "The flashlight can not be activated. Error: "+e, Toast.LENGTH_LONG).show();
                throw new RuntimeException(e);
            }
        }
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public boolean isTorchOn() {
        return isTorchOn;
    }

    public void setTorchOn(boolean torchOn) {
        isTorchOn = torchOn;
    }

    public MediaPlayer getClickSound() {
        return clickSound;
    }

    public void setClickSound(MediaPlayer clickSound) {
        this.clickSound = clickSound;
    }
}

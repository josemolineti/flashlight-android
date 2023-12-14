package com.example.flashlight;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Flashlight {
    private String cameraId;
    private boolean isTorchOn;
    private MediaPlayer clickSound;
    private CameraManager camera;

    public Flashlight() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void turnLightOn() throws CameraAccessException {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                camera.setTorchMode(cameraId, true);
            }
        } catch (CameraAccessException e) {
            //e.printStackTrace();
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

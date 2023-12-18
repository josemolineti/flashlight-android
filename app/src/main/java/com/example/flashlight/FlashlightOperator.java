package com.example.flashlight;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class FlashlightOperator {
    private String cameraId;
    private boolean isTorchOn;
    private MediaPlayer clickSound;
    private CameraManager camera;
    private CaptureRequest.Builder captureRequestBuilder;

    public FlashlightOperator(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.camera = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        }
    }


    public void turnLightOn(View view, Context context) {
        setCameraId("0");
        executeClickSound(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camera.setTorchMode(cameraId, true);
                isTorchOn = true;
                view.setActivated(true);
            } catch (CameraAccessException e) {
                Toast.makeText(context, "The flashlight can not be activated. Error: "+e, Toast.LENGTH_LONG).show();
                throw new RuntimeException(e);
            }
        }


    }

    public void turnLightOff(View view, Context context) {
        setCameraId("0");
        executeClickSound(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camera.setTorchMode(cameraId, false);
                isTorchOn = false;
                view.setActivated(false);
            } catch (CameraAccessException e) {
                Toast.makeText(context, "The flashlight can not be activated. Error: "+e, Toast.LENGTH_LONG).show();
                throw new RuntimeException(e);
            }
        }
    }

    public void changeFlashlightIntensity(int flashlight_intensity, Context context) {
        if (captureRequestBuilder != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                captureRequestBuilder.set(
                        CaptureRequest.FLASH_MODE,
                        flashlight_intensity > 0.0 ? CaptureRequest.FLASH_MODE_TORCH : CaptureRequest.FLASH_MODE_OFF
                );
            }
        }
    }

    public void executeClickSound(Context context) {
        if (clickSound == null) {
            clickSound = MediaPlayer.create(context, R.raw.click_sond);
        }

        clickSound.start();

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

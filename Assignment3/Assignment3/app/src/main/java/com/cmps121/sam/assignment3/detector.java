package com.cmps121.sam.assignment3;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class detector extends Service implements SensorEventListener {
    //constructor??
    private SensorManager detectorSensor;
    private Sensor accelerometer;
    private float currentMovement;
    private float lastMovement;
    int hasMoved = 0;
    int clearBtn = 0;




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        detectorSensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = detectorSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        detectorSensor.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI, new Handler());
        clearBtn = intent.getIntExtra("SAM", 0);
        System.out.println(clearBtn);
        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float xMotion = sensorEvent.values[0];
        float yMotion = sensorEvent.values[1];

        lastMovement = currentMovement;
        currentMovement = (float) Math.sqrt(xMotion*xMotion + yMotion*yMotion);

        float diff = currentMovement - lastMovement;


        if(diff != 0.0){
            changeToMoved();
        }else{
            changeToNotMoved();
        }

    }



    private void changeToMoved(){
        hasMoved++;
        Intent localUpdate = new Intent();
        localUpdate.setAction("ActionTextView");
        this.sendBroadcast(localUpdate);
        //System.out.println("It moved");
    }
    private void changeToNotMoved(){
        if(clearBtn == 1){
            hasMoved = 0;
            clearBtn =0;
        }

        if(hasMoved > 1){
            Intent localUpdate = new Intent();
            localUpdate.setAction("ActionTextView");
            this.sendBroadcast(localUpdate);
        }
        //System.out.println("It hasn't moved");
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

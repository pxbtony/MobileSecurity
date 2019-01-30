package com.example.administrator.mobilesecurity.common;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import com.example.administrator.mobilesecurity.MSActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SensorIntentService extends Service implements SensorEventListener {
    private SensorManager manager;
    private Sensor mSensorOrientation;
    private Vibrator mVibrator;
    private float a = 0.0f ;
    private float b = 0.0f ;
    private float c = 0.0f ;
    private Thread mThread;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        manager = (SensorManager) getApplicationContext().getSystemService(SENSOR_SERVICE);
        mSensorOrientation = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        manager.registerListener(this,mSensorOrientation,SensorManager.SENSOR_DELAY_UI);

        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i("xianbin","service");
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float aa = (float) (Math.round(event.values[0] * 100)) / 100;
            float bb = (float) (Math.round(event.values[1] * 100)) / 100;
            float cc = (float) (Math.round(event.values[2] * 100)) / 100;
            Log.i("binbin","a="+Math.abs(a - aa)+"-----"+"b="+Math.abs(b - bb)+"-----"+"c="+Math.abs(c - cc));
            if (Math.abs(b - bb) > 20 || Math.abs(c - cc) > 20) {

              new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new AlertMessage("still",true));
                    }
                }).start();

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.unregisterListener(this,mSensorOrientation);
    }
}

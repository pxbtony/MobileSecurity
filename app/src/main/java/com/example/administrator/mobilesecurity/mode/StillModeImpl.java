package com.example.administrator.mobilesecurity.mode;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.mobilesecurity.common.AlertMessage;
import com.example.administrator.mobilesecurity.common.SensorIntentService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StillModeImpl implements IBaseMode  {
    private ShowTheAlerm mshowTheAlerm;

    public StillModeImpl(Context context){

    }

    @Override
    public void startSensorMonitor(Context context) {
           Log.i("xianbin","startSensorMonitor_mode");
           Intent intent = new Intent(context , SensorIntentService.class);
           context.startService(intent);
    }

    @Override
    public void closeSensorMonitor(Context context) {

    }

}

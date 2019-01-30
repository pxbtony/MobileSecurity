package com.example.administrator.mobilesecurity.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.mobilesecurity.mode.IBaseMode;
import com.example.administrator.mobilesecurity.mode.StillModeImpl;

public class StillPresenterImpl extends BasePresenter{
    public Context mContext;
    public StillPresenterImpl(Context context){
        this.mContext = context;
    }

    public StillModeImpl SMImpl = new StillModeImpl(mContext);

    @Override
    public void startSensorMonitor() {
        SMImpl.startSensorMonitor(mContext);
    }

    @Override
    public void closeSensorMonitor() {
        SMImpl.closeSensorMonitor(mContext);
    }
}

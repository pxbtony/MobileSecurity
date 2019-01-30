package com.example.administrator.mobilesecurity.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import com.example.administrator.mobilesecurity.R;
import com.example.administrator.mobilesecurity.common.AlertMessage;
import com.example.administrator.mobilesecurity.common.SharepreferenceUtils;
import com.example.administrator.mobilesecurity.presenter.StillPresenterImpl;
import com.example.administrator.mobilesecurity.utils.ShowPasswordUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class funtionFragment extends Fragment implements View.OnClickListener ,ShowPasswordUtils.CancalTheAlarm
       {
    private Switch mStillSwitch;
    private Switch mFlightSwitch;
    private Switch mPowerSwitch;
    private Switch mPocketSwitch;
    private Switch mShutDownSwitch;
    public ShowPasswordUtils mShowPasswordUtils;
    public AlertDialog.Builder mBuilder;
    public Dialog mDialog;
    public StillPresenterImpl mStillPresenterImpl;
    public funtionFragment(){

     }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStillPresenterImpl = new StillPresenterImpl(getContext());
        EventBus.getDefault().register(this);
    }

    /**
     * 加载功能界面
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View mView = inflater.inflate(R.layout.funtion_fragment, container ,false);
         mStillSwitch = mView.findViewById(R.id.switch_still);
         mFlightSwitch = mView.findViewById(R.id.switch_flight);
         mPowerSwitch = mView.findViewById(R.id.switch_power);
         mPocketSwitch = mView.findViewById(R.id.switch_pocket);
         mShutDownSwitch = mView.findViewById(R.id.switch_ShutDown);
         mStillSwitch.setOnClickListener(this);
         mFlightSwitch.setOnClickListener(this);
         mPowerSwitch.setOnClickListener(this);
         mPocketSwitch.setOnClickListener(this);
         mShutDownSwitch.setOnClickListener(this);
         mShowPasswordUtils = ShowPasswordUtils.getInstance();

        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.switch_still:
                if(mStillSwitch.isChecked()){
                     mStillPresenterImpl.startSensorMonitor();
                    if(!SharepreferenceUtils.getParam(getContext(),"set_passwords",false)){
                        isShowPasswordDialog();
                    }
                }else {
                    //mStillPresenterImpl.startSensorMonitor(false);
                    //mStillPresenterImpl.closeSensorMonitor();
                }
                break;
            case R.id.switch_flight:
                Log.i("xian","----1");
                break;
            case R.id.switch_power:
                Log.i("xian","----2");
                break;
            case R.id.switch_pocket:
                Log.i("xian","----3");
                break;
            case R.id.switch_ShutDown: break;
        }
    }

    private void isShowPasswordDialog() {
        mBuilder = new AlertDialog.Builder(getContext(),R.style.passwordDialog);
                         mBuilder.setTitle("设置密码")
                         .setMessage("是否要设置解除报警密码")
                         .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                        //mShowPasswordUtils.setConfirmPassword(getActivity());
                                        dialog.cancel();

                             }
                         })
                         .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                  mShowPasswordUtils.setPassword(getActivity());
                                  SharepreferenceUtils.setParam(getContext(),"set_passwords",true);
                             }
                         });
        mDialog = mBuilder.create();
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();

    }


    @Override
    public void CancalTheAlarmWhenPasswordIsOk() {
        Log.i("xianbin","CancalTheAlarmWhenPasswordIsOk");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void AlertEventBus(AlertMessage event){
        if(event.name.equals("still") && event.isTure){
        Log.i("xianbin","AlertEventBus");
            mShowPasswordUtils.setConfirmPassword(getContext());
        }
    }

   @Override
   public void onDestroy() {
       super.onDestroy();
       Log.i("xianbin","fun_destroy");
       EventBus.getDefault().unregister(this);
   }


}

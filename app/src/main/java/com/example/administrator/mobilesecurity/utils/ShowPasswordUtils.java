package com.example.administrator.mobilesecurity.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.mobilesecurity.R;
import com.example.administrator.mobilesecurity.View.PasswordView;
import com.example.administrator.mobilesecurity.base.OnPasswordInputFinish;
import com.example.administrator.mobilesecurity.common.PasswordDialog;
import com.example.administrator.mobilesecurity.common.SharepreferenceUtils;

public class ShowPasswordUtils  {

    public static ShowPasswordUtils mShowPasswordUtils;
    public String mLastPassword;
    public boolean mIsShow = false;
    public ShowPasswordUtils(){
    }

    /**
     *
     * 报警后输入密码正确后，取消报警
     */

    public interface CancalTheAlarm{
        void CancalTheAlarmWhenPasswordIsOk();
    }
    public static ShowPasswordUtils getInstance(){
        if (mShowPasswordUtils ==null ){
            synchronized (ShowPasswordUtils.class){
                if (mShowPasswordUtils == null){
                    mShowPasswordUtils = new ShowPasswordUtils();
                }
            }
        }

        return mShowPasswordUtils;
    }

    /**
     * 设置解锁密码
     */
    public void setPassword(Context context){
        Log.i("xianbin","setPassword");
            showPasswordDialog(context ,true);
    }

    /**
     * 确认密码
     */
    public void setConfirmPassword(Context context){
        if(!mIsShow){
        showPasswordDialog(context ,false);
        }
    }

    /**
     * 显示密码对话框
     */
    public void showPasswordDialog(final Context context, final boolean isSet){
        mIsShow =true;
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout L = (LinearLayout) inflater.inflate(R.layout.password_view ,null);
        final PasswordDialog pd = new PasswordDialog(context);
        pd.show();
        pd.setCancelable(true);
        pd.setContentView(L);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.i("xianbins","---dialogInterface");
                mIsShow =false;
            }
        });
        /**
         * 如果是初始设置密码，显示确定按钮
         */
        Button btn = L.findViewById(R.id.setPassword);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharepreferenceUtils.setParam(context,"password",mLastPassword);
                pd.dismiss();
            }
        });
        if (isSet){
            btn.setVisibility(View.VISIBLE);
        }else {
            btn.setVisibility(View.GONE);
        }

        /**
         * 显示密码界面对话框
         */
        final PasswordView passwordView = L.findViewById(R.id.pwd_view);
        passwordView.setOnFinishInput(new OnPasswordInputFinish() {
            @Override
            public void inputFinish() {
                String password = passwordView.getStrPassword();
                if(isSet){
                    mLastPassword = password;
                    Log.i("binbin", "password="+password);
                    mIsShow = false;
                }else {
                    if(password.equals(SharepreferenceUtils.getParam(context,"password","111111"))){
                        mIsShow = false;
                        Log.i("binbin","密码正确");
                        new ShowPasswordUtils.CancalTheAlarm(){

                            @Override
                            public void CancalTheAlarmWhenPasswordIsOk() {

                            }
                        };

                        pd.dismiss();

                    }else {
                        Toast.makeText(context,"密码不正确",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void outfo() {

            }

            @Override
            public void forgetPwd() {

            }
        });

    }



}

package com.example.administrator.mobilesecurity.common;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.administrator.mobilesecurity.R;

public class PasswordDialog extends AlertDialog {
    private Context mContext;
    public PasswordDialog(Context context) {
        super(context,R.style.passwordDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度为全屏
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().getDecorView().setPadding(0,0,0,0);
        getWindow().setAttributes(layoutParams);
    }


}

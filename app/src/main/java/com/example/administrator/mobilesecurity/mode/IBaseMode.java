package com.example.administrator.mobilesecurity.mode;

import android.content.Context;

public interface IBaseMode {
    /**
     * 报警后弹出密码对话框
     */
    public interface ShowTheAlerm {
        void showPasswordWhenTheAlerm();
    }
    /**
     *启动传感器
     */
    void startSensorMonitor( Context Context);
    /**
     * 关闭传感器
     */
    void closeSensorMonitor(Context Context);
}

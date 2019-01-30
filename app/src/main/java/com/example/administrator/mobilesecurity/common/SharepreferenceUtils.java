package com.example.administrator.mobilesecurity.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharepreferenceUtils {

    private static final String FILE_NAME = "password";
    public static void setParam(Context context , String key ,boolean isTure){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("set_passwords",isTure);
        editor.commit();
    }
    public static void setParam(Context context , String key ,String password){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("password",password);
        editor.commit();
    }

    /**
     *
     */

    public static boolean getParam(Context context , String key ,boolean isTure){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        boolean key_value = sp.getBoolean("set_passwords",isTure);
        return key_value ;
    }
    public static String getParam(Context context , String key ,String defalut){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String key_value = sp.getString("password",defalut);
        return key_value ;
    }
}

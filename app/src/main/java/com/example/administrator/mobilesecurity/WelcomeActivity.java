package com.example.administrator.mobilesecurity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {
    private static int time = 2;
    private Timer mTimer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mTimer.schedule(tast,1000,1000);
    }

    TimerTask tast = new TimerTask() {
        @Override
        public void run() {
            time --;
            if (time < 0) {
                mTimer.cancel();
                Intent intent = new Intent(WelcomeActivity.this, MSActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}

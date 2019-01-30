package com.example.administrator.mobilesecurity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.mobilesecurity.fragment.funtionFragment;
import com.example.administrator.mobilesecurity.fragment.messageFragment;
import com.example.administrator.mobilesecurity.fragment.settingsFragment;


public class MSActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextTopbar;
    private TextView mTextFuntion;
    private TextView mTextMessage;
    private TextView mTSettings;
    private FrameLayout mFragment;
    private FragmentManager fManager;
    private funtionFragment fFragment;
    private settingsFragment sFragment;
    private messageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        setView();
        mTextFuntion.performClick();

    }

    private void setView() {
        mTextTopbar = findViewById(R.id.txt_topbar);
        mTextFuntion = findViewById(R.id.txt_funtion);
        mTextMessage = findViewById(R.id.txt_message);
        mTSettings = findViewById(R.id.txt_setting);
        mFragment = (FrameLayout) findViewById(R.id.ly_content);
        mTextFuntion.setOnClickListener(this);
        mTextMessage.setOnClickListener(this);
        mTSettings.setOnClickListener(this);
    }

    /**
     * 切换之前先隐藏之前的fragment
     * @param fragmentTransaction
     */
    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fFragment != null) fragmentTransaction.hide(fFragment);
        if(sFragment != null) fragmentTransaction.hide(sFragment);
        if(messageFragment != null) fragmentTransaction.hide(messageFragment);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            /**
             * 功能选项
             */
            case R.id.txt_funtion:
                setSelected();
                mTextFuntion.setSelected(true);
                if(fFragment == null){
                    fFragment = new funtionFragment();
                    fTransaction.add(R.id.ly_content,fFragment);
                }else {
                    fTransaction.show(fFragment);
                }
                mTextTopbar.setText(mTextFuntion.getText());
                break;
            /**
             * 信息选项
             */
            case  R.id.txt_message:
                setSelected();
                mTextMessage.setSelected(true);
                if(messageFragment == null){
                    messageFragment = new messageFragment();
                    fTransaction.add(R.id.ly_content,messageFragment);
                }else {
                    fTransaction.show(messageFragment);
                }
                mTextTopbar.setText(mTextMessage.getText());
                break;
            /**
             * 设置选项
             */
            case R.id.txt_setting:
                setSelected();
                mTSettings.setSelected(true);
                if(sFragment == null){
                    sFragment = new settingsFragment();
                    fTransaction.add(R.id.ly_content,sFragment);
                }else {
                    fTransaction.show(sFragment);
                }
                mTextTopbar.setText(mTSettings.getText());
                break;

        }
                 fTransaction.commit();
    }

    /**
     * 重置选项按钮
     */
    private void setSelected() {
        mTextFuntion.setSelected(false);
        mTextMessage.setSelected(false);
        mTSettings.setSelected(false);
    }


}

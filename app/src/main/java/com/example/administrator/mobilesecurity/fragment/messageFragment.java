package com.example.administrator.mobilesecurity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mobilesecurity.R;

public class messageFragment extends Fragment {
    public messageFragment(){

    }

    /**
     * 加载信息界面
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.messge_fragment, container ,false);
        //TextView txt_content = (TextView) mView.findViewById(R.id.txt_content);
        // txt_content.setText("功能");
        return mView;
    }
}

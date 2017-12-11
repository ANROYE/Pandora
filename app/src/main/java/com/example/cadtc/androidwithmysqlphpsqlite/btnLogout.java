package com.example.cadtc.androidwithmysqlphpsqlite;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class btnLogout extends Fragment {


    public btnLogout() {
        //必需的空公共構造函數
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //填滿此片段的佈局
        return inflater.inflate(R.layout.logout, container, false);
    }

}

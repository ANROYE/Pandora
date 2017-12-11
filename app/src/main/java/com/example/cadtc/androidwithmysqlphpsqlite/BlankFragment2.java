package com.example.cadtc.androidwithmysqlphpsqlite;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class BlankFragment2 extends Fragment {


    public BlankFragment2() {
        //必需的空公共構造函數
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //填滿此片段的佈局
        return inflater.inflate(R.layout.main2, container, false);
    }

}

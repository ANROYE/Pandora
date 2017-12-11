package com.example.cadtc.androidwithmysqlphpsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import library.UserFunctions;

public class DashboardActivity extends Activity {
    UserFunctions userFunctions;
    ImageButton btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //檢查數據庫中的登入狀態
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
            setContentView(R.layout.dashboard);
            btnLogout = (ImageButton) findViewById(R.id.btnLogout);


            btnLogout.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    userFunctions.logoutUser(getApplicationContext());
                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    //關閉登入畫面
                    finish();
                }
            });

        }else{
            //用戶未登入顯示登入畫面
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            //關閉登入畫面
            finish();
        }
    }
}
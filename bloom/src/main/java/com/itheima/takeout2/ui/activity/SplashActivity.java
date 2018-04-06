package com.itheima.takeout2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itheima.takeout2.MyApplication;
import com.itheima.takeout2.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final EditText  et_ip = (EditText) findViewById(R.id.et_ip);

        Button  btn_ok = (Button) findViewById(R.id.btn_ok);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = et_ip.getText().toString();
                if(ip != null && ip.length() > 0){
                    MyApplication.ip = ip;
                }else{
                    MyApplication.ip = "10.0.2.2";
                }
                Intent  intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

package com.lixm.tvdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.TextView;

public class SplashActivity extends Activity {
    private TextView mTime;
    private int i = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mTime = findViewById(R.id.text_time);
        new Thread() {
            @Override
            public void run() {
                while (i >= 0) {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i--;
                }
                super.run();
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mTime.setText(i+"S");
                    if (i==0){
                        startActivity(new Intent(SplashActivity.this,WebActivity.class));
                        finish();
                        overridePendingTransition(R.anim.next_in, R.anim.next_out);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
}

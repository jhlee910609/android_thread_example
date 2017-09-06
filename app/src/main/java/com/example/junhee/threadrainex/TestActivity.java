package com.example.junhee.threadrainex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LogThread().start();
            }
        });

        new LogThread().start();
    }

    private void print100T(String caller) {
        for (int i = 0; i < 100; i++) {
            Log.e("Thread test", "caller : " + caller + ", current count : " + i);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class LogThread extends Thread {

        @Override
        public void run() {
            count++;
            print100T("SubThread count : " + count);

        }
    }
}

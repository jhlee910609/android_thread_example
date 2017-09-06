package com.example.junhee.threadrainex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ThreadBasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_basic);


        /* 1. default thread 활용 */
        // 1.1. Thread 생성한다.
        Thread thread = new Thread(){

            @Override
            public void run() {

                Log.e("Thread Test", "Hello Thread!");

            }
        };

        // 1.2. thread.start(); 한다.
        thread.start();


        /* 2. Runnable 객체 활용 */
        // 2.1. Runnable 객체 생성한다.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Log.e("Thread Test", "Hello Runnable!");

            }
        };

        // 2.2. Runnable을 실행한다.
        new Thread(runnable).start();

        /* CustomThread 실행 */
        CustomThread customThread = new CustomThread();
        customThread.start();

        /* CustomRunnable 실행 */
        Thread customRunnableThread = new Thread(new CustomRunnable());
        customRunnableThread.start();
    }
}

/* CustomThread 작성 */
class CustomThread extends Thread {

    @Override
    public void run() {
        Log.e("Thread Test", "Hello CustomThread!");

    }
}

/* CustomRunnable 작성 */
class CustomRunnable implements Runnable {

    @Override
    public void run() {
        Log.e("Thread Test", "Hello Runnable!");
    }
}



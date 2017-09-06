package com.example.junhee.threadrainex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RainActivity extends AppCompatActivity {

    FrameLayout ground;
    Stage stage;
    int deviceWidth, deviceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain);
        ground = (FrameLayout) findViewById(R.id.stage);

        // device의 화면 크기 가져오는 객체
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels;
        
        stage = new Stage((Context) this);
        ground.addView(stage);

        findViewById(R.id.btnRun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runTask();
            }
        });

    }

    private void runTask(){
        Rain rain  = new Rain();
        rain.start();

        DrawCanvas drawCanvas = new DrawCanvas();
        drawCanvas.start();
    }

    class DrawCanvas extends Thread{
        @Override
        public void run() {
            while (true) {

                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stage.postInvalidate();

            }
        }
    }

    class Rain extends Thread {

        @Override
        public void run() {

            Random random = new Random();

            for(int i=0; i < 100; i++){
                RainDrop rainDrop = new RainDrop();
                rainDrop.radius = random.nextInt(25) + 5;
                rainDrop.x = random.nextInt(deviceWidth);
                rainDrop.y = 0f;
                rainDrop.speed = random.nextInt(10);

                Paint paint = new Paint();
                paint.setColor(Color.GREEN);
                rainDrop.paint = paint;

                stage.addRainDrop(rainDrop);

                rainDrop.start();
                try {
                    rainDrop.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class RainDrop extends Thread {

        Paint paint;

        float radius;
        float x;
        float y;
        int speed;
        boolean run = true;

        public RainDrop(){

        }

        @Override
        public void run() {
           int count = 0;
            while (y < deviceHeight){
                count++;
                y = count * speed;
                Log.e("RainDrop", "y : " + y);
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            run = false;
        }
    }

    class Stage extends View {

        Paint paint;
        List<RainDrop> rainDrops = new ArrayList<>();

        public Stage(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(RainDrop rainDrop : rainDrops){
                canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.radius, rainDrop.paint);
            }
        }

        public void addRainDrop(RainDrop rainDrop){
            this.rainDrops.add(rainDrop);

        }
    }
}

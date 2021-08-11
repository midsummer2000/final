package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View wallView = findViewById(R.id.rView);
        Button button = findViewById(R.id.click1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int[] img = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};
                        Random random = new Random();
                        int rNum = random.nextInt(img.length);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                wallView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img[rNum]));
                            }
                        });
                    }
                },3000,5000);
            }
        });
    }
}
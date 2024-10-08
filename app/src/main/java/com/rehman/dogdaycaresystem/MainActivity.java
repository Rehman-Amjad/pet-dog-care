package com.rehman.dogdaycaresystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rehman.dogdaycaresystem.Adapter.ImageSliderAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageSliderAdapter adapter;
    CardView foodtray_card,humTemp_card,ultra_card,history_card,realTime_card,remote_card
            ,sendText_card;
    ImageView logout_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout_image = findViewById(R.id.logout_image);
        sendText_card = findViewById(R.id.sendText_card);
        viewInit();
        sliderCode();


        logout_image.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        });

        sendText_card.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,SendTextActivity.class));
        });

        history_card.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,HistoryActivity.class));
        });

        foodtray_card.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,FoodTrayActivtiy.class));
        });

        humTemp_card.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,HumTempActivity.class));
        });

        ultra_card.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,UltraActivity.class));
        });

        realTime_card.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,RealTimeActivity.class));
        });

        remote_card.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,RemoteActivity.class));
        });

    }

    private void viewInit()
    {
        viewPager = findViewById(R.id.header_slider);
        foodtray_card = findViewById(R.id.foodtray_card);
        humTemp_card = findViewById(R.id.humTemp_card);
        ultra_card = findViewById(R.id.ultra_card);
        history_card = findViewById(R.id.history_card);
        realTime_card = findViewById(R.id.realTime_card);
        remote_card = findViewById(R.id.remote_card);
    }

    private void sliderCode()
    {
        adapter = new ImageSliderAdapter(this);
        viewPager.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable()
            {

                @Override
                public void run() {




                    if(viewPager.getCurrentItem() == 0)
                    {
                        viewPager.setCurrentItem(1);
                    }
                    else if(viewPager.getCurrentItem() == 1)
                    {
                        viewPager.setCurrentItem(2);
                    }

                    else if (viewPager.getCurrentItem() == 2)
                    {
                        viewPager.setCurrentItem(3);
                    }

                    else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }
}
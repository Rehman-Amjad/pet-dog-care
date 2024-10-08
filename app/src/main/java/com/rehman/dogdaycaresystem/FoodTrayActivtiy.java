package com.rehman.dogdaycaresystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodTrayActivtiy extends AppCompatActivity {

    ImageView back_image,image,fireImage;
    TextView message_text,tv_DateTime;
    String value,date;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tray_activtiy);

        back_image = findViewById(R.id.back_image);
        image = findViewById(R.id.image);
        message_text = findViewById(R.id.message_text);
        tv_DateTime = findViewById(R.id.tv_DateTime);
        fireImage = findViewById(R.id.fireImage);

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);

        image.setVisibility(View.GONE);

        back_image.setOnClickListener(v -> {
            getOnBackPressedDispatcher().onBackPressed();
        });

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("SmartData")
                .child("200");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    value  = snapshot.child("FireSensor").getValue(String.class);
                    date  = snapshot.child("DateTime").getValue(String.class);
                    tv_DateTime.setText(date);

                    int checkValue = Integer.parseInt(value);

                    assert value != null;
                    if (checkValue == 0)
                    {
                        image.setVisibility(View.VISIBLE);
                        fireImage.setVisibility(View.GONE);
                        message_text.setText("Fire is Off");
                        progressDialog.dismiss();
                    }else if (checkValue == 1)
                    {
                        image.setVisibility(View.VISIBLE);
                        fireImage.setVisibility(View.VISIBLE);
                        message_text.setText("Fire is On");
                        Glide.with(FoodTrayActivtiy.this)
                                        .load(R.drawable.flame)
                                                .into(fireImage);
                        progressDialog.dismiss();
                    }
                    progressDialog.dismiss();

                }else{
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
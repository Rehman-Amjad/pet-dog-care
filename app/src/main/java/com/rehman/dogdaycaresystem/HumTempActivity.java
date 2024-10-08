package com.rehman.dogdaycaresystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HumTempActivity extends AppCompatActivity {

    ImageView back_image,image;
    TextView message_text;
    String value,temp,hum,date;
    ProgressDialog progressDialog;
    TextView tv_temp,tv_humidity,tv_DateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hum_temp);

        back_image = findViewById(R.id.back_image);
        message_text = findViewById(R.id.message_text);
        tv_temp = findViewById(R.id.tv_temp);
        tv_humidity = findViewById(R.id.tv_humidity);
        tv_DateTime = findViewById(R.id.tv_DateTime);
        image = findViewById(R.id.image);

        image.setVisibility(View.GONE);

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);


        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SmartData")
                .child("200");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    image.setVisibility(View.VISIBLE);
                    temp  = snapshot.child("TemperSensor").getValue(String.class);
                    hum  = snapshot.child("HumSensor").getValue(String.class);
                    date  = snapshot.child("DateTime").getValue(String.class);

                    tv_temp.setText("Temperature: " + temp + " C");
                    tv_humidity.setText("Humidity: " + hum + " %");
                    tv_DateTime.setText("Date & Time: " + date);
                    progressDialog.dismiss();


                }else{
                    image.setVisibility(View.GONE);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
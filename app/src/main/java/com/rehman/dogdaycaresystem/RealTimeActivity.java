package com.rehman.dogdaycaresystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

public class RealTimeActivity extends AppCompatActivity {

    TextView tv_DateTime;
    ImageView back_image,image;
    String date,imageLink;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time);

        tv_DateTime = findViewById(R.id.tv_DateTime);
        back_image = findViewById(R.id.back_image);
        image = findViewById(R.id.image);

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SmartData")
                .child("200");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    imageLink  = snapshot.child("img").getValue(String.class);
                    date  = snapshot.child("DateTime").getValue(String.class);

                    tv_DateTime.setText("Date & Time: " + date);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] imageBytes = baos.toByteArray();
                    imageBytes = Base64.decode(imageLink, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                    image.setImageBitmap(decodedImage);
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
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

public class UltraActivity extends AppCompatActivity {

    ImageView back_image,image;
    TextView message_text,tv_DateTime;
    String value,buttonValue,date;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra);

        back_image = findViewById(R.id.back_image);
        message_text = findViewById(R.id.message_text);
        tv_DateTime = findViewById(R.id.tv_DateTime);
        image = findViewById(R.id.image);

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);

        image.setVisibility(View.GONE);
        tv_DateTime.setVisibility(View.GONE);

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tray");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    value  = snapshot.child("Motorinfo").getValue(String.class);
                    buttonValue  = snapshot.child("manuallybutton").getValue(String.class);


                    assert value != null;
                    assert buttonValue != null;
                    if (value.equals("1") || buttonValue.equals("1"))
                    {
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.close_tray);
                        message_text.setText("Pet Daycare Tray is Open");
                        progressDialog.dismiss();
                    }else if (value.equals("0"))
                    {
                        image.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.open_tray);
                        message_text.setText("Pet Daycare Tray is Close");
                        progressDialog.dismiss();
                    }
                    else
                    {
                        message_text.setText("error to show message");
                        progressDialog.dismiss();
                    }

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
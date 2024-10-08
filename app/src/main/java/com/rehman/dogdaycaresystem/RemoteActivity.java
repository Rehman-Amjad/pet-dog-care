package com.rehman.dogdaycaresystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RemoteActivity extends AppCompatActivity {

    Button btn_open,btn_close;
    ProgressDialog progressDialog;
    String value,buttonValue;
    ImageView back_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        btn_open = findViewById(R.id.btn_open);
        btn_close = findViewById(R.id.btn_close);
        back_image = findViewById(R.id.back_image);

        btn_open.setEnabled(false);
        btn_close.setEnabled(false);

        fetchInfo();

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        progressDialog = ProgressDialog.show(this, "", "Please wait", true);



        btn_open.setOnClickListener(v -> {

            if(value.equals("0")){
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Tray");
                reference1.child("manuallybutton").setValue("1");
                finish();
                startActivity(new Intent(RemoteActivity.this,RemoteActivity.class));
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                fetchInfo();
            }else{
                Toast.makeText(this, "Tray Already Open", Toast.LENGTH_SHORT).show();
            }


        });

        btn_close.setOnClickListener(v -> {
            if(value.equals("0")){
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Tray");
                reference1.child("manuallybutton").setValue("0");
                finish();
                startActivity(new Intent(RemoteActivity.this,RemoteActivity.class));
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                fetchInfo();
            }

        });
    }

    private void fetchInfo() {
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
                    if(value.equals("1")){
                        btn_close.setEnabled(false);
                        btn_open.setEnabled(false);

                    }else{
                        btn_close.setEnabled(true);
                        btn_open.setEnabled(true);
                    }
//                    if (value.equals("0"))
//                    {
//                        if(buttonValue.equals("0")){
//                            btn_close.setEnabled(false);
//                            btn_open.setEnabled(true);
//                        }else{
//                            Toast.makeText(RemoteActivity.this, "Tray Already Open", Toast.LENGTH_SHORT).show();
//                        }
//
//                        // Create a Map for the update
//                    }else if (value.equals("1"))
//                    {
////                        btn_close.setEnabled(true);
////                        btn_open.setEnabled(false);
//                    }
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
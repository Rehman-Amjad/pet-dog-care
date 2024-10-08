package com.rehman.dogdaycaresystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SendTextActivity extends AppCompatActivity {

    EditText ed_message;
    Button btn_send,btn_clear;
    String mesasge;
    ImageView back_image;
    TextView tv_text;
    ProgressDialog progressDialog;

    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text);

        ed_message = findViewById(R.id.ed_message);
        btn_send = findViewById(R.id.btn_send);
        btn_clear = findViewById(R.id.btn_clear);
        back_image = findViewById(R.id.back_image);
        tv_text = findViewById(R.id.tv_text);


        fetchText();

        back_image.setOnClickListener(v -> {
            onBackPressed();
        });

        btn_send.setOnClickListener(v -> {
            mesasge = ed_message.getText().toString().trim();

            if (mesasge.isEmpty())
            {
                ed_message.setError("Enter Text here");
                ed_message.requestFocus();
                return;
            }else
            {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tray");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            reference.child("MySpeech").setValue(mesasge);
                            tv_text.setText(mesasge);
                            Toast.makeText(SendTextActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btn_clear.setOnClickListener(v -> {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tray");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists())
                    {
                        reference.child("MySpeech").setValue(" ");
                        ed_message.setText("");
                        Toast.makeText(SendTextActivity.this, "clear", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });


    }

    private void fetchText() {
        progressDialog = ProgressDialog.show(this, "", "Please wait", true);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tray");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    text  = snapshot.child("MySpeech").getValue(String.class);

                    assert text != null;
                    tv_text.setText(text);

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
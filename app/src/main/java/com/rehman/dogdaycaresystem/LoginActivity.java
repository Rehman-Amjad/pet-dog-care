package com.rehman.dogdaycaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin,btnExit;
    EditText etUserName,etUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin=findViewById(R.id.btnLogin);
        etUserName=findViewById(R.id.etUserName);
        etUserPassword=findViewById(R.id.etUserPassword);
        btnExit=findViewById(R.id.btnExit);

        String username="admin";
        String userpassword="asdf@1234";


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.equals(etUserName.getText().toString()))
                {
                    if (userpassword.equals(etUserPassword.getText().toString()))
                    {
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        etUserPassword.setError("incorrect Password");
                        etUserPassword.requestFocus();
                        etUserPassword.setText("");
                    }
                }
                else
                {
                    etUserName.setError("incorrect username");
                    etUserName.requestFocus();
                    etUserName.setText("");
                }

            }
        });
    }
}
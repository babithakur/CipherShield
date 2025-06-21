package com.example.ciphershield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SetupPin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_pin);
        EditText txtPin = findViewById(R.id.userPin);
        Button setPin = findViewById(R.id.btnSetPin);
        setPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredPin = txtPin.getText().toString();
                if(enteredPin.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter PIN", Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("user_pin", enteredPin);
                    editor.apply();
                    Intent i = new Intent(SetupPin.this, AuthenticateUser.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}
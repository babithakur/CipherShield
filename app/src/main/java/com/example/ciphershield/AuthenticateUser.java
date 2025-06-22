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

public class AuthenticateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate_user);
        EditText txtPin = findViewById(R.id.userPin);
        Button btn = findViewById(R.id.btnSubmitPin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE);
                String savedPin = prefs.getString("user_pin", null);
                String enteredPin = txtPin.getText().toString();
                if(enteredPin.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter PIN", Toast.LENGTH_LONG).show();
                }else{
                    if(enteredPin.equals(savedPin)) {
                        Intent i = new Intent(AuthenticateUser.this, MainActivity.class);
                        i.putExtra("isAuthenticated", true);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Incorrect PIN. Please try again!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
package com.example.ciphershield;

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

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        EditText oldPin = findViewById(R.id.userPin);
        EditText newPin = findViewById(R.id.newPin);
        SharedPreferences prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE);
        String savedPin = prefs.getString("user_pin", null);
        Button changePin = findViewById(R.id.btnChangePin);
        Button btnCancel = findViewById(R.id.btnCancel);
        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldPin.getText().toString().isEmpty() || newPin.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your PIN.", Toast.LENGTH_LONG).show();
                }else {
                    if (oldPin.getText().toString().equals(savedPin)) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("user_pin", newPin.getText().toString());
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Your PIN was changed!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Old pin was incorrect!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
}
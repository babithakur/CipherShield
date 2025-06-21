package com.example.ciphershield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE);
        String savedPin = prefs.getString("user_pin", null);

        if (savedPin == null) {
            // First time launch: redirect to PIN setup
            Intent intent = new Intent(this, SetupPin.class);
            startActivity(intent);
            finish();
        }else {
            Intent i = getIntent();
            boolean authenticated = i.getBooleanExtra("isAuthenticated", false);
            if(!authenticated) {
                Intent intent = new Intent(this, AuthenticateUser.class);
                startActivity(intent);
                finish();
            }
        }


    }
}
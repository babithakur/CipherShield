package com.example.ciphershield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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
            String[] menuItems = {"Add new password", "View passwords", "Settings", "About app"};
            int[] images = {R.drawable.add, R.drawable.view, R.drawable.settings, R.drawable.info};
            GridView gridView = findViewById(R.id.menuView);
            GridViewAdapter adapter = new GridViewAdapter(this, menuItems, images);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String option = adapter.menuNames[position];
                    if(option.equals("Add new password")){
                        Intent i = new Intent(MainActivity.this, AddPassword.class);
                        startActivity(i);
                    }else if(option.equals("View passwords")){
                        Intent i = new Intent(MainActivity.this, ViewPasswords.class);
                        startActivity(i);
                    }
                }
            });
        }
    }
}
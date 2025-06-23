package com.example.ciphershield;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewPasswords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_passwords);
        DbHelper helper = new DbHelper(this);
        Cursor cursor = helper.viewPasswords();
        ArrayList<String> appNames = new ArrayList<>();
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();
        AESEncryption encryption = new AESEncryption();
        SharedPreferences prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE);
        String savedPin = prefs.getString("user_pin", null);
        while(cursor.moveToNext()){
            appNames.add(cursor.getString(1));
            usernames.add(cursor.getString(2));
            try{
                String passwd = cursor.getString(3);
                String dec_passwd = encryption.decrypt(passwd, savedPin);
                passwords.add(dec_passwd);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        ArrayListAdapter adapter = new ArrayListAdapter(this, appNames, usernames, passwords);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
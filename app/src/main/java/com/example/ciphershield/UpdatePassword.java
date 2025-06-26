package com.example.ciphershield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdatePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        Intent i = getIntent();
        String appName = i.getStringExtra("appName");
        String username = i.getStringExtra("username");
        String password = i.getStringExtra("password");
        EditText app_name = findViewById(R.id.applicationName);
        EditText uname = findViewById(R.id.applicationUsername);
        EditText passwd = findViewById(R.id.applicationPassword);
        app_name.setText(appName);
        uname.setText(username);
        passwd.setText(password);
        Button btnEdit = findViewById(R.id.btnUpdate);
        Button btnCancel = findViewById(R.id.btnCancel);
        DbHelper helper = new DbHelper(this);
        AESEncryption encryption = new AESEncryption();
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enc_passwd = "";
                String newAppName = app_name.getText().toString();
                String newUsername = uname.getText().toString();
                String newPassword = passwd.getText().toString();
                if (newAppName.isEmpty() || newUsername.isEmpty() || newPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter proper details.", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        enc_passwd = encryption.encrypt(newPassword, "1c09a2ef52d471d75474a0236e05164d");
                        int id = helper.getPasswordId(appName, username);
                        boolean result = helper.updatePassword(newAppName, newUsername, enc_passwd, id);
                        if (result) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePassword.this);
                            builder.setTitle("CipherShield");
                            builder.setMessage("Your password was updated!");
                            builder.setPositiveButton("OK", (dialog, which) -> {
                                dialog.dismiss();
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
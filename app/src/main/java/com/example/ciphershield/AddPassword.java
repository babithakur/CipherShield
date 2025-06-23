package com.example.ciphershield;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnCancel = findViewById(R.id.btnCancel);
        EditText appName = findViewById(R.id.applicationName);
        EditText username = findViewById(R.id.applicationUsername);
        EditText password = findViewById(R.id.applicationPassword);
        DbHelper helper = new DbHelper(this);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String app_name = appName.getText().toString();
                String uname = username.getText().toString();
                String passwd = password.getText().toString();
                AESEncryption encryption = new AESEncryption();
                String enc_passwd = "";
                try {
                    SharedPreferences prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE);
                    String savedPin = prefs.getString("user_pin", null);
                    enc_passwd = encryption.encrypt(passwd, "1234");
                    helper.addPassword(app_name, uname, enc_passwd);
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddPassword.this);
                    builder.setTitle("CipherShield");
                    builder.setMessage("Your password was added!");
                    builder.setPositiveButton("OK", (dialog, which) -> {
                        dialog.dismiss();
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
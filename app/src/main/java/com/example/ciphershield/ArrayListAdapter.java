package com.example.ciphershield;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArrayListAdapter extends ArrayAdapter<String> {
    ArrayList<String> appNames = new ArrayList<>();
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> passwords = new ArrayList<>();
    Activity context;
    public ArrayListAdapter(Activity context, ArrayList<String> appNames,ArrayList<String> usernames,ArrayList<String> passwords){
        super(context, R.layout.passwords_list, appNames);
        this.appNames = appNames;
        this.usernames = usernames;
        this.passwords = passwords;
        this.context = context;
    }
    public View getView(int position, View v, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.passwords_list, null, true);
        TextView app_name = view.findViewById(R.id.appValue);
        TextView user_name = view.findViewById(R.id.userValue);
        TextView password = view.findViewById(R.id.passValue);
        Button deleteBtn = view.findViewById(R.id.btnDelete);
        Button updateBtn = view.findViewById(R.id.btnEdit);
        DbHelper helper = new DbHelper(context);
        app_name.setText(appNames.get(position));
        user_name.setText(usernames.get(position));
        password.setText(passwords.get(position));
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = helper.getPasswordId(appNames.get(position), usernames.get(position));
                Log.d("DeleteDebug", "ID fetched: " + id);
                boolean deleted = helper.deletePassword(String.valueOf(id));
                if (deleted) {
                    appNames.remove(position);
                    usernames.remove(position);
                    passwords.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdatePassword.class);
                i.putExtra("appName", appNames.get(position));
                i.putExtra("username", usernames.get(position));
                i.putExtra("password", passwords.get(position));
                context.startActivity(i);
            }
        });
        return view;
    }
}

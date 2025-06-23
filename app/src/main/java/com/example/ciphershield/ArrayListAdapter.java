package com.example.ciphershield;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        app_name.setText(appNames.get(position));
        user_name.setText(usernames.get(position));
        password.setText(passwords.get(position));
        return view;
    }
}

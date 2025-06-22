package com.example.ciphershield;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends ArrayAdapter<String> {
    String[] menuNames;
    int[] images;
    Activity context;
    public GridViewAdapter(Activity context, String[] menuNames, int[] images){
        super(context, R.layout.gridview_items, menuNames);
        this.context = context;
        this.menuNames = menuNames;
        this.images = images;
    }
    public View getView(int position, View v, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.gridview_items, null, true);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        ImageView image = (ImageView) view.findViewById(R.id.iconImage);
        txtName.setText(menuNames[position]);
        image.setImageResource(images[position]);
        return view;
    }
}

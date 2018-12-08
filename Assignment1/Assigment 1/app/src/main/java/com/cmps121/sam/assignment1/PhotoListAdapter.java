package com.cmps121.sam.assignment1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends ArrayAdapter<Photo> {
    private static final String TAG = "PhotoListAdapter";
    private Context mContext;
    int mResourse;

    public PhotoListAdapter(Context context, int resource, ArrayList<Photo> objects){
        super(context, resource, objects);      //initalizes photoListAdapter
        mContext = context;
        mResourse = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String nameS = getItem(position).getName();
        String yearS = getItem(position).getYear();
        String photographerS = getItem(position).getPhotographer();

        Photo temp = new Photo(nameS, yearS, photographerS);        //creates temp photo object

        LayoutInflater myInflator = LayoutInflater.from(mContext);
        convertView = myInflator.inflate(mResourse, parent, false);

        TextView textViewName = (TextView) convertView.findViewById(R.id.nameVV);       //defines textViews
        TextView textViewYear = (TextView) convertView.findViewById(R.id.yearVV);
        TextView textViewPhotographer = (TextView) convertView.findViewById(R.id.photographerVV);

        textViewName.setText(nameS);
        textViewYear.setText(yearS);                    //sets text of textviews pulling from photo objects
        textViewPhotographer.setText(photographerS);

        return convertView;
    }
}

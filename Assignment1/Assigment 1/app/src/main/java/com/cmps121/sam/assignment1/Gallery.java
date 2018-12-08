package com.cmps121.sam.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Gallery extends AppCompatActivity {

    public static final String DEFAULT = "N/A";

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Button backBtn = (Button) findViewById(R.id.backBtn);

        mListView = (ListView) findViewById(R.id.myListView);

        backBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                onBackPressed();
            }
        });
    }

    public void load(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String myList = sharedPreferences.getString("myList", DEFAULT);


        myList = myList.replace("[","");
        myList = myList.replace("]","");
        myList = myList.replace(",","");
        String[] myListSplit = myList.split("\\s+");        //removes extra characters created by string array

        ArrayList<Photo> myPhotoList = new ArrayList<>();

        int size = myListSplit.length-1;
        while(size > 0){
            String photographer = myListSplit[size];            //takes the string array and converts all data to photo objects
            String year = myListSplit[size-1];
            String name = myListSplit[size-2];
            Photo temp = new Photo(name, year, photographer);
            myPhotoList.add(temp);                              //stores newly made photo objects into Array list of objects
            size = size - 3;
        }
        if(myList.equals(DEFAULT)){
            Toast.makeText(this,"No Data was Found", Toast.LENGTH_LONG).show();     //pop up if no data is stored
        }else{
            Toast.makeText(this, "Data Loaded Successfully", Toast.LENGTH_LONG).show();

            PhotoListAdapter adapter = new PhotoListAdapter(this, R.layout.adapterviewlayout, myPhotoList);
            mListView.setAdapter(adapter);
        }
    }
}

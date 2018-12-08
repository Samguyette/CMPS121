package com.cmps121.sam.assignment2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Range extends AppCompatActivity {

    EditText start;
    EditText end;
    Button find;
    String startString;
    String endString;
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);
        start = findViewById(R.id.startEditText);
        end = findViewById(R.id.endEditText);
        find = (Button) findViewById(R.id.findBtn);

        mListView = (ListView) findViewById(R.id.rangeListView);
        mDatabaseHelper = new DatabaseHelper(this);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startString = start.getText().toString();
                int startInt = Integer.parseInt(startString);
                endString = end.getText().toString();
                int endInt = Integer.parseInt(endString);
                populateListView(startInt, endInt);
            }
        });
    }
    private void populateListView(int start, int end){
        Cursor data = mDatabaseHelper.getData();
        ArrayList<PhotoObject> listData = new ArrayList<>();
        boolean isValid = false;

        while(data.moveToNext()){
            String id = data.getString(0);
            int idInt = Integer.parseInt(id);
            String title = data.getString(1);
            byte[] photo = data.getBlob(2);
            String url = data.getString(3);


            if(idInt >= start && idInt <= end){
                isValid = true;
                PhotoObject po = new PhotoObject(photo, title, id, url);
                listData.add(po);
            }
        }
        if(!isValid){
            Toast.makeText(this, "This range is invalid.", Toast.LENGTH_SHORT).show();
        }

        PhotoListAdapter adapterTwo = new PhotoListAdapter(this, R.layout.adapter_view_layout, listData);
        mListView.setAdapter(adapterTwo);
    }
}

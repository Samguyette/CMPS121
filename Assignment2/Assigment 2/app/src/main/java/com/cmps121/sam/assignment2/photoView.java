package com.cmps121.sam.assignment2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class photoView extends AppCompatActivity {

    private static String TAG = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        mListView = (ListView) findViewById(R.id.listLayout);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView(){
        Cursor data = mDatabaseHelper.getData();
        ArrayList<PhotoObject> listData = new ArrayList<>();
        while(data.moveToNext()){
            String id = data.getString(0);
            String title = data.getString(1);
            byte[] photo = data.getBlob(2);
            String url = data.getString(3);
            PhotoObject po = new PhotoObject(photo, title, id, url);
            listData.add(po);
        }
        PhotoListAdapter adapter = new PhotoListAdapter(this, R.layout.adapter_view_layout, listData);
        mListView.setAdapter(adapter);
    }
}

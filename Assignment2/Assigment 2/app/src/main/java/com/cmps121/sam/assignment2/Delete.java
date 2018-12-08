package com.cmps121.sam.assignment2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    EditText deletePhotoTitle;
    EditText deletePhotoID;
    Button deleteTitleBtn;
    Button deleteIDBtn;
    DatabaseHelper mDatabaseHelper;
    private static final String TAG = "DatabaseHelper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deletePhotoID = (EditText) findViewById(R.id.deleteIDEditText);
        deletePhotoTitle = (EditText) findViewById(R.id.deleteEditText);
        deleteTitleBtn = (Button) findViewById(R.id.titleDeleteBtn);
        deleteIDBtn = (Button) findViewById(R.id.idDeleteBtn);
        mDatabaseHelper = new DatabaseHelper(this);

        deleteTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteNum = deletePhotoTitle.getText().toString();
                deletePhotoByTitle(deleteNum);
                deletePhotoID.setText("");
                deletePhotoTitle.setText("");
            }
        });

        deleteIDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteID = deletePhotoID.getText().toString();
                int deleteIDInt = Integer.parseInt(deleteID);
                deletePhotoByID(deleteIDInt);
                deletePhotoID.setText("");
                deletePhotoTitle.setText("");
            }
        });

    }

    private void deletePhotoByTitle(String title) {
        Cursor data = mDatabaseHelper.getItemID(title);
        int itemID = -1;
        while(data.moveToNext()){
            itemID = data.getInt(0);                //works
        }

        if(itemID > -1){
            Log.d(TAG, "The ID is: "+ itemID);
        }else {
            Toast.makeText(this, "No photo associated with name.", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Photo Object was successfully deleted.", Toast.LENGTH_SHORT).show();
        mDatabaseHelper.delete(itemID, title);
    }

    private void deletePhotoByID(int id){
        Cursor data = mDatabaseHelper.getItemTitle(id);
        String name = "";
        while(data.moveToNext()){
            name = data.getString(0);                //works
        }
        if(name == ""){
            Toast.makeText(this, "No photo associated with id.", Toast.LENGTH_SHORT).show();
        }
        mDatabaseHelper.delete(id,name);

    }


}

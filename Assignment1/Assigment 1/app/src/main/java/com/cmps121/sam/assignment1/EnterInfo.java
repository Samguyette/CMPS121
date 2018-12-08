package com.cmps121.sam.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EnterInfo extends AppCompatActivity {

    EditText name;
    EditText photographer;
    Spinner yearSpinner;

    public static final String DEFAULT = "N/A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_info);
        name = (EditText) findViewById(R.id.nameEditText); //userName
        photographer = (EditText) findViewById(R.id.photographerEditText); //password



        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(EnterInfo.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.years));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);


    }

    public void save(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String myList = "";

        if (sharedPreferences.contains("myList")) {         //if list txt. file is already on device it copies it and adds the new data
            myList = sharedPreferences.getString("myList", DEFAULT);
        }
        myList = myList + " ";
        myList = myList + (name.getText().toString());
        myList = myList + " ";
        myList = myList + (photographer.getText().toString());          //adds new data to string
        myList = myList + " ";
        myList = myList + (yearSpinner.getSelectedItem().toString());


        editor.putString("myList", String.valueOf(myList));
        editor.commit();

        Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show();

        onBackPressed();
    }

}

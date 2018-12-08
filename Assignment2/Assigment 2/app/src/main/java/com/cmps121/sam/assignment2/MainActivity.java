package com.cmps121.sam.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declares main btns
        Button downloadBtn = (Button) findViewById(R.id.downloadBtn);
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        Button viewBtn = (Button) findViewById(R.id.viewBtn);
        Button rangeBtn = (Button) findViewById(R.id.rangeBtn);

        //on click listeners to move activities
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveDownload = new Intent(MainActivity.this, Download.class);
                startActivity(moveDownload);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveDelete = new Intent(MainActivity.this, Delete.class);
                startActivity(moveDelete);
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveView = new Intent(MainActivity.this, photoView.class);
                startActivity(moveView);
            }
        });
        rangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveRange = new Intent(MainActivity.this, Range.class);
                startActivity(moveRange);
            }
        });
    }
}

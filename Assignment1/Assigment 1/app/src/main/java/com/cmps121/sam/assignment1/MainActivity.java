package com.cmps121.sam.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declares buttons
        Button enterInfoBtn = (Button) findViewById(R.id.enterInfoBtn);
        Button viewBtn = (Button) findViewById(R.id.viewBtn);       //declares buttons
        Button exitBtn = (Button) findViewById(R.id.exitBtn);

        //launches activity for enterInfoBtn
        enterInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveEnterInfo = new Intent(MainActivity.this, EnterInfo.class);      //moves to enter info display
                startActivity(moveEnterInfo);
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveView = new Intent(MainActivity.this, Gallery.class);         //moves to Gallery display
                startActivity(moveView);

            }
        });

        //launches activity that quits application
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}

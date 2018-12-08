package com.cmps121.sam.assignment3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private Handler mHandler = new Handler();
    TextView infoTextView;
    Button clearBtn;
    Button exitBtn;
    boolean hasMoved = false;
    private BroadcastReceiver updateTextView;

    IntentFilter newIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        delayToast();

        infoTextView = findViewById(R.id.movedTextView);
        clearBtn = findViewById(R.id.clearBtn);
        exitBtn = findViewById(R.id.exitBtn);


        final Intent newIntent = new Intent(this, detector.class);
        newIntentFilter = new IntentFilter();
        newIntentFilter.addAction("ActionTextView");

        //infoTextView.setText("Everything was quiet.");

        mHandler.postDelayed(new Runnable() {
            public void run() {


            }
        }, 10000);



        startService(newIntent);

        updateTextView = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                hasMoved = true;
                infoTextView.setText("The phone moved!");
            }
        };
        registerReceiver(updateTextView, newIntentFilter);

        if(hasMoved){
            infoTextView.setText("The phone moved!");
        }

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newIntent.putExtra("SAM", 1);
                startService(newIntent);
                //hasMoved = false;
                infoTextView.setText("Everything was quiet.");
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }

        });



    }


    private void delayToast() {
        Toast.makeText(this, "Press CLEAR to start.", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Movement detection will start in 30 seconds.", Toast.LENGTH_LONG).show();
    }


//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Intent newIntent = new Intent(this, detector.class);
//        stopService(newIntent);
//    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(updateTextView, newIntentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(updateTextView);
    }
}

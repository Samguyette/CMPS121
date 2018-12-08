package com.cmps121.sam.assignment2;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Download extends AppCompatActivity {

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    EditText photoUrl;
    EditText photoTitle;
    Button storeBtn;
    DatabaseHelper mDatabaseHeler;
    String newUrl;
    String newTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        photoUrl = (EditText) findViewById(R.id.urlEditText);
        photoTitle = (EditText) findViewById(R.id.photoTitleEditText);
        storeBtn = (Button) findViewById(R.id.storeBtn);
        mDatabaseHeler = new DatabaseHelper(this);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT);
        }

        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTitle = photoTitle.getText().toString();
                newUrl = photoUrl.getText().toString();
                if (photoTitle.length() > 0) {
                    AddData();
                    photoTitle.setText("");
                    photoUrl.setText("");
                    finish();
                } else {
                    //Toast.makeText(this,"Enter text before storing.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void AddData() {
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(newUrl, newTitle);
    }


    class DownloadTask extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Download.this);
            progressDialog.setTitle("Downloading...");
            progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String path = params[0];

            try {
                URL realUrl = new URL(path);
                URLConnection urlConnection = realUrl.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                ByteArrayOutputStream buff = new ByteArrayOutputStream();

                int current = 0;
                byte[] data = new byte[1024];
                while ((current = bufferedInputStream.read(data, 0, data.length)) != -1) {
                    buff.write(data, 0, current);
                }

                mDatabaseHeler.addData(newTitle, data, newUrl);      //should be realUrl


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Not a valid URL (1)";
            } catch (IOException e) {
                e.printStackTrace();
                return "Not a valid URL (2)";

            }
            return "Download complete.";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.hide();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

        }


    }
}
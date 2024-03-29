package com.example.harshal.gmailsender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends Activity {

    Button button;

    GMailSender sender;



    @SuppressLint("NewApi")

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.mybtn);

        // Add your mail Id and Password

        sender = new GMailSender("my mail", "my password");



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.

                Builder().permitAll().build();



        StrictMode.setThreadPolicy(policy);

        button.setOnClickListener(new OnClickListener() {



            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub



                try {

                    new MyAsyncClass().execute();



                } catch (Exception ex) {

                    Toast.makeText(getApplicationContext(), ex.toString(), 100).show();

                }



            }

        });



    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it          //     is present.

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }



    class MyAsyncClass extends AsyncTask<Void, Void, Void> {



        ProgressDialog pDialog;



        @Override

        protected void onPreExecute() {

            super.onPreExecute();



            pDialog = new ProgressDialog(MainActivity.this);

            pDialog.setMessage("Please wait...");

            pDialog.show();



        }



        @Override

        protected Void doInBackground(Void... mApi) {

            try {

                // Add subject, Body, your mail Id, and receiver mail Id.

                sender.sendMail("Subject", " body", "from Mail", "to mail");





            }



            catch (Exception ex) {



            }

            return null;

        }



        @Override

        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            pDialog.cancel();

            Toast.makeText(getApplicationContext(), "Email send", 100).show();

        }

    }

}
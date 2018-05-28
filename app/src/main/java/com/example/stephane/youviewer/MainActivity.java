package com.example.stephane.youviewer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int SPLASH_TIME_OUT = 4000;
    private DatePickerDialog dpd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Button button11 = (Button) findViewById(R.id.button11);
        TextView tv_textView = (TextView) findViewById(R.id.textView);
        String now = DateUtils.formatDateTime(getApplicationContext(), (new Date()).getTime(), DateFormat.FULL);
        tv_textView.setText(now);

        // DatePickerDialog
        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                TextView tv_textView = (TextView) findViewById(R.id.textView);
                tv_textView.setText(i2 + "/" + i1 +"/"+ i);
            }
        };
        dpd= new DatePickerDialog(this,odsl,2018,10,5);*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }

        },SPLASH_TIME_OUT);
    }

    public void clickButton11(View view) {

        //Toast
        Toast.makeText(getApplicationContext(),getString(R.string.msg),Toast.LENGTH_LONG).show();

        // DatePickerDialog
        dpd.show();
    }



    public void clickButton1(View view){
        // Intent
        Intent i = new Intent(this,SecondActivity.class);
        startActivity(i);
    }



    public void clickButton2(View view){
        // Intent
        Intent i = new Intent(this,SecondActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        //myTextView.setText("Bienvenue sur Youviewer");
    }
}


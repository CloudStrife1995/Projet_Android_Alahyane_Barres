package com.example.stephane.youviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by stephane on 21/05/2018.
 */

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void clickButton2(View view) {
        // Intent
        Intent j = new Intent(this, MainActivity.class);
        startActivity(j);
    }
}
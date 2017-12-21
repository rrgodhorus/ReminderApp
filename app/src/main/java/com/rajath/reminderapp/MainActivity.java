package com.rajath.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

     android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //Its working right?
    }

    public void fun1(){
        int i=1;
        String test = "test";
    }


}

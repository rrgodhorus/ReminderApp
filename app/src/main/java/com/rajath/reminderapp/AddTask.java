package com.rajath.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

public class AddTask extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        toolbar = findViewById(R.id.toolbar_new_task);
        toolbar.setTitle("New Task");
        toolbar.setNavigationIcon(R.drawable.ic_nav_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddTask.this,"Nav back",Toast.LENGTH_SHORT).show();
                finish();

            }
        });



    }
}

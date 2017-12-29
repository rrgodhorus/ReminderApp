package com.rajath.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

     Toolbar toolbar;
     ImageButton add;
     RecyclerView recview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_toolbar);
        add = findViewById(R.id.btn_add);
        toolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(toolbar);

        recview = findViewById(R.id.rv);

        Tasks[] tasks = new Tasks[50];


        for(int i=0;i<50;++i){
            tasks[i] = new Tasks("Sample "+i);
        }

        Adapter adapter = new Adapter(tasks);
        recview.setAdapter(adapter);
        recview.setLayoutManager(new LinearLayoutManager(this));





    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.search) {
                Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
                return true;
            }

            return super.onOptionsItemSelected(item);


        }





}

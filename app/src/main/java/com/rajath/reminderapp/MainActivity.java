package com.rajath.reminderapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

     Toolbar toolbar;
     ImageButton add;
     RecyclerView recview;
     DatabaseHelper myDb;




    ArrayList<Tasks> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_toolbar);
        add = findViewById(R.id.btn_add);
        toolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(toolbar);
        recview = findViewById(R.id.rv);
        myDb = new DatabaseHelper(this);

        getAll(tasks);


       /*for(int i=0;i<50;++i){
            //tasks[i] = new Tasks("Sample ",i);
            tasks.add(new Tasks("Sample"));
        }
         */
        final Adapter adapter = new Adapter(tasks.toArray(new Tasks[tasks.size()]),getApplicationContext());
       // Adapter adapter = new Adapter(null);
        recview.setAdapter(adapter);
        recview.setLayoutManager(new LinearLayoutManager(this));
        recview.setHasFixedSize(true);



         add.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,NewTask.class);
                 //intent.putExtra("tasks",tasks.toArray(new Tasks[tasks.size()]));
                 intent.putExtra("edit","false");
                 startActivity(intent);
                 finish();
                 //adapter.notifyDataSetChanged();
             }
         });




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

        public void getAll(ArrayList<Tasks> tasks){
            Cursor res = myDb.getAllData();
            if(res.getCount() != 0){
                while(res.moveToNext()){
                    int id = Integer.parseInt(res.getString(res.getColumnIndexOrThrow("ID")));
                    String taskdesc = res.getString(res.getColumnIndexOrThrow("TaskDesc"));
                    Boolean ischecked = Boolean.parseBoolean(res.getString(res.getColumnIndexOrThrow("IsChecked")));
                    String date = res.getString(res.getColumnIndexOrThrow("DATE"));
                    String time = res.getString(res.getColumnIndexOrThrow("TIME"));
                    tasks.add(new Tasks(id,taskdesc,ischecked,date,time));
                   // adapter.notifyDataSetChanged();

                }
            }
        }






}

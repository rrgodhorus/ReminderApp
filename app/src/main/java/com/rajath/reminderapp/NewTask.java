package com.rajath.reminderapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTask extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;
    EditText edt1;
    EditText edt2;
    EditText edt3;
    Calendar c = Calendar.getInstance();
    int Year = c.get(Calendar.YEAR);
    int Month = c.get(Calendar.MONTH);
    int Day = c.get(Calendar.DAY_OF_MONTH);
    DatePickerDialog dpd;
    DatabaseHelper myDb;
    boolean temp;
    //Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        myDb = new DatabaseHelper(this);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        toolbar = findViewById(R.id.toolbar_new_task);
        toolbar.setTitle("New Task");

        if(getIntent().getExtras().getString("edit").equals("true")){
            toolbar.setTitle("Edit Task");
            renderEdit();

        }
        toolbar.setNavigationIcon(R.drawable.ic_nav_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewTask.this,"Nav back",Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });

        setDatePickerDialog();
        setTimePickerDialog();




    }

    public void setDatePickerDialog(){

        edt2.setKeyListener(null);
        edt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd = new DatePickerDialog(NewTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String dayOfWeek = new SimpleDateFormat("EEEE").format(new Date(year,month,dayOfMonth-1));
                        String month_txt = new SimpleDateFormat("MMM").format(new Date(year,month,dayOfMonth-1));
                        //edt2.setText(dayOfMonth + "-" + (month+1) + "-" + year);
                        edt2.setText(dayOfWeek + ", " + dayOfMonth + "-" + month_txt + "-" + year);
                    }
                },Year,Month,Day);
                dpd.setTitle("Select Date");
                dpd.show();
            }
        });

    }

    public void setTimePickerDialog(){

        edt3.setKeyListener(null);
        edt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int Hour = c.get(Calendar.HOUR_OF_DAY);
                int Minute = c.get(Calendar.MINUTE);
                final TimePickerDialog tpd = new TimePickerDialog(NewTask.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String am_pm = "AM";
                        int hour = hourOfDay;
                        if (hourOfDay < 12) {
                            if(hourOfDay == 0) {
                                if(minute == 0)
                                    am_pm = "Midnight";
                                hour = 12;
                            }

                        }
                        else if (hourOfDay == 12){
                            if(minute == 0)
                                am_pm = "Noon";
                            else am_pm = "PM";
                            hour = 12;
                        }

                        else {
                            am_pm = "PM";
                            hour = hourOfDay - 12;
                        }
                        edt3.setText(new DecimalFormat("00").format(hour) + ":" + new DecimalFormat("00").format(minute) + " " + am_pm);
                    }

                },Hour,Minute,false);
                tpd.setTitle("Select Time");
                tpd.show();
            }
        });
    }

    public void addData(){

        if(edt1.getText().toString().length() ==0 ){
            showMessage("Error!!","Please enter the task first");
            return;
        }

        if(edt3.getText().toString().length()!=0 && edt2.getText().toString().length() == 0){
            showMessage("Error!!","Please specify date before setting time");
            return;
        }

        boolean isInserted = myDb.insertData(edt1.getText().toString(),edt2.getText().toString(),edt3.getText().toString(),"false");
        if(isInserted){
            Toast.makeText(NewTask.this,"Data inserted",Toast.LENGTH_LONG).show();
            onBackPressed();
        }
        else Toast.makeText(NewTask.this,"Data NOT inserted!!",Toast.LENGTH_LONG).show();

    }

    public void renderEdit(){
        int id = getIntent().getExtras().getInt("id");
        Cursor res = myDb.getDataById(id);
        if(res.getCount() == 0){
            Toast.makeText(NewTask.this,"FATAL ERROR : Data not Retrieved",Toast.LENGTH_SHORT).show();
        }
        else {
               if(res.moveToNext()) {
                   int idt = Integer.parseInt(res.getString(res.getColumnIndexOrThrow("ID")));
                   String taskdesc = res.getString(res.getColumnIndexOrThrow("TaskDesc"));
                   Boolean ischecked = Boolean.parseBoolean(res.getString(res.getColumnIndexOrThrow("IsChecked")));
                   temp = ischecked;
                   String date = res.getString(res.getColumnIndexOrThrow("DATE"));
                   String time = res.getString(res.getColumnIndexOrThrow("TIME"));
                   edt1.setText(taskdesc);
                   edt2.setText(date);
                   edt3.setText(time);
               }
        }
        res.close();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_new_task,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.Save){
            if (getIntent().getExtras().getString("edit").equals("true")) {
                int idt = getIntent().getExtras().getInt("id");
                String taskdesc = edt1.getText().toString();
                Boolean ischecked = temp;
                String date = edt2.getText().toString();
                String time = edt3.getText().toString();
                boolean isUpdated = myDb.updateData(idt + "", taskdesc, date, time, ischecked + "");
                if (!isUpdated)
                    Toast.makeText(NewTask.this, "FATAL ERROR : Data not updated", Toast.LENGTH_SHORT).show();
                onBackPressed();


            }
             else addData();

            return true;
        }
        return super.onOptionsItemSelected(item);

    }



    @Override
    public void onBackPressed() {
        //Toast.makeText(getApplicationContext(),"Going Back",Toast.LENGTH_SHORT).show();
        //super.onBackPressed();
        startActivity(new Intent(NewTask.this,MainActivity.class));
        finish();
        //Toast.makeText(getApplicationContext(),"Activity not killed",Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String title , String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}





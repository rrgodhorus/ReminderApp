package com.rajath.reminderapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        toolbar = findViewById(R.id.toolbar_new_task);
        toolbar.setTitle("New Task");
        toolbar.setNavigationIcon(R.drawable.ic_nav_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewTask.this,"Nav back",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int Day = c.get(Calendar.DAY_OF_MONTH);


        final DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dayOfWeek = new SimpleDateFormat("EEEE").format(new Date(year,month,dayOfMonth-1));
                String month_txt = new SimpleDateFormat("MMM").format(new Date(year,month,dayOfMonth-1));
                     //edt2.setText(dayOfMonth + "-" + (month+1) + "-" + year);
                edt2.setText(dayOfWeek + ", " + dayOfMonth + "-" + month_txt + "-" + year);
            }
        },Year,Month,Day);
        edt2.setKeyListener(null);
        edt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.setTitle("Select Date");
                dpd.show();
            }
        });

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
}

package com.rajath.reminderapp;


import java.text.DateFormat;
import java.util.Date;

public class Tasks {

    int id;
    String taskdesc;
    boolean checked;
    Date date;

    public Tasks(String s,int id1){
        id = id1;
        taskdesc = s;
        checked = false;
        date = null;

    }
    public Tasks(int id1){
        id = id1;
        checked = false;
        date = null;

    }
   // public String setTask(){

    //}
}

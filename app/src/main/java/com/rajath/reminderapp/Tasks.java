package com.rajath.reminderapp;


import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;
    int id;
    String taskdesc;
    boolean checked;
    String date;
    String time;

    public Tasks(int id1,String tsk ,boolean chkd, String dt,String tm){
        id = id1;
        taskdesc = tsk;
        checked = chkd;
        date = dt;
        time = tm;

    }
    public Tasks(int id1){
        id = id1;
        checked = false;
        date = null;

    }
   // public String setTask(){

    @Override
    public String toString() {
        return super.toString();
        //return "Tasks[id=" + id + ", taskdesc=" +
    }


}

package com.rajath.reminderapp;



public class Tasks {

    int id;
    String taskdesc;
     boolean checked;

    public Tasks(String s,int id1){
        id = id1;
        taskdesc = s;
        checked = false;

    }
    public Tasks(){
        checked = false;
    }
   // public String setTask(){

    //}
}

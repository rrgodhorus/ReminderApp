package com.rajath.reminderapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class ViewHolder extends RecyclerView.ViewHolder {

    CheckBox chkbx;
    TextView tv1;
    int id;
    public ViewHolder(View itemView) {
        super(itemView);
        chkbx = itemView.findViewById(R.id.chk1);
        tv1 = itemView.findViewById(R.id.tv);
    }
}

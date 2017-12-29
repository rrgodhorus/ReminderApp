package com.rajath.reminderapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;


public class ViewHolder extends RecyclerView.ViewHolder {

    CheckedTextView chk1;
    public ViewHolder(View itemView) {
        super(itemView);
        chk1 = itemView.findViewById(R.id.chkdtxt);
    }
}

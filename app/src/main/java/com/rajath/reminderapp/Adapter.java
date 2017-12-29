package com.rajath.reminderapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    Tasks[] tasks;

    public Adapter(Tasks[] tasksArrray){
        tasks = tasksArrray;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View newView = layoutInflater.inflate(R.layout.item_format,parent,false);
        ViewHolder viewHolder = new ViewHolder(newView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.chk1.setText(tasks[position].taskdesc);
        holder.chk1.setChecked(tasks[position].checked);

    }

    @Override
    public int getItemCount() {
        return tasks.length;
    }
}

package com.rajath.reminderapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    Tasks[] tasks;


    public Adapter(Tasks[] tasksArrray){
        tasks = tasksArrray;

    }


       /* View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((TextView) v).setText("YESSSS");
                tasks[pos].taskdesc = "Set";

            }
        }*/

    

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View newView = layoutInflater.inflate(R.layout.item_format,parent,false);
        ViewHolder viewHolder = new ViewHolder(newView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int pos = position;
        holder.tv1.setText(tasks[position].taskdesc+position);
        holder.chkbx.setChecked(tasks[position].checked);
        holder.id = position;
        tasks[position].id = position;
        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) v).setText("YESSSS");
                tasks[pos].taskdesc = "Set";
            }
        });
        //holder.chkbx.setOnCheckedChangeListener();

    }

    @Override
    public int getItemCount() {
        return tasks.length;
    }
}

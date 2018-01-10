package com.rajath.reminderapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    Tasks[] tasks;
    Context context;

    public Adapter(Tasks[] tasksArrray,Context ctx){
        tasks = tasksArrray;
        context = ctx;

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
        holder.tv1.setText(tasks[position].taskdesc);
        //holder.chkbx.setChecked(tasks[position].checked);
        holder.id = tasks[position].id;
        final int pos = holder.id;
        //int pos = holder.getAdapterPosition();
       holder.tv1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context,NewTask.class);
               intent.putExtra("edit","true");
               intent.putExtra("id",pos);
               context.startActivity(intent);

           }
       });
        //holder.chkbx.setOnCheckedChangeListener();

    }

    @Override
    public int getItemCount() {
        if(tasks == null)
        return 0;
        else return tasks.length;
    }
}

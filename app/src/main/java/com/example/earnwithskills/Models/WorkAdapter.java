package com.example.earnwithskills.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earnwithskills.R;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.viewHolder> {

    private List<Work> workList;
    CardClickListener cardClickListener;
    public WorkAdapter(List<Work> workList,CardClickListener cardClickListener) {
        this.workList = workList;
        this.cardClickListener=cardClickListener;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list,parent,false);
        viewHolder holder=new viewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Work w=workList.get(position);
        System.out.println(w.getTopic());
        System.out.println(w.getOrg());
        holder.topic.setText(w.getTopic());
        holder.skill.setText(w.getSkill());

    }

    @Override
    public int getItemCount() {
        return workList.size();
    }


    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView topic,skill;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            topic=(TextView)itemView.findViewById(R.id.txt_job);
            skill=(TextView)itemView.findViewById(R.id.txt_skill);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cardClickListener.onClick(v,getAbsoluteAdapterPosition());
        }
    }

    public interface CardClickListener
    {
        void onClick(View view, int position);
    }
}

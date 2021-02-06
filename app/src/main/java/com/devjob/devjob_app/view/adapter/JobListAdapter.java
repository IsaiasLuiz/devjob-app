package com.devjob.devjob_app.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devjob.devjob_app.R;
import com.devjob.devjob_app.model.Job;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobsViewHolder> {

    private List<Job> jobs;

    private static RecyclerItemClickListener clickListener;

    public JobListAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void setClickListener(RecyclerItemClickListener clickListener) {
        JobListAdapter.clickListener = clickListener;
    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jobs, parent, false);
        JobsViewHolder viewHolder = new JobsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final JobsViewHolder holder, final int position) {
        holder.title.setText(jobs.get(position).getTitle());
        holder.subTitle.setText(jobs.get(position).getSeniority());
        holder.supportingText.setText(jobs.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public static class JobsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;

        public TextView subTitle;

        public TextView supportingText;

        public JobsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            subTitle = itemView.findViewById(R.id.card_subtitle);
            supportingText = itemView.findViewById(R.id.card_supporting_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition());
        }
    }

}

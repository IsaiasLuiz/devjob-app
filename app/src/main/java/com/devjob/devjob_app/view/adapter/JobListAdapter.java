package com.devjob.devjob_app.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobsViewHolder> {

    private List siteList;

    private static RecyclerItemClickListener clickListener;

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class JobsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public JobsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {
        }
    }

}

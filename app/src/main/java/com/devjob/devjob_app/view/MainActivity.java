package com.devjob.devjob_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.devjob.devjob_app.R;
import com.devjob.devjob_app.view.adapter.JobListAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView jobsRecyclerView;

    private JobListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
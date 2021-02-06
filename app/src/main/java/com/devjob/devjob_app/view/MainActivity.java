package com.devjob.devjob_app.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devjob.devjob_app.R;
import com.devjob.devjob_app.model.Job;
import com.devjob.devjob_app.model.Pageable;
import com.devjob.devjob_app.model.Resource;
import com.devjob.devjob_app.permission.ConnectionService;
import com.devjob.devjob_app.permission.PermissionService;
import com.devjob.devjob_app.service.jobs.JobService;
import com.devjob.devjob_app.view.adapter.JobListAdapter;
import com.devjob.devjob_app.view.adapter.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Job> jobs;

    private JobService service;

    private PermissionService permissionService;

    private RecyclerView jobsRecyclerView;

    private JobListAdapter adapter;

    private Pageable pageable;

    private boolean isLoading;

    private ProgressBar progressBar;

    private FloatingActionButton buttonFilter;

    private static final int FILTER_ACTIVITY = 1;

    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobs = new LinkedList<>();
        service = new JobService();
        permissionService =  new PermissionService(this);

        pageable = new Pageable();

        progressBar = findViewById(R.id.main_loading);
        buttonFilter = findViewById(R.id.filter);
        buttonFilter.setOnClickListener(this);

        jobsRecyclerView = findViewById(R.id.jobs_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        jobsRecyclerView.setLayoutManager(layoutManager);

        adapter = new JobListAdapter(jobs);
        jobsRecyclerView.setAdapter(adapter);

        jobsRecyclerView.addOnScrollListener(new  RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled( RecyclerView recyclerView, int dx,int dy) {

                if(((LinearLayoutManager) layoutManager).findLastVisibleItemPosition() == layoutManager.getItemCount()-1) {
                    if (isLoading) {
                        return;
                    }
                    if(pageable.getTotalElements() > 0) {
                        getJobs(Integer.toString(pageable.getPage() + 1), filter);
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        adapter.setClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String jobId = jobs.get(position).getId();
                Intent intent = new Intent(getApplicationContext(), JobDetails.class);
                intent.putExtra(Job.JOB_ID_KEY, jobId);
                startActivity(intent);
            }
        });
        getJobs("0", filter);
    }

    private void getJobs(String page, String filter) {
        if(ConnectionService.isConnected(this)) {
            isLoading = true;
            progressBar.setVisibility(View.VISIBLE);
            if (permissionService.hasPermission()) {
                Call<Resource> call = service.get(page, filter);
                call.enqueue(new Callback<Resource>() {
                    @Override
                    public void onResponse(Call<Resource> call, Response<Resource> response) {
                        if (response.isSuccessful()) {
                            List<Job> list = response.body().getData().getJobs().getJobs();
                            pageable = response.body().getData().getJobs().getPageable();
                            updateView(list);
                            isLoading = false;
                        } else {
                            isLoading = false;
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Resource> call, Throwable t) {
                        isLoading = false;
                        progressBar.setVisibility(View.GONE);
                    }
                });
            } else {
                permissionService.requestPermission();
            }
        } else {
            System.out.println("Não foi possível conectar a internet");
        }

    }

    public void updateView(List<Job> jobs) {
        this.jobs.addAll(jobs);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionService.REQUEST_PERMISSION) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equalsIgnoreCase(Manifest.permission.INTERNET) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    getJobs("0", filter);
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v == buttonFilter) {
            Intent intent = new Intent(this, FilterActivity.class);
            startActivityForResult(intent, FILTER_ACTIVITY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == FILTER_ACTIVITY) {
            Bundle params = data != null ? data.getExtras() : null;
            filter = params.getString(FilterActivity.FILTER_NAME_KEY);
            jobs.clear();
            adapter.notifyDataSetChanged();
            getJobs("0", filter);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
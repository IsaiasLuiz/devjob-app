package com.devjob.devjob_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.devjob.devjob_app.R;
import com.devjob.devjob_app.model.Benefit;
import com.devjob.devjob_app.model.CompanyValue;
import com.devjob.devjob_app.model.Job;
import com.devjob.devjob_app.model.Qualification;
import com.devjob.devjob_app.model.Resource;
import com.devjob.devjob_app.model.Responsibility;
import com.devjob.devjob_app.model.Technology;
import com.devjob.devjob_app.permission.ConnectionService;
import com.devjob.devjob_app.permission.PermissionService;
import com.devjob.devjob_app.service.jobs.JobService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetails extends AppCompatActivity implements View.OnClickListener {

    private String jobId;

    private boolean isLoading;

    private ProgressBar progressBar;

    private PermissionService permissionService;

    private JobService service;

    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        permissionService =  new PermissionService(this);
        service = new JobService();

        progressBar = findViewById(R.id.details_loading);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        extractData();
    }

    private void extractData() {
        Bundle bundle = getIntent().getExtras();
        jobId = bundle.getString(Job.JOB_ID_KEY);
        getJobs();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getJobs() {
        if(ConnectionService.isConnected(this)) {
            isLoading = true;
            progressBar.setVisibility(View.VISIBLE);
            if (permissionService.hasPermission()) {
                Call<Resource> call = service.getById(jobId);
                call.enqueue(new Callback<Resource>() {
                    @Override
                    public void onResponse(Call<Resource> call, Response<Resource> response) {
                        if (response.isSuccessful()) {
                            job = response.body().getData().getJob();
                            updateView();
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

    private void updateView() {
        TextView title =  findViewById(R.id.details_title);
        title.setText(job.getTitle());

        TextView subtitle =  findViewById(R.id.details_subtitle);
        subtitle.setText(job.getSeniority() + ", " + job.getVacancyType() + ", " + job.getLocation().getCity() + "/" + job.getLocation().getState());

        TextView description = findViewById(R.id.details_description);
        description.setText(job.getDescription());

        TextView responsibilities = findViewById(R.id.details_responsibilities);
        StringBuilder res = new StringBuilder("Responsabilidades e Atribuições: \n");
        for (Responsibility responsibility : job.getResponsibilities()) {
            res.append(responsibility.getDescription() + "\n");
        }
        responsibilities.setText(res);

        TextView qualifications = findViewById(R.id.details_qualifications);
        StringBuilder qua = new StringBuilder("Qualificações: \n");
        for (Qualification qualification : job.getQualifications()) {
            qua.append(qualification.getDescription() + "\n");
        }
        qualifications.setText(qua);

        TextView technologies = findViewById(R.id.details_technologies);
        StringBuilder tech = new StringBuilder("Tecnologias:\n");
        for (Technology technology : job.getTechnologies()) {
            tech.append(technology.getName() + ", ");
        }
        tech.replace(tech.length() -2, tech.length(), "");
        technologies.setText(tech);

        TextView companyN = findViewById(R.id.details_company_name);
        companyN.setText("Empresa: " + job.getCompany().getName() + "\n");

        TextView company = findViewById(R.id.details_company);
        StringBuilder comp = new StringBuilder("");
        comp.append(job.getCompany().getLegacy() + "\n");
        for (CompanyValue value : job.getCompany().getValues()) {
            comp.append(value.getDescription() + "\n");
        }
        comp.replace(comp.length() -1, comp.length(), "");
        company.setText(comp);

        TextView benefits = findViewById(R.id.details_benefits);
        StringBuilder ben = new StringBuilder(job.isRemoteWork() ? "Vaga Remota\n\n" :  "Vaga presencial\n\n");
        ben.append("Salário: " + job.getRemuneration() + "\n\n");

        ben.append("Benefícios\n");
        for (Benefit benefit : job.getBenefits()) {
            ben.append(benefit.getDescription());
            if (benefit.getValue() != null && benefit.getValue() > 0) {
                ben.append(": " + benefit.getValue() + "\n");
            }
        }
        benefits.setText(ben);

        ImageView phone = findViewById(R.id.details_call);
        phone.setOnClickListener(this);

        ImageView mail = findViewById(R.id.details_mail);
        mail.setOnClickListener(this);

        ImageView wpp = findViewById(R.id.details_wpp);
        wpp.setOnClickListener(this);

        ImageView linkedin = findViewById(R.id.details_linkedin);
        linkedin.setOnClickListener(this);

        isLoading = false;
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.details_card).setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.details_call) {
            Uri uri = Uri.parse("tel:"+ job.getContact().getPhone());
            Intent intent = new Intent(Intent.ACTION_DIAL,uri);
            startActivity(intent);
        } else if (v.getId() == R.id.details_mail) {
            Uri uri = Uri.parse("mailto:"+ job.getContact().getMail());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        } else if (v.getId() == R.id.details_wpp) {
            Uri uri = Uri.parse("https://api.whatsapp.com/send?phone="+ job.getContact().getWhatsapp());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        } else if (v.getId() == R.id.details_linkedin) {
            Uri uri = Uri.parse("http://www.linkedin.com/profile/view/"+ job.getContact().getLinkedin());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    }
}
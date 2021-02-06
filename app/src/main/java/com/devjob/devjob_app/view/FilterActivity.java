package com.devjob.devjob_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.devjob.devjob_app.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.LinkedList;
import java.util.List;

public class FilterActivity extends AppCompatActivity   implements View.OnClickListener {

    public static final String FILTER_NAME_KEY = "FILTER_NAME";

    private TextInputEditText title;

    private TextInputEditText seniority;

    private TextInputEditText inputTechnologies;

    private List<String> technologies;

    private TextInputEditText remuneration;

    private TextInputEditText company;

    private TextInputEditText state;

    private TextInputEditText city;

    private TextView textView;

    private CheckBox isRemote;

    private RadioGroup radioGroup;

    private Button addTechnology;

    private Button confirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        technologies = new LinkedList<>();

        title = findViewById(R.id.filter_title);
        seniority = findViewById(R.id.filter_seniority);
        inputTechnologies = findViewById(R.id.filter_technology);
        remuneration = findViewById(R.id.filter_remuneration);
        company = findViewById(R.id.filter_company);
        state = findViewById(R.id.filter_state);
        textView = findViewById(R.id.selected_technologies);
        city = findViewById(R.id.filter_city);
        isRemote = findViewById(R.id.filter_remote);
        radioGroup = findViewById(R.id.radioGroup);
        addTechnology = findViewById(R.id.add_technology);
        confirm = findViewById(R.id.btn_confirm);

        addTechnology.setOnClickListener(this);
        confirm.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent data = new Intent();
            data.putExtra(FILTER_NAME_KEY, "");
            setResult(RESULT_OK, data);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == addTechnology) {
            String tech = inputTechnologies.getText().toString();
            if (tech != null && !tech.isEmpty()) {
                technologies.add(tech);
                textView.setText(technologies.toString());
                inputTechnologies.setText("");
            }
        } else if (v == confirm) {
            getFilter();
        }
    }

    private void getFilter() {
        StringBuilder filter = new StringBuilder("filter: {");
        if (!title.getText().toString().isEmpty()) {
            filter.append("title: \"" + title.getText().toString() + "\",");
        }
        if (!seniority.getText().toString().isEmpty()) {
            filter.append( "seniority: \"" + seniority.getText().toString() + "\",");
        }
        if (!technologies.isEmpty()) {
            StringBuilder sTech  = new StringBuilder("");
            for (String t : technologies) {
                sTech.append("\"" + t + "\"");
            }
            filter.append("tecnologies: [" + sTech.toString() + "],");
        }
        if (!remuneration.getText().toString().isEmpty()) {
            filter.append("remuneration: " + remuneration.getText().toString() + ",");
        }

        filter.append("isRemoteWork: " + isRemote.isChecked() + ",");

        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_button_clt) {
           filter.append("vacancyType: CLT,");
        }
        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_button_pj) {
            filter.append("vacancyType: PJ,");
        }
        if (!company.getText().toString().isEmpty()) {
            filter.append( "companyName: \"" + company.getText().toString() + "\",");
        }
        if (!state.getText().toString().isEmpty()) {
            filter.append( "state: \"" + state.getText().toString() + "\",");
        }
        if (!city.getText().toString().isEmpty()) {
            filter.append( "city: \"" + city.getText().toString() + "\"");
        }
        filter.append("}");

        Intent data = new Intent();
        data.putExtra(FILTER_NAME_KEY, filter.toString());
        setResult(RESULT_OK, data);
        finish();

    }
}
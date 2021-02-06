package com.devjob.devjob_app.service.jobs;

import com.devjob.devjob_app.model.Data;
import com.devjob.devjob_app.model.Jobs;
import com.devjob.devjob_app.model.Resource;
import com.devjob.devjob_app.service.ServiceUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobService {

    private Retrofit retrofit;

    public Call<Resource> get(String page, String filter) {


        retrofit = new Retrofit.Builder()
                .baseUrl(ServiceUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitJobService retrofitService = retrofit.create(RetrofitJobService.class);
        String query = "";
        try {
            query = URLEncoder.encode("{" +
                    "            jobs(pageable:{page: " + page + "}, " + filter + ") {" +
                    "             jobs {" +
                    "              id" +
                    "              title" +
                    "              description" +
                    "              seniority" +
                    "            }" +
                    "              pageable {" +
                    "                page" +
                    "                size" +
                    "                offset" +
                    "              totalElements" +
                    "              }" +
                    "            }" +
                    "          }", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return retrofitService.get(query);
    }

    public Call getById(String id) {
        retrofit = new Retrofit.Builder()
                .baseUrl(ServiceUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RetrofitJobService retrofitService = retrofit.create(RetrofitJobService.class);
        String query = "";

        try {
            query = URLEncoder.encode("{" +
                    "  job(id: \"" + id + "\") {" +
                    "    title" +
                    "    description" +
                    "    seniority" +
                    "    remuneration" +
                    "    isRemoteWork" +
                    "    vacancyType" +
                    "    qualifications {" +
                    "      description" +
                    "    }" +
                    "    technologies {" +
                    "      name" +
                    "    }" +
                    "    responsibilities {" +
                    "      description" +
                    "    }" +
                    "    company {" +
                    "      name" +
                    "      legacy" +
                    "      values {" +
                    "        description" +
                    "      }" +
                    "    }" +
                    "    benefits {" +
                    "      description" +
                    "      value" +
                    "    }" +
                    "    location {" +
                    "      city" +
                    "      state" +
                    "    }" +
                    "    contact {" +
                    "      phone" +
                    "       mail" +
                    "      whatsapp" +
                    "      linkedin" +
                    "    }" +
                    "    recruiter {" +
                    "      name" +
                    "    }" +
                    "    createdAt" +
                    "  }}", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retrofitService.get(query);
    }

}

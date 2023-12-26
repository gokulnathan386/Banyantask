package com.example.banyantask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class workmanage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workmanage);

        TextView textViewreponse = findViewById(R.id.textViewreponse);

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();


        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(HttpRequestWorker.class)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).enqueue(workRequest);




    }
}
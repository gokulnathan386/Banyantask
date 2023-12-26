package com.example.banyantask;


import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequestWorker extends Worker {

    public HttpRequestWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @NonNull
    @Override
    public Result doWork() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder() // Use OkHttp's Request.Builder
                    .url("https://api.spaceflightnewsapi.net/v4/articles/?format=json&limit=20&offset=1")
                    .build();

            Response response = client.newCall(request).execute();


            if (response.isSuccessful()) {

                   Log.d("fvfvfvfvfvfvfvf","suucess");

                    return Result.success();
            } else {
                Log.d("fvfvfvfvfvfvfvf","Failed");

                return Result.failure();
            }
        } catch (Exception e) {
            return Result.failure();
        }
    }
}

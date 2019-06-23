package com.example.easytravel;

import android.app.Application;

import com.example.easytravel.util.YelpFusionAPI;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIApplication extends Application {

    public static YelpFusionAPI sAPIInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        String key = getResources().getString(R.string.yelp_key);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(
                it -> {
                    Request request = it.request().newBuilder().addHeader("Authorization", "Bearer " + key).build();
                    return it.proceed(request);
                }
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        sAPIInterface = retrofit.create(YelpFusionAPI.class);
    }
}

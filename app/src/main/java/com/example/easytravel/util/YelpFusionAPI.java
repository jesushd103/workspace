package com.example.easytravel.util;

import com.example.easytravel.model.Event;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface YelpFusionAPI {

    @GET("businesses/search")
    Call<JsonObject> getBusinesses(@Query("location") String location, @Query("term") String term, @Query("price") String price);

    @GET("businesses/{id}/reviews")
    Call<JsonObject> getReviews(@Path("id") String id);

    @GET("events/{id}")
    Call<Event> getEvents(@Path("id") String id);

    @GET("categories")
    Call<JsonObject> getCategories();

    @GET("categories/{alias}")
    Call<JsonObject> categoryDetails(@Path("alias") String alias);
}

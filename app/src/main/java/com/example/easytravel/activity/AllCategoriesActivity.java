package com.example.easytravel.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.easytravel.APIApplication;
import com.example.easytravel.R;
import com.example.easytravel.adapter.AllCategoriesAdapter;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        Toolbar toolbar = findViewById(R.id.toolbar_all_category);
        toolbar.setTitle("All Categories");
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.all_categories_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        APIApplication.sAPIInterface.getCategories().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    runOnUiThread(() -> {
                        recyclerView.setAdapter(new AllCategoriesAdapter(response.body().get("categories").getAsJsonArray()));
                    });
                } else {
                    Toast.makeText(AllCategoriesActivity.this, "Query Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(AllCategoriesActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

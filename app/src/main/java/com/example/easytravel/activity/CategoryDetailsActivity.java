package com.example.easytravel.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytravel.APIApplication;
import com.example.easytravel.R;
import com.example.easytravel.model.Category;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        Toolbar toolbar = findViewById(R.id.toolbar_category_details);
        toolbar.setTitle("Category Details");
        setSupportActionBar(toolbar);
        String aliasVal = getIntent().getStringExtra("alias");
        TextView alias = findViewById(R.id.alias);
        TextView title = findViewById(R.id.title);
        TextView parent_aliases = findViewById(R.id.parent_aliases);
        TextView country_whitelist = findViewById(R.id.country_whitelist);
        TextView country_blacklist = findViewById(R.id.country_blacklist);

        APIApplication.sAPIInterface.categoryDetails(aliasVal).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    runOnUiThread(() -> {
                        Gson gson = new Gson();
                        Category category = gson.fromJson(response.body().get("category"), Category.class);
                        alias.setText(category.getAlias());
                        title.setText(category.getTitle());
                        parent_aliases.setText(Arrays.toString(category.getParent_aliases().toArray()));
                        country_whitelist.setText(Arrays.toString(category.getCountry_whitelist().toArray()));
                        country_blacklist.setText(Arrays.toString(category.getCountry_blacklist().toArray()));
                    });
                } else {
                    Toast.makeText(CategoryDetailsActivity.this, "Query Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(CategoryDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.easytravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytravel.APIApplication;
import com.example.easytravel.R;
import com.example.easytravel.adapter.SearchBusinessesAdapter;
import com.example.easytravel.model.Business;
import com.example.easytravel.model.Location;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBusinessesActivity extends AppCompatActivity implements SearchBusinessesAdapter.OnBusinessClick {
    @BindView(R.id.spinner_term)
    Spinner spinnerTerm;
    @BindView(R.id.spinner_location)
    Spinner spinnerLocation;
    @BindView(R.id.spinner_price)
    Spinner spinnerPrice;

    @BindView(R.id.search_button)
    Button button;

    @BindView(R.id.search_businesses_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.search_hint)
    TextView searchHintText;

    String[] terms;
    String[] locations;
    String[] prices;

    private List<Business> businessList = new ArrayList<>();
    private SearchBusinessesAdapter businessAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_businesses);
        Toolbar toolbar = findViewById(R.id.toolbar_search_businesses);
        toolbar.setTitle("Search Businesses");
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        initSpinner();
        initRecyclerView();
    }

    private void search() {
        //handle exceptions
        try {
            String term = spinnerTerm.getSelectedItem().toString();
            String location = spinnerLocation.getSelectedItem().toString();
            String priceStr = spinnerPrice.getSelectedItem().toString();
            if (term == null || location == null || priceStr == null || term.equals("") || location.equals("") || priceStr.equals("")) {
                Toast.makeText(SearchBusinessesActivity.this,
                        "Something wrong about the search conditions.",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }
            String price = "";
            switch (priceStr) {
                case "$":
                    price = "1";
                    break;
                case "$$":
                    price = "2";
                    break;
                case "$$$":
                    price = "3";
                    break;
                case "$$$$":
                    price = "4";
                    break;
            }
            if (price.equals("")) {
                Toast.makeText(SearchBusinessesActivity.this,
                        "Something wrong about the search conditions.",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }
            APIApplication.sAPIInterface.getBusinesses(location,term,price).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        runOnUiThread(() -> {
                            try {
                                JsonObject result=response.body();
                                businessList.clear();
                                businessList.addAll(convertToList(result));
                                searchHintText.setVisibility(View.GONE);
                                businessAdapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                Log.e("error", "JSON exception error");
                            }
                        });
                    } else {
                        Toast.makeText(SearchBusinessesActivity.this, "Query Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(SearchBusinessesActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }

                private List<Business> convertToList(JsonObject result) {
                    List<Business> res = new ArrayList<>();
                    JsonArray businesses = result.getAsJsonArray("businesses");
                    for (int i = 0; i < businesses.size(); i++) {
                        JsonObject temp = (JsonObject) businesses.get(i);
                        Business business = new Business();
                        business.setId(temp.get("id").getAsString());
                        business.setName(temp.get("name").getAsString());
                        business.setPrice(temp.get("price").getAsString());
                        business.setRating(temp.get("rating").getAsInt());
                        business.setReview_count(temp.get("review_count").getAsInt());
                        business.setPhone(temp.get("phone").getAsString());
                        JsonObject temp2 = temp.get("location").getAsJsonObject();
                        if (temp2 != null) {
                            Gson gson=new Gson();
                            Location location = gson.fromJson(temp2.toString(),Location.class);
                            business.setLocation(location);
                        }
                        res.add(business);
                    }
                    return res;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(SearchBusinessesActivity.this,
                    "Something wrong about the search conditions.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void initSpinner() {
        terms = getResources().getStringArray(R.array.terms);
        locations = getResources().getStringArray(R.array.locations);
        prices = getResources().getStringArray(R.array.prices);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, terms);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, prices);
        spinnerTerm.setAdapter(adapter1);
        spinnerLocation.setAdapter(adapter2);
        spinnerPrice.setAdapter(adapter3);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchBusinessesActivity.this));
        businessAdapter = new SearchBusinessesAdapter(SearchBusinessesActivity.this, businessList);
        recyclerView.setAdapter(businessAdapter);
    }

    @Override
    public void onBusinessClick(int pos) {
        Intent intent = new Intent(SearchBusinessesActivity.this, BusinessDetailsActivity.class);
        intent.putExtra("business", businessList.get(pos));
        startActivity(intent);
    }
}

package com.example.easytravel.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytravel.APIApplication;
import com.example.easytravel.R;
import com.example.easytravel.adapter.ReviewsAdapter;
import com.example.easytravel.model.Business;
import com.example.easytravel.model.Review;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessDetailsActivity extends AppCompatActivity {

    private Business business;

    @BindView(R.id.business_name)
    TextView nameText;
    @BindView(R.id.business_price)
    TextView priceText;
    @BindView(R.id.business_rating)
    TextView ratingText;
    @BindView(R.id.business_location)
    TextView locationText;
    @BindView(R.id.business_review_count)
    TextView reviewCountText;
    @BindView(R.id.business_phone)
    TextView phoneText;
    @BindView(R.id.review_recycler_view)
    RecyclerView recyclerView;

    ReviewsAdapter reviewAdapter;

    List<Review> reviewsList=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);
        Toolbar toolbar = findViewById(R.id.toolbar_business_details);
        toolbar.setTitle("Business details");
        setSupportActionBar(toolbar);
        business=(Business) getIntent().getSerializableExtra("business");
        ButterKnife.bind(this);
        initRecyclerView();
        populate();
    }

    public void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewAdapter=new ReviewsAdapter(this,reviewsList);
        recyclerView.setAdapter(reviewAdapter);
    }

    public void populate(){
        if(business==null){
            Toast.makeText(BusinessDetailsActivity.this,
                    "Business is not initialized." ,
                    Toast.LENGTH_LONG
            ).show();
            return;
        }
        if(business.getName()!=null)
            nameText.setText("Name: "+business.getName());
        if(business.getPrice()!=null)
            priceText.setText("Price: "+business.getPrice());
        if(business.getRating()!=0)
            ratingText.setText("Rating: "+business.getRating());
        if(business.getLocation()!=null)
            locationText.setText("Location: "+business.getLocation().toString());
        if(business.getReview_count()!=0)
            reviewCountText.setText("Review count: "+business.getReview_count());
        if(business.getPhone()!=null)
            phoneText.setText("Phone: "+business.getPhone());

        if(business.getId()==null||business.getId().equals("")){
            Toast.makeText(BusinessDetailsActivity.this, "Something wrong.", Toast.LENGTH_SHORT).show();
            return;
        }
        APIApplication.sAPIInterface.getReviews(business.getId()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    runOnUiThread(() -> {
                        try {
                            JsonObject result=response.body();
                            reviewsList.clear();
                            reviewsList.addAll(convertToList(result));
                            reviewAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Log.e("error", "JSON exception error");
                        }
                    });
                } else {
                    Toast.makeText(BusinessDetailsActivity.this, "Query Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(BusinessDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }

            private List<Review> convertToList(JsonObject result) {
                List<Review> res = new ArrayList<>();
                JsonArray reviews = result.getAsJsonArray("reviews");
                for (int i = 0; i < reviews.size(); i++) {
                    JsonObject temp = (JsonObject) reviews.get(i);
                    Review review = new Review();
                    JsonObject temp2=temp.getAsJsonObject("user");
                    if(temp2!=null){
                        review.setName(temp2.get("name").getAsString());
                    }
                    if(temp.get("text")!=null)
                        review.setContent(temp.get("text").getAsString());
                    if(temp.get("time_created")!=null)
                        review.setTime(temp.get("time_created").getAsString());
                    res.add(review);
                }
                return res;
            }
        });
    }
}

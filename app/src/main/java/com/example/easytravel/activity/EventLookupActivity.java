package com.example.easytravel.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytravel.APIApplication;
import com.example.easytravel.R;
import com.example.easytravel.model.Event;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventLookupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_lookup);
        Toolbar toolbar = findViewById(R.id.toolbar_event);
        toolbar.setTitle("Event Lookup");
        setSupportActionBar(toolbar);
        TextView attending_count = findViewById(R.id.attending_count);
        TextView category = findViewById(R.id.category);
        TextView event_site_url = findViewById(R.id.event_site_url);
        TextView tickets_url = findViewById(R.id.tickets_url);
        TextView business_id = findViewById(R.id.business_id);
        EditText event_id = findViewById(R.id.event_id);
        event_id.setText("oakland-saucy-oakland-restaurant-pop-up");
        findViewById(R.id.event_search).setOnClickListener(it -> {
            String id = event_id.getText().toString();
            id = id.trim();
            if (id.isEmpty()) {
                Toast.makeText(this, "Id cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                APIApplication.sAPIInterface.getEvents(id).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            runOnUiThread(() -> {
                                Event re = response.body();
                                attending_count.setText(String.valueOf(re.getAttending_count()));
                                category.setText(re.getCategory());
                                event_site_url.setText(re.getEvent_site_url());
                                tickets_url.setText(String.valueOf(re.getTickets_url()));
                                business_id.setText(String.valueOf(re.getBusiness_id()));
                            });
                        } else {
                            Toast.makeText(EventLookupActivity.this, "Query Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    //handle failure
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(EventLookupActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}

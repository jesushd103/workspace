package com.example.easytravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.rides.client.SessionConfiguration;

public class UberImpl_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber_impl_);

        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("8h8lhYIka93LFePvYIqWTZ-w7e7vB4BB")
                .setServerToken("54-uDp8rgLr2iEzvKlNYbkBBPd96flkuAD25C1KF")
                .setRedirectUri("<REDIRECT_URI>")
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();

        UberSdk.initialize(config);


        RideRequestButton requestButton = new RideRequestButton(UberImpl_Activity.this);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.estaes);
        layout.addView(requestButton);



    }


}

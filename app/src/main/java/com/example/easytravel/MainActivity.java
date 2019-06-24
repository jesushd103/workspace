package com.example.easytravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.easytravel.activity.SplashActivity;
import com.example.easytravel.budget.MainActivity3;
import com.example.easytravel.budget.intro.IntroActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login) {
            return true;
        }
        if(id == R.id.action_intro)
        {
            startIntro();
            return true;
        }
        SharedPreferences prefs = getSharedPreferences("protect.budgetwatch", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            startIntro();
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        return super.onOptionsItemSelected(item);
    }
    public void MensajeCar(View view){
        Toast.makeText(this, "Coches a alquilar", Toast.LENGTH_SHORT).show();
    }
    public void Following(View view){
        Intent following = new Intent(this, MapActivity.class);
        startActivity(following);

    }
    public void Following2(View view){
        Intent following2 = new Intent(this, UberImpl_Activity.class);
        startActivity(following2);

    }
    public void goToInBrowser(View view){
        Intent inBrowser = new Intent(this, InBrowser.class);
        startActivity(inBrowser);

    }
    public void goToLogin(View view){
        Intent login = new Intent(this, GoogleLogin.class);
        startActivity(login);

    }

    public void goToCamera(View view){
        Intent camera = new Intent(this, CameraActivity.class);
        startActivity(camera);

    }
    public void goToWeather(View view){
        Intent weather = new Intent(this, WeatherActivity.class);
        startActivity(weather);

    }

    public void goToRestaurant(View view){
        Intent resta = new Intent(this, Map2Activity.class);
        startActivity(resta);

    }
    public void goToFly(View view){
        Intent fly = new Intent(this, AirportActivity.class);
        startActivity(fly);

    }
    public void goToYelp(View view){
        Intent yelp = new Intent(this, SplashActivity.class);
        startActivity(yelp);

    }
    public void goToAlarm(View view){
        Intent alarm = new Intent(this, Alarm.class);
        startActivity(alarm);

    }
    public void goToTask(View view){
        Intent task = new Intent(this, TaskHome.class);
        startActivity(task);

    }
    public void goToBudget(View view){
        Intent budget = new Intent(this, MainActivity3.class);
        startActivity(budget);

    }
    private void startIntro()
    {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }


}

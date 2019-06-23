package com.example.easytravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.easytravel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.button_business_search)
    Button mButton1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Easy Travel");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);

        ButterKnife.bind(this);

        mButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity2.this, SearchBusinessesActivity.class);
                startActivity(mIntent);
            }
        });

        findViewById(R.id.btn_all_category).setOnClickListener(it -> {
            Intent intent = new Intent(this, AllCategoriesActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_event_lookup).setOnClickListener(it -> {
            Intent intent = new Intent(this, EventLookupActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_exit: {
                finish();
                System.exit(0);
                break;
            }
        }
        return true;
    }


}

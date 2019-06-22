package com.example.easytravel;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AirportActivity extends AppCompatActivity implements
        FlightsFragment.OnFragmentInteractionListener,
        ArrivalsFragment.OnFragmentInteractionListener,
        DeparturesFragment.OnFragmentInteractionListener {

    private TimerTask simTask;
    private Toolbar m_ActionToolBar;
    private DrawerLayout m_DrawerLayout;
    private NavigationView m_navigationView;
    private BottomNavigationView m_flightsNavigationView;
    private SearchableWithButtonView m_searchableWithButtonView;

    private FragmentManager m_FragmentMgr;
    private FragmentTransaction m_FragmentTran;

    final Handler handler = new Handler();

    final private AirportDataModel
            m_AirportDataModel = new AirportDataModel();

    final private NavigationBarListener
            m_NavigationBarListener = new NavigationBarListener();

    private class NavigationBarListener implements
            NavigationView.OnNavigationItemSelectedListener
    {
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            // set item as selected to persist highlight
            menuItem.setChecked(true);

            m_FragmentTran = m_FragmentMgr.beginTransaction();

            if (menuItem.getItemId() == R.id.flights)
                m_FragmentTran.replace(R.id.airport_fragment_container,
                        FlightsFragment.newInstance());

            else if (menuItem.getItemId() == R.id.about) {}

            m_FragmentTran.addToBackStack(null); m_FragmentTran.commit();

            if (m_DrawerLayout.isDrawerOpen(GravityCompat.START))
                m_DrawerLayout.closeDrawers();

            return true;
        }

        public void setupInitialFragment()
        {
            if (m_FragmentMgr == null)
                m_FragmentMgr = getSupportFragmentManager();

            m_FragmentTran = m_FragmentMgr.beginTransaction();
            m_FragmentTran.add(R.id.airport_fragment_container,
                    FlightsFragment.newInstance()).commit();
        }
    }

    public class SearchableWithButtonListener implements View.OnClickListener, TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            FlightsFragment flightsFragment = (FlightsFragment)
                    m_FragmentMgr.findFragmentById(R.id.airport_fragment_container);

            RecyclerView flightsRecyclerView = null;
            ArrayList<AirportDataModel> DataSet, oldDataSet = null;
            TabLayout tabLayout = findViewById(R.id.flights_destination_tabs);

            if (tabLayout.getTabAt(0).isSelected()) {
                //flightsRecyclerView = findViewById(R.id.arrivals_recycler_view);
                flightsRecyclerView = flightsFragment.m_ArrivalsFragment.m_ArrivalsRecyclerView;
                oldDataSet = flightsFragment.m_ArrivalsFragment.m_ArrivalsDataSet;

            }

            else if (tabLayout.getTabAt(1).isSelected()) {
                //flightsRecyclerView = findViewById(R.id.departures_recycler_view);
                flightsRecyclerView = flightsFragment.m_DeparturesFragment.m_DeparturesRecyclerView;
                oldDataSet = flightsFragment.m_DeparturesFragment.m_DeparturesDataSet;
            }

            if (!charSequence.toString().isEmpty()) {
                DataSet = new FlightsIndexedSearch().
                        doSearch(charSequence.toString(), oldDataSet);

                if (DataSet.size() == 0) {
                    DataSet = oldDataSet;
                }
            }

            else DataSet = oldDataSet;

            FlightsRecyclerAdapter recyclerAdapter
                    = new FlightsRecyclerAdapter(DataSet, getBaseContext());

            flightsRecyclerView.setAdapter(recyclerAdapter);

            recyclerAdapter.notifyDataSetChanged();
            recyclerAdapter.notifyItemRangeChanged(0, DataSet.size());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        @Override
        public void onClick(View view) {
            if (!m_DrawerLayout.isDrawerOpen(GravityCompat.START))
                m_DrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport);

        //m_ActionToolBar = findViewById(R.id.airport_actionbar);
        m_DrawerLayout = findViewById(R.id.airport_drawer_layout);
        m_navigationView = findViewById(R.id.airport_navigation_view);
        m_flightsNavigationView = findViewById(R.id.flights_navigation);

        //setSupportActionBar(m_ActionToolBar);
        //this.setupActionBar(R.drawable.ic_dehaze_white_24dp);

        m_searchableWithButtonView =
                new SearchableWithButtonView(AirportActivity.this, R.id.searchable);

        m_searchableWithButtonView.setupSearchableWithButton();
        m_searchableWithButtonView.setTextWatchListener(new SearchableWithButtonListener());
        m_searchableWithButtonView.setSearchButtonClickListener(new SearchableWithButtonListener());

        m_navigationView.setNavigationItemSelectedListener(m_NavigationBarListener);

        m_flightsNavigationView.setSelectedItemId(R.id.flights_now);
        m_NavigationBarListener.setupInitialFragment(); this.hideSoftInputKeyboard();

        m_flightsNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        FlightsFragment flightsFragment = (FlightsFragment)
                                m_FragmentMgr.findFragmentById(R.id.airport_fragment_container);

                        RecyclerView recyclerView = null;
                        ArrayList<AirportDataModel> dataSet = null;
                        FlightsRecyclerAdapter recyclerAdapter = null;
                        TabLayout tabLayout = findViewById(R.id.flights_destination_tabs);

                        if (tabLayout.getTabAt(0).isSelected()) {
                            recyclerView = flightsFragment.m_ArrivalsFragment.m_ArrivalsRecyclerView;
                            dataSet = flightsFragment.m_ArrivalsFragment.m_ArrivalsDataSet;
                        }

                        else if (tabLayout.getTabAt(1).isSelected()) {
                            recyclerView = flightsFragment.m_DeparturesFragment.m_DeparturesRecyclerView;
                            dataSet = flightsFragment.m_DeparturesFragment.m_DeparturesDataSet;
                        }

                        long curr_time = System.currentTimeMillis();

                        if (menuItem.getItemId() == R.id.flights_prev)
                        {
                            recyclerAdapter = new FlightsRecyclerAdapter(m_AirportDataModel.filterByTime(dataSet,
                                    curr_time - (long)3.6e+6 * 48, curr_time), getBaseContext());
                        }

                        else if (menuItem.getItemId() == R.id.flights_now)
                        {
                            recyclerAdapter = new FlightsRecyclerAdapter(m_AirportDataModel.filterByTime(dataSet,
                                    curr_time - (long)3.6e+6 * 24, curr_time + (long)3.6e+6 * 24), getBaseContext());
                        }

                        else if (menuItem.getItemId() == R.id.flights_next)
                        {
                            recyclerAdapter = new FlightsRecyclerAdapter(m_AirportDataModel.filterByTime(dataSet,
                                    curr_time, curr_time + (long)3.6e+6 * 48), getBaseContext());
                        }

                        recyclerView.setAdapter(recyclerAdapter);

                        recyclerAdapter.notifyDataSetChanged();
                        recyclerAdapter.notifyItemRangeChanged(0, dataSet.size());

                        return true;
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.findViewById(R.id.search_bar).requestFocus();

        startSimulation();
    }

    private void startSimulation()
    {
        this.Simulate(); new Timer().schedule(simTask, 50, 10000);
    }

    private void hideSoftInputKeyboard()
    {
        View activeView;
        if ((activeView = this.getCurrentFocus()) == null)
            activeView = new View( this);

        ((InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE)).
                hideSoftInputFromWindow(activeView.getWindowToken(), 0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            m_DrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void Simulate() {
        simTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        FlightsFragment flightsFragment = (FlightsFragment)
                                m_FragmentMgr.findFragmentById(R.id.airport_fragment_container);

                        m_flightsNavigationView.setSelectedItemId(R.id.flights_now);

                        ArrayList<AirportDataModel> dataSet = null;
                        RecyclerView recyclerView = null;
                        TabLayout tabLayout = findViewById(R.id.flights_destination_tabs);

                        if (tabLayout.getTabAt(0).isSelected()) {
                            dataSet = flightsFragment.m_ArrivalsFragment.m_ArrivalsDataSet;
                            recyclerView = flightsFragment.m_ArrivalsFragment.m_ArrivalsRecyclerView;
                        }

                        else if (tabLayout.getTabAt(1).isSelected()) {
                            dataSet = flightsFragment.m_DeparturesFragment.m_DeparturesDataSet;
                            recyclerView = flightsFragment.m_DeparturesFragment.m_DeparturesRecyclerView;
                        }

                        m_AirportDataModel.Simulate(dataSet);

                        FlightsRecyclerAdapter recyclerAdapter
                                = new FlightsRecyclerAdapter(dataSet, getBaseContext());

                        recyclerView.setAdapter(recyclerAdapter);

                        recyclerAdapter.notifyDataSetChanged();
                        recyclerAdapter.notifyItemRangeChanged(0, dataSet.size());
                    }
                });
            }
        };
    }

    //private void setupActionBar(int resID)
    //{
    //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //    getSupportActionBar().setHomeAsUpIndicator(resID);
    //}
}
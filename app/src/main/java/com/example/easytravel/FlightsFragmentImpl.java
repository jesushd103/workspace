package com.example.easytravel;


import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class FlightsFragmentImpl extends android.support.v4.app.Fragment implements
        ArrivalsFragment.OnFragmentInteractionListener,
        DeparturesFragment.OnFragmentInteractionListener
{
    public RecyclerView m_RecyclerView;
    public RecyclerView.Adapter m_RecyclerAdapter;
    public RecyclerView.LayoutManager m_LayoutManager;

    public void setupFlightsRecyclerView(RecyclerView recyclerView, ArrayList<AirportDataModel> dataSet)
    {
        m_RecyclerView = recyclerView;

        m_RecyclerView.setHasFixedSize(true);

        m_LayoutManager = new LinearLayoutManager(getContext());
        m_RecyclerView.setLayoutManager(m_LayoutManager);

        m_RecyclerAdapter = new FlightsRecyclerAdapter(dataSet, getContext());

        m_RecyclerView.setAdapter(m_RecyclerAdapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
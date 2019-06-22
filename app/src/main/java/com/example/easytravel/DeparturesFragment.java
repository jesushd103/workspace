package com.example.easytravel;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DeparturesFragment extends android.support.v4.app.Fragment {

    public  RecyclerView m_DeparturesRecyclerView;

    public ArrayList<AirportDataModel> m_DeparturesDataSet;

    public FlightsFragment m_FlightsFragment;

    private OnFragmentInteractionListener mListener;

    public DeparturesFragment() {
        // Required empty public constructor
        m_DeparturesDataSet = new AirportDataModel().InitModel(20);
    }

    public static DeparturesFragment newInstance() {
        return new DeparturesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View DeparturesView =  inflater.inflate(R.layout.fragment_arrivals, container, false);

        m_FlightsFragment =
                (FlightsFragment) this.getParentFragment();
        m_DeparturesRecyclerView =
                DeparturesView.findViewById(R.id.arrivals_recycler_view);

        m_FlightsFragment.setupFlightsRecyclerView(m_DeparturesRecyclerView, m_DeparturesDataSet);

        return DeparturesView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
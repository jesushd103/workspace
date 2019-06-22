package com.example.easytravel;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArrivalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArrivalsFragment extends android.support.v4.app.Fragment {

    public  RecyclerView m_ArrivalsRecyclerView;

    public ArrayList<AirportDataModel> m_ArrivalsDataSet;

    public FlightsFragment m_FlightsFragment;

    private OnFragmentInteractionListener mListener;

    public ArrivalsFragment() {
        // Required empty public constructor
        m_ArrivalsDataSet = new AirportDataModel().InitModel(20);
    }

    public static ArrivalsFragment newInstance() {
        return new ArrivalsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ArrivalsView =  inflater.inflate(R.layout.fragment_arrivals, container, false);

        m_FlightsFragment =
                (FlightsFragment) this.getParentFragment();

        m_ArrivalsRecyclerView =
                ArrivalsView.findViewById(R.id.arrivals_recycler_view);

        m_FlightsFragment.setupFlightsRecyclerView(m_ArrivalsRecyclerView, m_ArrivalsDataSet);

        return ArrivalsView;
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
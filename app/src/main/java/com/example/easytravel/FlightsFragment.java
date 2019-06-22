package com.example.easytravel;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FlightsFragment extends FlightsFragmentImpl
{
    private TabLayout m_TabLayout;
    private ViewPager m_ViewPager;
    final private TabSelectedListener
            m_TabSelListener = new TabSelectedListener();

    public ArrivalsFragment m_ArrivalsFragment;
    public DeparturesFragment m_DeparturesFragment;

    private class TabSelectedListener implements TabLayout.OnTabSelectedListener
    {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            m_ViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private OnFragmentInteractionListener mListener;

    public FlightsFragment() {
        // Required empty public constructor
    }

    public static FlightsFragment newInstance() {
        return new FlightsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View FlightsFragmentView =
                inflater.inflate(R.layout.fragment_flights, container, false);

        m_TabLayout = FlightsFragmentView.findViewById(R.id.flights_destination_tabs);
        m_ViewPager = FlightsFragmentView.findViewById(R.id.flights_destination_pager);

        FlightsDestPagerAdapter pagerAdapter = new FlightsDestPagerAdapter(
                getChildFragmentManager(), m_TabLayout.getTabCount());

        m_ArrivalsFragment = ArrivalsFragment.newInstance();
        m_DeparturesFragment = DeparturesFragment.newInstance();

        pagerAdapter.add(m_ArrivalsFragment);
        pagerAdapter.add(m_DeparturesFragment);

        m_ViewPager.setAdapter(pagerAdapter);

        m_ViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(m_TabLayout));
        m_TabLayout.addOnTabSelectedListener(m_TabSelListener);

        return FlightsFragmentView;
    }

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
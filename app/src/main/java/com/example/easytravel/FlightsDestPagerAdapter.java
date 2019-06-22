package com.example.easytravel;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FlightsDestPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> m_Fragments = new ArrayList<Fragment>();

    public FlightsDestPagerAdapter(FragmentManager FragmentMgr, int NumberOfTabs) {
        super(FragmentMgr);
    }

    public void add(Fragment fragment)
    {
        m_Fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return m_Fragments.get(position);
    }

    @Override
    public int getCount() {
        return m_Fragments.size();
    }
}
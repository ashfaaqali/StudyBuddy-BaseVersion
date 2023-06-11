package com.example.studybuddybaseversion;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ScheduleViewPagerAdapter extends FragmentPagerAdapter {

    int tabCount = 0;

    public ScheduleViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MonScheduleFragment();
            case 1:
                return new TueScheduleFragment();
            case 2:
                return new WedScheduleFragment();
            case 3:
                return new ThuScheduleFragment();
            case 4:
                return new FriScheduleFragment();
            case 5:
                return new SatScheduleFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

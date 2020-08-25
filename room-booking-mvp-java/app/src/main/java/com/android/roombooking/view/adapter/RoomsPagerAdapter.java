package com.android.roombooking.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.roombooking.model.Calendar;
import com.android.roombooking.view.fragment.RoomFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoomsPagerAdapter extends FragmentPagerAdapter {

    private List<Calendar> calendars = new ArrayList<>();

    public RoomsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addAll(Collection<Calendar> calendars) {
        this.calendars.addAll(calendars);
        notifyDataSetChanged();
    }

    public void add(Calendar calendar) {
        this.calendars.add(calendar);
        notifyDataSetChanged();
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars.clear();
        this.calendars = calendars;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Calendar calendar = calendars.get(position);
        return RoomFragment.newInstance(calendar.id);
    }

    @Override
    public int getCount() {
        return calendars.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    public Calendar getPageCalendar(int position){
        return calendars.get(position);
    }
}

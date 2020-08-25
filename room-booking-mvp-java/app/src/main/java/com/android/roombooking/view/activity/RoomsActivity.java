package com.android.roombooking.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.roombooking.R;
import com.android.roombooking.RoomBookingApplication;
import com.android.roombooking.model.Calendar;
import com.android.roombooking.presenter.CalendarsPresenter;
import com.android.roombooking.utils.injector.component.ApplicationComponent;
import com.android.roombooking.utils.injector.component.CalendarsComponent;
import com.android.roombooking.utils.injector.component.DaggerCalendarsComponent;
import com.android.roombooking.utils.injector.module.ActivityModule;
import com.android.roombooking.utils.injector.module.CalendarsModule;
import com.android.roombooking.view.CalendarsView;
import com.android.roombooking.view.adapter.RoomsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomsActivity extends AppCompatActivity implements CalendarsView {

    private static final String TAG = "RoomsActivity";

    @Inject
    CalendarsPresenter calendarsPresenter;
    @Bind(R.id.ar_toolbar)
    Toolbar toolbar;
    @Bind(R.id.ar_tabs)
    TabLayout tabLayout;
    @Bind(R.id.ar_pager)
    ViewPager viewPager;
    @Bind(R.id.ar_loading)
    View loadingView;
    @Bind(R.id.ar_fab)
    FloatingActionButton fab;

    private RoomsPagerAdapter calendarPagerAdapter;
    private CalendarsComponent calendarsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        ButterKnife.bind(this);
        ApplicationComponent appComponent = ((RoomBookingApplication) getApplication()).getApplicationComponent();
        calendarsComponent = DaggerCalendarsComponent.builder()
                .calendarsModule(new CalendarsModule())
                .activityModule(new ActivityModule(this))
                .applicationComponent(appComponent)
                .build();
        calendarsComponent.inject(this);
        initializePresenter();
        setSupportActionBar(toolbar);
        calendarPagerAdapter = new RoomsPagerAdapter(getSupportFragmentManager(), 0);
        viewPager.setAdapter(calendarPagerAdapter);
    }

    public CalendarsComponent getCalendarsComponent() {
        return calendarsComponent;
    }

    @Override
    protected void onStop() {
        super.onStop();
        calendarsPresenter.onStop();
    }

    private void initializePresenter() {
        calendarsPresenter.attachView(this);
    }

    @OnClick(R.id.ar_fab)
    public void onFabClick() {
        int position = viewPager.getCurrentItem();
        Calendar pageCalendar = calendarPagerAdapter.getPageCalendar(position);
        Intent intent = new Intent(this, NewEventActivity.class);
        intent.putExtra(NewEventActivity.KEY_CALENDAR_ID, pageCalendar.getId());
        intent.putExtra(NewEventActivity.KEY_CALENDAR_NAME, pageCalendar.getDescription());
        startActivity(intent);
    }

    public void addRooms(Collection<Calendar> rooms) {
        calendarPagerAdapter.addAll(rooms);
        hideLoading();
        tabLayout.setupWithViewPager(viewPager);
    }

    public void showLoading() {
        fab.setVisibility(View.GONE);
        viewPager.setVisibility(View.INVISIBLE);
        loadingView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        fab.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCalendars(final List<Calendar> calendars) {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                addRooms(calendars);
            }
        };
        runOnUiThread(myRunnable);
    }

    @Override
    public void showError() {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RoomsActivity.this, R.string.main_error_loading, Toast.LENGTH_LONG).show();
            }
        };
        runOnUiThread(myRunnable);
    }
}

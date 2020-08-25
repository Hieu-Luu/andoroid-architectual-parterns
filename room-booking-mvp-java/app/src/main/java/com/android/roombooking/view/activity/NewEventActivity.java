package com.android.roombooking.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.roombooking.R;
import com.android.roombooking.presenter.NewEventPresenter;

import javax.inject.Inject;

import butterknife.Bind;

public class NewEventActivity extends AppCompatActivity {

    @Inject
    public NewEventPresenter newEventPresenter;

    public static final String KEY_CALENDAR_ID = "calendarId";
    public static final String KEY_CALENDAR_NAME = "calendarName";


    @Bind(R.id.ane_summary)
    EditText summary;

    @Bind(R.id.ane_description)
    EditText description;

    @Bind(R.id.ane_end_date)
    EditText endDate;

    @Bind(R.id.ane_end_time)
    EditText endTime;

    @Bind(R.id.ane_start_date)
    EditText startDate;

    @Bind(R.id.ane_start_time)
    EditText startTime;

    @Bind(R.id.ane_toolbar)
    Toolbar toolbar;

    @Bind(R.id.ane_progressbar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
    }
}

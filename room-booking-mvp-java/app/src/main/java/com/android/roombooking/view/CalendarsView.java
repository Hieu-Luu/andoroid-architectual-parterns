package com.android.roombooking.view;

import com.android.roombooking.model.Calendar;

import java.util.List;

public interface CalendarsView extends BaseView {

    void showCalendars(List<Calendar> calendars);
    void showLoading();
    void showError();

}

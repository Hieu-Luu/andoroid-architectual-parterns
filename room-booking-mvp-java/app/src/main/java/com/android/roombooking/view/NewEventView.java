package com.android.roombooking.view;

import com.android.roombooking.model.Event;

public interface NewEventView extends BaseView {
    void showLoading();
    void showError();
    void showEventCreated(Event event);
}

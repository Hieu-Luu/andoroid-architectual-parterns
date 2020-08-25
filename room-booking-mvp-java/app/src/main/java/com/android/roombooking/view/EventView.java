package com.android.roombooking.view;

import com.android.roombooking.model.Event;

public interface EventView extends BaseView {
    void showLoading();
    void showError();
    void showEvent(Event event);
}

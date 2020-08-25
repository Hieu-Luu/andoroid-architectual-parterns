package com.android.roombooking.view;

import com.android.roombooking.model.Event;

import java.util.List;

public interface EventsCollectionView extends BaseView {

    void showLoading();
    void showEvents(List<Event> events);
    void showError();
    void showEmpty();
}

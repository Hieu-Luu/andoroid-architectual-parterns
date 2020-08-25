package com.android.roombooking.domain;

import com.android.roombooking.model.Event;
import com.android.roombooking.repository.Repository;

import rx.Observable;

public class FetchEventUsecase implements Usecase<Event> {
    private Repository repository;
    private int calendarId;
    private int eventId;

    public FetchEventUsecase(Repository repository) {
        this.repository = repository;
    }

    public void setEventParams(int calendarId, int eventId) {
        this.calendarId = calendarId;
        this.eventId = eventId;
    }

    @Override
    public Observable<Event> execute() {
        return repository.getEvent(calendarId, eventId);
    }
}

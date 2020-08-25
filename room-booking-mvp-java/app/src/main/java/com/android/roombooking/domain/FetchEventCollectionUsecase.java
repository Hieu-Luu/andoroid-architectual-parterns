package com.android.roombooking.domain;

import com.android.roombooking.model.Event;
import com.android.roombooking.repository.Repository;

import java.util.List;

import rx.Observable;

public class FetchEventCollectionUsecase implements Usecase<List<Event>> {

    private Repository repository;
    private int calendarId;

    public FetchEventCollectionUsecase(Repository repository) {
        this.repository = repository;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    @Override
    public Observable<List<Event>> execute() {
        return repository.getEvents(calendarId).map(new ResponseMappingFunc<List<Event>>());
    }
}

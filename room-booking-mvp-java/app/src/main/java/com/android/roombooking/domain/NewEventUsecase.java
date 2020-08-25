package com.android.roombooking.domain;

import com.android.roombooking.model.Event;
import com.android.roombooking.repository.Repository;

import rx.Observable;

public class NewEventUsecase implements Usecase<Event> {

    private Repository repository;
    private Event event;

    public NewEventUsecase(Repository repository) {
        this.repository = repository;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public Observable<Event> execute() {
        return repository.postEvent(event).map(new ResponseMappingFunc<Event>());
    }
}

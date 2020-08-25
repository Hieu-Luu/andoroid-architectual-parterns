package com.android.roombooking.domain;

import com.android.roombooking.model.Calendar;
import com.android.roombooking.repository.Repository;

import java.util.List;

import rx.Observable;


public class FetchCalendarsUsecase implements Usecase<List<Calendar>> {

    private Repository repository;

    public FetchCalendarsUsecase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Calendar>> execute() {
        return repository.getCalendars().map(new ResponseMappingFunc<List<Calendar>>());
    }
}

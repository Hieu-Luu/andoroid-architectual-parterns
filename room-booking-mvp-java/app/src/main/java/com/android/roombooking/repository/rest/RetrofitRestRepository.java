package com.android.roombooking.repository.rest;

import com.android.roombooking.model.Calendar;
import com.android.roombooking.model.Event;
import com.android.roombooking.model.ResponseWrapper;
import com.android.roombooking.model.User;
import com.android.roombooking.repository.Repository;

import java.util.List;

import retrofit.Retrofit;
import rx.Observable;

/**
 * Class that implements interface repository. Should use rest methods provided by ApiService.class to implements Repository methods
 */
public class RetrofitRestRepository implements Repository {

    private ApiService apiService;

    public RetrofitRestRepository(Retrofit retrofit) {
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<ResponseWrapper<List<Calendar>>> getCalendars() {
        return apiService.getCalendars();
    }

    @Override
    public Observable<ResponseWrapper<List<Event>>> getEvents(int calendarId) {
        return apiService.getEvents(calendarId);
    }

    @Override
    public Observable<ResponseWrapper<Event>> postEvent(Event event) {
        return apiService.postEvent(event.calendarId, event);
    }

    @Override
    public Observable<Event> getEvent(int calendarId, int eventId) {
        return null;
    }

    @Override
    public Observable<ResponseWrapper<List<User>>> getUsers() {
        return apiService.getUsers();
    }
}

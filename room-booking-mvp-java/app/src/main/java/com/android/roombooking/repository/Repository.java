package com.android.roombooking.repository;

import com.android.roombooking.model.Calendar;
import com.android.roombooking.model.Event;
import com.android.roombooking.model.ResponseWrapper;
import com.android.roombooking.model.User;

import java.util.List;

import rx.Observable;

public interface Repository {
    Observable<ResponseWrapper<List<Calendar>>> getCalendars();
    Observable<ResponseWrapper<List<Event>>> getEvents(int calendarId);
    Observable<ResponseWrapper<Event>> postEvent(Event event);
    Observable<Event> getEvent(int calendarId, int eventId);
    Observable<ResponseWrapper<List<User>>> getUsers();

}

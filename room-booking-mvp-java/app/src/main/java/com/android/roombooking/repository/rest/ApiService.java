package com.android.roombooking.repository.rest;

import com.android.roombooking.model.Calendar;
import com.android.roombooking.model.Event;
import com.android.roombooking.model.ResponseWrapper;
import com.android.roombooking.model.User;

import java.util.List;

import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Retrofit service interface with retrofit annoted method like @GET, @POST
 */
public interface ApiService {

    @GET("calendars")
    Observable<ResponseWrapper<List<Calendar>>> getCalendars();

    @GET("calendars/{calendar_id}/events")
    Observable<ResponseWrapper<List<Event>>> getEvents(@Path("calendar_id") int calendarId);

    @DELETE("calendars/{calendar_id}/events/{event_id}")
    Observable<Response> deleteEvent(@Path("calendar_id") int calendarId, @Path("event_id") int eventId);

    @GET("users")
    Observable<ResponseWrapper<List<User>>> getUsers();

    @POST("calendars/{calendar_id}/events")
    Observable<ResponseWrapper<Event>> postEvent(@Path("calendar_id") int calendarId, @Body Event event);
}

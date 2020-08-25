package com.android.roombooking.presenter;

import com.android.roombooking.domain.NewEventUsecase;
import com.android.roombooking.model.Event;
import com.android.roombooking.view.NewEventView;

import java.util.ArrayList;
import java.util.Calendar;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

public class NewEventPresenter implements Presenter<NewEventView> {

    private NewEventView newEventView;
    private Subscription newEventSubscription;
    private NewEventUsecase newEventUsecase;
    private Event newEvent;
    Calendar startCalendar;
    Calendar endCalendar;

    public NewEventPresenter(NewEventUsecase newEventUsecase) {
        this.newEventUsecase = newEventUsecase;
        this.newEvent = new Event();
        this.startCalendar = Calendar.getInstance();
        this.endCalendar = Calendar.getInstance();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (newEventSubscription != null && !newEventSubscription.isUnsubscribed()) {
            newEventSubscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(NewEventView view) {
        this.newEventView = view;
    }

    public void setCalendarId(int calendarId) {
        newEvent.calendarId = calendarId;
    }

    public void setOrganizer(String organizer) {
        newEvent.organizer = organizer;
    }

    public void setSummary(String summary) {
        newEvent.summary = summary;
    }

    public void setDescription(String description) {
        newEvent.description = description;
    }

    private void setStatus(String status) {
        newEvent.status = status;
    }

    public void setStartDate(int year, int monthOfYear, int dayOfMonth) {
        startCalendar.set(year, monthOfYear, dayOfMonth);
    }

    public void setStartTime(int hourOfDay, int minute) {
        startCalendar.set(HOUR_OF_DAY, hourOfDay);
        startCalendar.set(MINUTE, minute);
    }

    public void setEndDate(int year, int monthOfYear, int dayOfMonth) {
        endCalendar.set(year, monthOfYear, dayOfMonth);
    }

    public void setEndTime(int hourOfDay, int minute) {
        endCalendar.set(HOUR_OF_DAY, hourOfDay);
        endCalendar.set(MINUTE, minute);
    }

    private void setEventDates(){
        newEvent.startDate = startCalendar.getTime();
        newEvent.endDate = endCalendar.getTime();
    }

    public void postNewEvent() {
        setEventDates();
        newEvent.attendeesEmails = new ArrayList<>(1);
        newEvent.attendeesEmails.add("example@example.com");
        newEventView.showLoading();
        newEventView.showLoading();
        newEventSubscription = newEventUsecase.execute()
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, Event>() {
                    @Override
                    public Event call(Throwable throwable) {
                        throwable.printStackTrace();
                        newEventView.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        if (event != null) {
                            newEventView.showEventCreated(event);
                        }
                    }
                });
    }
}

package com.android.roombooking.presenter;

import com.android.roombooking.domain.FetchEventCollectionUsecase;
import com.android.roombooking.model.Event;
import com.android.roombooking.view.EventsCollectionView;

import java.util.List;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class EventsCollectionPresenter implements Presenter<EventsCollectionView> {

    private EventsCollectionView eventsCollectionView;
    private Subscription getEventsSubscription;
    private FetchEventCollectionUsecase fetchEventCollectionUsecase;
    private int calendarId;
    private List<Event> eventsCollection;

    public EventsCollectionPresenter(FetchEventCollectionUsecase fetchEventCollectionUsecase) {
        this.fetchEventCollectionUsecase = fetchEventCollectionUsecase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {
        getEvents();
    }

    @Override
    public void onStop() {
        if (getEventsSubscription != null && !getEventsSubscription.isUnsubscribed()) {
            getEventsSubscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(EventsCollectionView view) {
        this.eventsCollectionView = view;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    private void getEvents() {
        if (eventsCollection != null && eventsCollectionView != null) {
            eventsCollectionView.showEvents(eventsCollection);
        } else {
            eventsCollectionView.showLoading();
        }
        fetchEventCollectionUsecase.setCalendarId(calendarId);
        getEventsSubscription = fetchEventCollectionUsecase.execute()
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<Event>>() {
                    @Override
                    public List<Event> call(Throwable throwable) {
                        throwable.printStackTrace();
                        eventsCollectionView.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<List<Event>>() {
                    @Override
                    public void call(List<Event> events) {
                        if (events != null) {
                            if (!events.isEmpty()) {
                                eventsCollection = events;
                                eventsCollectionView.showEvents(events);
                            } else {
                                eventsCollectionView.showEmpty();
                            }
                        }
                    }
                });
    }

    public void onRefresh() {
        eventsCollection = null;
        getEvents();
    }
}

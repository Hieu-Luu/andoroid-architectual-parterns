package com.android.roombooking.presenter;

import com.android.roombooking.domain.FetchEventUsecase;
import com.android.roombooking.model.Event;
import com.android.roombooking.view.EventView;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class EventPresenter implements Presenter<EventView> {

    private EventView eventView;
    private Subscription eventSubscription;
    private int calendarId;
    private int eventId;
    private FetchEventUsecase fetchEventUsecase;

    public EventPresenter(FetchEventUsecase fetchEventUsecase) {
        this.fetchEventUsecase = fetchEventUsecase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (eventSubscription != null && !eventSubscription.isUnsubscribed()) {
            eventSubscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(EventView view) {
        this.eventView = view;
    }

    /**
     * Calls getEvent after setting calendarId and eventId params
     *
     * @param calendarId
     * @param eventId
     */
    public void setEventParams(int calendarId, int eventId) {
        this.calendarId = calendarId;
        this.eventId = eventId;
        getEvent();
    }

    private void getEvent() {
        fetchEventUsecase.setEventParams(calendarId, eventId);
        eventView.showLoading();
        eventSubscription = fetchEventUsecase.execute()
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, Event>() {
                    @Override
                    public Event call(Throwable throwable) {
                        throwable.printStackTrace();
                        eventView.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        eventView.showEvent(event);
                    }
                });
    }
}

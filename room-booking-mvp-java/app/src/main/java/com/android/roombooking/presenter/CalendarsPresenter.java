package com.android.roombooking.presenter;

import android.util.Log;

import com.android.roombooking.domain.FetchCalendarsUsecase;
import com.android.roombooking.model.Calendar;
import com.android.roombooking.view.CalendarsView;


import java.util.List;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CalendarsPresenter implements Presenter<CalendarsView> {

    private static final String TAG = "CalendarsPresenter";

    private Subscription getCalendarsSubscription;
    private CalendarsView calendarsView;
    private FetchCalendarsUsecase fetchCalendarsUsecase;

    public CalendarsPresenter(FetchCalendarsUsecase fetchCalendarsUsecase) {
        this.fetchCalendarsUsecase = fetchCalendarsUsecase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (getCalendarsSubscription != null && !getCalendarsSubscription.isUnsubscribed()) {
            getCalendarsSubscription.unsubscribe();
        }

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(CalendarsView view) {
        this.calendarsView = view;
        getCalendars();
    }

    private void getCalendars() {
        calendarsView.showLoading();
        getCalendarsSubscription = fetchCalendarsUsecase.execute()
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<Calendar>>() {
                    @Override
                    public List<Calendar> call(Throwable throwable) {
                        Log.e(TAG, "getCalendars()");
                        throwable.printStackTrace();
                        calendarsView.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<List<Calendar>>() {
                    @Override
                    public void call(List<Calendar> calendars) {
                        if (calendars != null) {
                            calendarsView.showCalendars(calendars);
                        }
                    }
                });
    }
}

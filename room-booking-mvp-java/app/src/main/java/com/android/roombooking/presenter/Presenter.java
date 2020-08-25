package com.android.roombooking.presenter;

import com.android.roombooking.view.BaseView;

public interface Presenter<T extends BaseView> {

    void onCreate();
    void onStart();
    void onStop();
    void onPause();
    void attachView(T view);
}

package com.android.roombooking.domain;

import rx.Observable;

public interface Usecase<T> {
    Observable<T> execute();
}

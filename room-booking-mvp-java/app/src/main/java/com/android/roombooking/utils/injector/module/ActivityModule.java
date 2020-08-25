package com.android.roombooking.utils.injector.module;

import android.app.Activity;
import android.content.Context;

import com.android.roombooking.utils.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public Context context() {
        return activity;
    }

}

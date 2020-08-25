package com.android.roombooking;

import android.app.Application;

import com.android.roombooking.utils.injector.component.ApplicationComponent;
import com.android.roombooking.utils.injector.component.DaggerApplicationComponent;
import com.android.roombooking.utils.injector.module.ApplicationModule;

public class RoomBookingApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupInjector();
    }

    private void setupInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

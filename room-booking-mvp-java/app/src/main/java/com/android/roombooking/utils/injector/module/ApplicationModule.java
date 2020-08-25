package com.android.roombooking.utils.injector.module;

import android.app.Application;

import com.android.roombooking.RoomBookingApplication;
import com.android.roombooking.utils.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides dependencies for application
 * Must be injected in main app component
 * If you want to use ApplicationContext please use Application object
 */
@Module
public class ApplicationModule {
    private final RoomBookingApplication application;

    public ApplicationModule(RoomBookingApplication application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    public RoomBookingApplication provideMvpApplication() {
        return application;
    }

    @Provides
    @PerApplication
    public Application provideApplication() {return application; }
}

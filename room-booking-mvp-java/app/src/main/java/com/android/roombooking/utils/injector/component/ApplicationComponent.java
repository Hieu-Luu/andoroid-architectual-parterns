package com.android.roombooking.utils.injector.component;

import android.app.Application;

import com.android.roombooking.RoomBookingApplication;
import com.android.roombooking.repository.Repository;
import com.android.roombooking.utils.injector.module.ApplicationModule;
import com.android.roombooking.utils.injector.module.NetworkModule;
import com.android.roombooking.utils.scope.PerApplication;

import dagger.Component;

@PerApplication
@Component(modules = {ApplicationModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {

    Application application();
    RoomBookingApplication roomBookerApplication();
    Repository repository();
}

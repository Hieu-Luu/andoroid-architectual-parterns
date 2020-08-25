package com.android.roombooking.utils.injector.component;

import android.content.Context;

import com.android.roombooking.utils.injector.module.ActivityModule;
import com.android.roombooking.utils.injector.module.CalendarsModule;
import com.android.roombooking.utils.scope.PerActivity;
import com.android.roombooking.view.activity.RoomsActivity;
import com.android.roombooking.view.fragment.RoomFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, CalendarsModule.class})
public interface CalendarsComponent {
    void inject(RoomsActivity roomsActivity);
    void inject(RoomFragment roomFragment);
    Context context();
}

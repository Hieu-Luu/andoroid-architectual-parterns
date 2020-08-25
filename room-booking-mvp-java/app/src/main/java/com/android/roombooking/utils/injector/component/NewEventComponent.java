package com.android.roombooking.utils.injector.component;

import com.android.roombooking.utils.injector.module.ActivityModule;
import com.android.roombooking.utils.injector.module.EventModule;
import com.android.roombooking.utils.scope.PerActivity;
import com.android.roombooking.view.activity.NewEventActivity;

import dagger.Component;

@PerActivity
@Component (dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, EventModule.class})
public interface NewEventComponent {
    void inject(NewEventActivity activity);
}

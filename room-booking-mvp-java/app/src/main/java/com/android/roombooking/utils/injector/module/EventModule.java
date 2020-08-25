package com.android.roombooking.utils.injector.module;

import com.android.roombooking.domain.NewEventUsecase;
import com.android.roombooking.presenter.NewEventPresenter;
import com.android.roombooking.repository.Repository;
import com.android.roombooking.utils.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@PerActivity
@Module
public class EventModule {

    @PerActivity
    @Provides
    public NewEventUsecase provideNewEventUsecase(Repository repository) {
        return new NewEventUsecase(repository);
    }

    @PerActivity
    @Provides
    public NewEventPresenter provideNewEventPresenter(NewEventUsecase usecase) {
        return new NewEventPresenter(usecase);
    }

}

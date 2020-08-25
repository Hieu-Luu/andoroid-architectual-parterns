package com.android.roombooking.utils.injector.module;

import com.android.roombooking.domain.FetchCalendarsUsecase;
import com.android.roombooking.domain.FetchEventCollectionUsecase;
import com.android.roombooking.presenter.CalendarsPresenter;
import com.android.roombooking.presenter.EventsCollectionPresenter;
import com.android.roombooking.repository.Repository;
import com.android.roombooking.utils.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class CalendarsModule {

    @PerActivity
    @Provides
    public FetchCalendarsUsecase provideGetCalendarsUsecase(Repository repository) {
        return new FetchCalendarsUsecase(repository);
    }

    @PerActivity
    @Provides
    public CalendarsPresenter provideCalendarPresenter(FetchCalendarsUsecase usecase) {
        return new CalendarsPresenter(usecase);
    }

    @Provides
    public FetchEventCollectionUsecase provideGetEventCollectionUsecase(Repository repository) {
        return new FetchEventCollectionUsecase(repository);
    }

    @Provides
    public EventsCollectionPresenter provideEventsCollectionPresenter(FetchEventCollectionUsecase usecase) {
        return new EventsCollectionPresenter(usecase);
    }
}

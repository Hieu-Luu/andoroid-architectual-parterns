package com.android.roombooking.domain;

import com.android.roombooking.model.User;
import com.android.roombooking.repository.Repository;

import java.util.List;

import rx.Observable;

public class FetchUsersUsecase implements Usecase<List<User>> {

    private Repository repository;

    public FetchUsersUsecase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<User>> execute() {
        return repository.getUsers().map(new ResponseMappingFunc<List<User>>());
    }
}

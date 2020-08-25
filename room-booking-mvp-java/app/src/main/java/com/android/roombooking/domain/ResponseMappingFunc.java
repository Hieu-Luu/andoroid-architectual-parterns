package com.android.roombooking.domain;

import com.android.roombooking.model.ResponseWrapper;

import rx.functions.Func1;


public class ResponseMappingFunc<RM> implements Func1<ResponseWrapper<RM>, RM> {

    @Override
    public RM call(ResponseWrapper<RM> rmResponseWrapper) {
        if (rmResponseWrapper == null) {
            return null;
        }
        return rmResponseWrapper.body;
    }
}

package com.almin.mvpdemo.provider;

import android.text.TextUtils;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class BaseErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError cause) {
        String errorDescription;
        if(cause.getKind() == RetrofitError.Kind.NETWORK){
            errorDescription = "net error";
        } else {
            if (cause.getResponse() == null) {
                errorDescription = "unknow error";
            } else {
                try {
                    errorDescription = handleCustomError(cause);
                    if(errorDescription == null){
                        errorDescription = (String) cause.getBody();
                    }
                    if(TextUtils.isEmpty(errorDescription)){
                        errorDescription = cause.getMessage();
                    }
                } catch (Exception ex) {
                    errorDescription = ex.getLocalizedMessage();
                }
            }
        }
        return new Exception(errorDescription);
    }

    protected String handleCustomError(RetrofitError cause){
        return null;
    }
}
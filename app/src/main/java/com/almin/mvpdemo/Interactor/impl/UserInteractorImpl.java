package com.almin.mvpdemo.Interactor.impl;

import com.almin.mvpdemo.Interactor.OnUsersFetchCallback;
import com.almin.mvpdemo.Interactor.UserInteractor;
import com.almin.mvpdemo.model.User;
import com.almin.mvpdemo.provider.UserProvider;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Almin on 2016/1/24.
 */
public class UserInteractorImpl implements UserInteractor{
    private UserProvider mProvider;
    private OnUsersFetchCallback mOnUsersFetchCallback;

    public UserInteractorImpl(OnUsersFetchCallback onUsersFetchCallback){
        mOnUsersFetchCallback = onUsersFetchCallback;
        mProvider = new UserProvider();
    }

    @Override
    public void loadUsers(String username, String password) {
        // send request
        mProvider.loadUsers(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mOnUsersFetchCallback.onFetchError("please try.");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mOnUsersFetchCallback.onFetchSuccess(new ArrayList<User>());
            }
        });

    }

    @Override
    public void updateUserName(String name) {
        mProvider.updateName(name, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mOnUsersFetchCallback.onFetchError("please try.");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mOnUsersFetchCallback.onUserUpdate("update success");
            }
        });
    }
}

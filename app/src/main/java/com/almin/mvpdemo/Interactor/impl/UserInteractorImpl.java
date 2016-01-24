package com.almin.mvpdemo.Interactor.impl;

import com.almin.mvpdemo.Interactor.OnUsersFetchCallback;
import com.almin.mvpdemo.Interactor.UserInteractor;
import com.almin.mvpdemo.model.User;
import com.almin.mvpdemo.provider.UserProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almin on 2016/1/24.
 */
public class UserInteractorImpl implements UserInteractor{
    private UserProvider mProvider;
    private OnUsersFetchCallback mOnUsersFetchCallback;

    public UserInteractorImpl(OnUsersFetchCallback onUsersFetchCallback){
        mOnUsersFetchCallback = onUsersFetchCallback;
    }

    @Override
    public void loadUsers(String username, String password) {
//        mProvider
        // send request

        if(true){
            mOnUsersFetchCallback.onFetchSuccess(new ArrayList<User>());
        }else{
            mOnUsersFetchCallback.onFetchError("please try.");
        }


    }

    @Override
    public void updateUserName(String name) {

        if(true){
            mOnUsersFetchCallback.onUserUpdate("update success");
        }
    }
}

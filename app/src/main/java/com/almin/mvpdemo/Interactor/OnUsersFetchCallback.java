package com.almin.mvpdemo.Interactor;

import com.almin.mvpdemo.model.User;

import java.util.List;

/**
 * Created by Almin on 2016/1/24.
 */
public interface OnUsersFetchCallback {
    void onFetchSuccess(List<User> list);
    void onFetchError(String error);
    void onUserUpdate(String text);
}

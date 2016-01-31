package com.almin.mvpdemo.presenter;

import android.content.Context;

import com.almin.mvpdemo.ui.BaseView;

/**
 * Created by Almin on 2016/1/24.
 */

public interface Presenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
    void onResume();
    void onCreate();
    void onDestroy();
    void onStop();
}
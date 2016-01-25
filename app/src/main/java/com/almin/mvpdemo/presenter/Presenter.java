package com.almin.mvpdemo.presenter;

import android.content.Context;

/**
 * Created by Almin on 2016/1/24.
 */
public interface Presenter {
    void onResume();
    void onCreate();
    void onDestroy();
    void onStop();
}

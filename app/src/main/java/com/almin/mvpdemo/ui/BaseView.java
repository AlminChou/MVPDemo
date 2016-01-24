package com.almin.mvpdemo.ui;

/**
 * Created by Administrator on 2016/1/24.
 */
public interface BaseView {
    void init();
    void onResume();
    void onDestroy();
    void showSpinner();
    void hideSpinner();
    void showToast(String text);
}

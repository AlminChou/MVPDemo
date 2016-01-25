package com.almin.mvpdemo.ui;

import android.content.Context;

import com.almin.mvpdemo.presenter.Presenter;

/**
 * Created by Administrator on 2016/1/24.
 */
public interface BaseView {
    void showSpinner();
    void hideSpinner();
    void showToast(String text);
    Presenter getCurrentPresenter();
    Context getContext();
}

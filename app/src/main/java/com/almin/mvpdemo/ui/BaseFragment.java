package com.almin.mvpdemo.ui;

import android.support.v4.app.Fragment;

import com.almin.mvpdemo.presenter.impl.PresenterImpl;

/**
 * Created by Almin on 2016/1/24.
 */
public abstract class BaseFragment extends Fragment implements BaseView{

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void showToast(String text) {

    }
}

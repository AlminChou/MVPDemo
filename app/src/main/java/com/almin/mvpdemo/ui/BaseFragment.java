package com.almin.mvpdemo.ui;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.almin.mvpdemo.presenter.Presenter;

/**
 * Created by Almin on 2016/1/24.
 */
public abstract class BaseFragment extends Fragment {
    protected abstract Presenter getCurrentPresenter();


    public void showSpinner() {

    }

    public void hideSpinner() {

    }

    public void showToast(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),text,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getCurrentPresenter().onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
       getCurrentPresenter().onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getCurrentPresenter().detachView();
    }
}

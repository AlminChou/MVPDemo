package com.almin.mvpdemo.presenter.impl;

import com.almin.mvpdemo.Interactor.OnUsersFetchCallback;
import com.almin.mvpdemo.Interactor.UserInteractor;
import com.almin.mvpdemo.Interactor.impl.UserInteractorImpl;
import com.almin.mvpdemo.model.User;
import com.almin.mvpdemo.presenter.AllUsersPresenter;
import com.almin.mvpdemo.ui.view.AllUsersView;

import java.util.List;

/**
 * Created by Almin on 2016/1/24.
 */
public class AllUsersPresenterImpl implements AllUsersPresenter ,OnUsersFetchCallback {
    private AllUsersView mAllUsersView;
    private UserInteractor mUserInteractor;

    public AllUsersPresenterImpl(AllUsersView view){
        mAllUsersView = view;
        mUserInteractor = new UserInteractorImpl(this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void loadUsers(String username, String password) {
        mAllUsersView.showSpinner();
        mUserInteractor.loadUsers(username,password);
    }

    @Override
    public void updateUserName(String name) {
        mAllUsersView.showSpinner();
        mUserInteractor.updateUserName(name);
    }

    @Override
    public void onFetchSuccess(List<User> list) {
        mAllUsersView.hideSpinner();
        mAllUsersView.showAllUsers(list);
    }

    @Override
    public void onFetchError(String error) {
        mAllUsersView.hideSpinner();
        mAllUsersView.showToast(error);
    }

    @Override
    public void onUserUpdate(String text) {
        mAllUsersView.hideSpinner();
        mAllUsersView.showToast(text);
    }


}

package com.almin.mvpdemo.presenter;

import com.almin.mvpdemo.ui.BaseView;

/**
 * Created by Almin on 2016/1/24.
 */
public interface AllUsersPresenter extends Presenter {
    void loadUsers(String username,String password);
    void updateUserName(String name);
}

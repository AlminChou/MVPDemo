package com.almin.mvpdemo.presenter;

import com.almin.mvpdemo.ui.view.AllUsersView;

/**
 * Created by Almin on 2016/1/24.
 */
public interface AllUsersPresenter extends Presenter<AllUsersView>  {
    void loadUsers(String username,String password);
    void updateUserName(String name);
}

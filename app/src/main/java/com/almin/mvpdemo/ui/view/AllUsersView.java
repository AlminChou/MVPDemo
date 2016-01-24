package com.almin.mvpdemo.ui.view;

import com.almin.mvpdemo.model.User;
import com.almin.mvpdemo.ui.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/1/24.
 */
public interface AllUsersView extends BaseView {
    void showAllUsers(List<User> users);
}

package com.almin.mvpdemo.presenter.impl;

import android.util.Log;

import com.almin.mvpdemo.Interactor.OnUsersFetchCallback;
import com.almin.mvpdemo.Interactor.UserInteractor;
import com.almin.mvpdemo.Interactor.impl.UserInteractorImpl;
import com.almin.mvpdemo.Interactor.impl.VendorCategoryInteractorImpl;
import com.almin.mvpdemo.model.User;
import com.almin.mvpdemo.presenter.AllUsersPresenter;
import com.almin.mvpdemo.presenter.VendorCategoryListPresenter;
import com.almin.mvpdemo.provider.BaseDataProvider;
import com.almin.mvpdemo.ui.view.AllUsersView;
import com.almin.mvpdemo.ui.view.VendorCategoriesView;

import java.util.List;


public class CategoryListPresenterImpl implements VendorCategoryListPresenter,VendorCategoryInteractorImpl.OnVendorCategoriesApiCallback {
    private VendorCategoriesView mVendorCategoriesView;
    private VendorCategoryInteractorImpl mVendorCategoryInteractorImplInteractor;

    public CategoryListPresenterImpl(VendorCategoriesView view){
        mVendorCategoriesView = view;
//        mVendorCategoryInteractorImplInteractor = new VendorCategoryInteractorImpl(null,this);
    }

    public CategoryListPresenterImpl(BaseDataProvider.LoaderManagerHelper loaderManagerHelper, VendorCategoriesView view){
        this(view);
        mVendorCategoryInteractorImplInteractor = new VendorCategoryInteractorImpl(loaderManagerHelper,this);
    }

    @Override
    public void onCreate() {
//        loadCategoryList();
    }




    @Override
    public void attachView(VendorCategoriesView view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStop() {

    }

//    @Override
//    public void loadUsers(String username, String password) {
//        mAllUsersView.showSpinner();
//        mUserInteractor.loadUsers(username,password);
//    }
//
//    @Override
//    public void updateUserName(String name) {
//        mAllUsersView.showSpinner();
//        mUserInteractor.updateUserName(name);
//    }



    @Override
    public void loadCategoryList() {
        Log.e("","CategoryListPresenterImpl-------loadCategoryList");
        mVendorCategoryInteractorImplInteractor.loadCategoryList();
    }

    @Override
    public void refreshCategoryList() {
        mVendorCategoryInteractorImplInteractor.refreshCategoryList();
    }

    @Override
    public void onFetchCategoryList(List<String> list) {
        mVendorCategoriesView.showCategoryList(list);
        Log.e("","onFetchCategoryList------------");
    }

    @Override
    public void onError(String error) {

    }
}

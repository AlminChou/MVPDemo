package com.almin.mvpdemo.Interactor.impl;

import android.util.Log;

import com.almin.mvpdemo.Interactor.OnApiCallback;
import com.almin.mvpdemo.Interactor.VendorCategoryInteractor;
import com.almin.mvpdemo.provider.BaseDataProvider;
import com.almin.mvpdemo.provider.VendorCategoryProvider;
import com.almin.mvpdemo.spi.RESTfulApiClient;

import java.util.List;

/**
 * Created by dgao on 2/17/2016.
 */
public class VendorCategoryInteractorImpl extends InteractorImpl implements VendorCategoryInteractor{

    OnVendorCategoriesApiCallback mOnVendorCategoriesApiCallback;
    VendorCategoryProvider mVendorCategoryProvider;

    public VendorCategoryInteractorImpl(BaseDataProvider.LoaderManagerHelper loaderManagerHelper) {
        mVendorCategoryProvider=new VendorCategoryProvider(loaderManagerHelper);
    }

    public VendorCategoryInteractorImpl(BaseDataProvider.LoaderManagerHelper loaderManagerHelper, OnVendorCategoriesApiCallback onVendorCategoriesApiCallback) {
        this(loaderManagerHelper);
        mOnVendorCategoriesApiCallback=onVendorCategoriesApiCallback;

    }

    @Override
    public void loadCategoryList() {
        Log.e("", "VendorCategoryInteractorImpl-------loadCategoryList");
        mVendorCategoryProvider.getVendorCategoryList(new RESTfulApiClient.OnApiCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> strings) {
                mOnVendorCategoriesApiCallback.onFetchCategoryList(strings);
            }

            @Override
            public void onFailure(String reason) {
                mOnVendorCategoriesApiCallback.onError(reason);
            }
        });
    }

    @Override
    public void refreshCategoryList() {
        mVendorCategoryProvider.refreshVendorCategoryList();
    }

    public interface OnVendorCategoriesApiCallback extends OnApiCallback{
        void onFetchCategoryList(List<String> list);
    }
}

package com.almin.mvpdemo.presenter;

import com.almin.mvpdemo.ui.view.VendorCategoriesView;

/**
 * Created by dgao on 2/17/2016.
 */
public interface VendorCategoryListPresenter extends Presenter<VendorCategoriesView>{
    void loadCategoryList();
    void refreshCategoryList();
}

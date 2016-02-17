package com.almin.mvpdemo.ui.view;

import com.almin.mvpdemo.ui.BaseView;

import java.util.List;

/**
 * Created by dgao on 2/16/2016.
 */
public interface VendorCategoriesView extends BaseView{
    void showCategoryList(List<String> list);
}
